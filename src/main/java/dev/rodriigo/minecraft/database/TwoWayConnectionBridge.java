package dev.rodriigo.minecraft.database;

import dev.rodriigo.minecraft.BackendPlugin;
import dev.rodriigo.minecraft.util.StringUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Connection;
import java.util.*;

public class TwoWayConnectionBridge implements NormalizedDatabaseBridge {
    // This class is used to create a connection to the database
    // This will support both SQL and MongoDB and will load the correct driver
    // based on a configuration file

    Connection connection;
    MongoDBConnector mongoDB;
    BackendPlugin backendPlugin = BackendPlugin.getInstance();
    NoSQLConnectionBridge noSQLConnectionBridge;
    SQLConnectionBridge sqlConnectionBridge;
    RelationalBridge relationalBridge;
    List<String> sqlTables = new ArrayList<>();


    public TwoWayConnectionBridge(File configFile) throws Exception {
        // Load the configuration file
        // This will be used to determine which driver to use

        final YamlConfiguration config = new YamlConfiguration();
        config.load(configFile);

        String driver = config.getString("storage.driver");
        String database = config.getString("storage.database");
        if (driver == null) {
            throw new Exception("Database Driver is null into the configuration file. Please check the file.");
        }

        if (database == null) {
            throw new Exception("Database name is null into the configuration file. Please check the file.");
        }

        if (driver.equalsIgnoreCase("mongodb")) {
            // Create a connection to MongoDB
            String uri = config.getString("storage.uri");
            if (uri == null) {
                throw new Exception("MongoDB URI is null into the configuration file. Please check the file.");
            }
            mongoDB = new MongoDBConnector(uri);
            mongoDB.setDatabase(database);

            noSQLConnectionBridge = new NoSQLConnectionBridge(mongoDB);
        } else if (driver.equalsIgnoreCase("sqlite")) {
            // Create a connection to SQLite
            RelationalBridge relationalBridge = RelationalBridge.fromSqlite(
                    backendPlugin.getDataFolder()
                            .toPath()
                            .resolve(database)
                            .toString()
            );

            sqlTables = relationalBridge.getTables();
            connection = relationalBridge.getConnection();
            this.relationalBridge = relationalBridge;
            sqlConnectionBridge = new SQLConnectionBridge(connection);
        } else if (driver.equalsIgnoreCase("mysql")) {
            // Create a connection to MySQL
            String host = config.getString("storage.host");
            int port = config.getInt("storage.port");
            String username = config.getString("storage.username");
            String password = config.getString("storage.password");
            if (host == null) {
                throw new Exception("MySQL Host is null into the configuration file. Please check the file.");
            }
            if (username == null) {
                throw new Exception("MySQL Username is null into the configuration file. Please check the file.");
            }
            if (password == null) {
                throw new Exception("MySQL Password is null into the configuration file. Please check the file.");
            }
            RelationalBridge relationalBridge = new RelationalBridge(
                    host,
                    port,
                    database,
                    username,
                    password
            );

            sqlTables = relationalBridge.getTables();
            connection = relationalBridge.getConnection();
            this.relationalBridge = relationalBridge;

            sqlConnectionBridge = new SQLConnectionBridge(connection);
        } else {
            throw new Exception("Database Driver is not supported. Please check the file.");
        }
    }

    /**
     * A function to find data from a table using a query and optional limit.
     *
     * @param  table  the name of the table to search
     * @param  query  the query to filter the results
     * @param  limit  optional limit for the number of results
     * @return        a list of maps containing the found data
     */
    @Override
    public List<Map<String, Object>> findFrom(String table, Map<String, Object> query, int... limit) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Using a map provides us a more flexible query for both SQL and MongoDB
            // Check the service using
            final int queryLimit = limit.length > 0 ? Math.max(limit[0], 1) : 1;


            if (sqlConnectionBridge != null) {
                return sqlConnectionBridge.findFrom(table, query, queryLimit);
            } else if (noSQLConnectionBridge != null) {
                return noSQLConnectionBridge.findFrom(table, query, queryLimit);
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Find records from the specified table based on the provided query.
     *
     * @param  table  the name of the table to search
     * @param  query  the query parameters
     * @return        a list of maps representing the found records
     */
    @Override
    public List<Map<String, Object>> findFrom(String table, Map<String, Object> query) {
        return findFrom(table, query, 1);
    }

    /**
     * Find one record in the specified table based on the given query.
     *
     * @param  table  the name of the table to search in
     * @param  query  the query parameters to search with
     * @return       a list containing a single map of the found record
     */
    @Override
    public List<Map<String, Object>> findOne(String table, Map<String, Object> query) {
        // Recycle the same method but with a limit of 1
        return findFrom(table, query);
    }

    /**
     * Update the specified table with the given query and update parameters.
     *
     * @param  table  the name of the table to be updated
     * @param  query  the query parameters for the update
     * @param  update the update parameters
     */
    @Override
    public void updateFor(String table, Map<String, Object> query, Map<String, Object> update) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }
            // Check the service using

