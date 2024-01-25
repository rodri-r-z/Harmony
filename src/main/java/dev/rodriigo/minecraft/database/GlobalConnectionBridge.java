package dev.rodriigo.minecraft.database;

import com.mongodb.client.FindIterable;
import dev.rodriigo.minecraft.BackendPlugin;
import org.bson.Document;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GlobalConnectionBridge {
    // This class is used to create a connection to the database
    // This will support both SQL and MongoDB and will load the correct driver
    // based on a configuration file

    Connection connection;
    MongoDBConnector mongoDB;
    BackendPlugin backendPlugin = BackendPlugin.getInstance();


    public GlobalConnectionBridge(File configFile) throws Exception {
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
        } else if (driver.equalsIgnoreCase("sqlite")) {
            // Create a connection to SQLite
            connection = new RelationalBridge(
                    backendPlugin.getDataFolder()
                            .toPath()
                            .resolve(database)
                            .toString()
            ).getConnection();
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
            connection = new RelationalBridge(
                    host,
                    port,
                    database,
                    username,
                    password
            ).getConnection();
        } else {
            throw new Exception("Database Driver is not supported. Please check the file.");
        }
    }

    public List<Map<String, Object>> findFrom(String table, Map<String, Object> query, int... limit) {
        try {
            // Using a map provides us a more flexible query for both SQL and MongoDB
            // Check the service using
            final List<Map<String, Object>> result = new ArrayList<>();
            final int queryLimit = limit.length > 0 ? Math.max(limit[0], 1) : 1;

            if (connection != null) {
                // We're using SQL. Query the database
                final String keys = query.keySet().stream().map(a -> a + " = ?").collect(Collectors.joining(" AND "));
                final PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + keys+" LIMIT " + queryLimit);

                 final ResultSet resultSet = statement.executeQuery();
                 // We have used a list to store the results since the query can result in multiple rows
                 while (resultSet.next()) {
                     final Map<String, Object> row = new HashMap<>();
                     for (String key : query.keySet()) {
                         row.put(key, resultSet.getObject(key));
                     }
                     result.add(row);
                 }
            } else if (mongoDB != null) {
                final FindIterable<Document> documents = mongoDB.getCollection(table)
                        .find(new Document(query))
                        .limit(queryLimit);

                for (Document document : documents) {
                    final Map<String, Object> row = new HashMap<>();
                    for (String key : query.keySet()) {
                        row.put(key, document.get(key));
                    }
                    result.add(row);
                }
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, Object>> findOne(String table, Map<String, Object> query) {
        // Recycle the same method but with a limit of 1
        return findFrom(table, query, 1);
    }

    public void updateFor(String table, Map<String, Object> query, Map<String, Object> update) {
        try {
            // Using a map provides us a more flexible query for both SQL and MongoDB
            // Check the service using
            final List<Map<String, Object>> result = new ArrayList<>();

            if (connection != null) {
                // We're using SQL. Query the database
                final String keys = query.keySet().stream().map(a -> a + " = ?").collect(Collectors.joining(" AND "));
                final String updates = update.keySet().stream().map(a -> a + " = ?").collect(Collectors.joining(", "));
                final PreparedStatement statement = connection.prepareStatement("UPDATE " + table + " WHERE " + keys+" SET "+updates);

                statement.executeUpdate();
            } else if (mongoDB != null) {
                mongoDB.getCollection(table)
                        .updateMany(new Document(query), new Document(update));
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