            if (sqlConnectionBridge != null) {
                sqlConnectionBridge.updateFor(table, query, update);
            } else if (noSQLConnectionBridge != null) {
                noSQLConnectionBridge.updateFor(table, query, update);
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a record from the specified table based on the provided query.
     *
     * @param  table  the name of the table from which to delete the record
     * @param  query  the map representing the conditions for the deletion
     */
    @Override
    public void deleteOne(String table, Map<String, Object> query) {
        deleteFor(table, query);
    }

    /**
     * Deletes records from a specified table based on the provided query.
     *
     * @param  table   the name of the table from which to delete records
     * @param  query   a map representing the query used to select records for deletion
     * @param  limit   an optional limit for the number of records to delete
     */
    @Override
    public void deleteFor(String table, Map<String, Object> query, int... limit) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Check the service using
            final int queryLimit = limit.length > 0 ? Math.max(limit[0], 1) : 1;

            if (sqlConnectionBridge != null) {
                sqlConnectionBridge.deleteFor(table, query, queryLimit);
            } else if (noSQLConnectionBridge != null) {
                noSQLConnectionBridge.deleteFor(table, query);
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes records from the specified table based on the provided query.
     *
     * @param  table   the name of the table from which records will be deleted
     * @param  query   the map containing the conditions for deleting records
     */
    @Override
    public void deleteFor(String table, Map<String, Object> query) {
        deleteFor(table, query, 1);
    }

    /**
     * Insert data into the specified table using the provided query.
     *
     * @param  table  the name of the table to insert data into
     * @param  query  a map containing the columns and values to insert
     */
    @Override
    public void insertInto(String table, Map<String, Object> query) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Check the service using

            if (sqlConnectionBridge != null) {
                sqlConnectionBridge.insertInto(table, query);
            } else if (noSQLConnectionBridge != null) {
                noSQLConnectionBridge.insertInto(table, query);
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inserts data into the specified table using the provided query.
     *
     * @param  table  the name of the table to insert data into
     * @param  query  the query containing the data to be inserted
     */
    @Override
    public void insertInto(String table, Iterable<Map<String, Object>> query) {
        query.forEach(a -> insertInto(table, a));
    }

    /**
     * Method to drop a table from the database.
     *
     * @param  table  the name of the table to be dropped
     */
    @Override
    public void dropTable(String table) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Check the service using
            if (sqlConnectionBridge != null) {
                sqlConnectionBridge.dropTable(table);
            } else if (noSQLConnectionBridge != null) {
                noSQLConnectionBridge.dropTable(table);
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Drops the specified database table.
     *
     * @param  table  the name of the table to drop
     */
    @Override
    public void dropDatabase(String table) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid database name: " + table);
            }

            // Check the service using
            if (sqlConnectionBridge != null) {
                sqlConnectionBridge.dropDatabase(table);
            } else if (noSQLConnectionBridge != null) {
                noSQLConnectionBridge.dropDatabase(table);
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the table name is invalid.
     *
     * @param  table  the table name to be checked
     * @return       true if the table name is invalid, false otherwise
     */
    boolean isTableNameInvalid(String table) {
        return !StringUtil.strictMatches(table, "^[a-zA-Z_][a-zA-Z0-9_]*$")
                || ( connection != null && !sqlTables.contains(table) )
                || ( mongoDB != null && mongoDB.getCollection(table) == null );
    }

    @Override
    public void syncTables() {
        // This method should only be used with SQL database.
        // MongoDB does not support this function.
        try {
            if (relationalBridge != null) {
                relationalBridge.bumpTables();
                sqlTables = relationalBridge.getTables();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the connection to the SQL or NoSQL database, depending on which bridge is set.
     */
    @Override
    public void close() {
        try {
            if (sqlConnectionBridge != null) {
                sqlConnectionBridge.close();
            } else if (noSQLConnectionBridge != null) {
                noSQLConnectionBridge.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
