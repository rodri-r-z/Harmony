package net.brydget.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RelationalBridge {

    Connection connection;
    // Database security is a very important thing
    // To keep the security, we'll bump all tables into a string list
    // This way we can get all existing tables and detect if someone
    // is trying to query an invalid table
    List<String> tables = new ArrayList<>();

    void bumpTables() throws Exception {
        if (connection == null) return;
        tables.clear();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, "%", new String[] {"TABLE"});
        while (tables.next()) {
            this.tables.add(tables.getString("TABLE_NAME"));
        }
    }


    public static RelationalBridge fromSqlite(String database) {
        try {
            // Instance the driver for older versions
            Class.forName("org.sqlite.JDBC");
        } catch (Exception ignored) {
            throw new RuntimeException("SQLite Driver is not installed! Are you using an updated version of Bukkit?");
        }

        // Constructor for SQLite
        try {
            return new RelationalBridge("jdbc:sqlite:" + database);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RelationalBridge(String host, String database, String username, String password) {
        try {
            // Instance the driver for older versions
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ignored) {
            throw new RuntimeException("MySQL Driver is not installed! Are you using an updated version of Bukkit?");
        }

        // Constructor for MySQL
        try {
            connection = DriverManager.getConnection("jdbc:mysql:" + host + "/" + database+"?autoReconnect=true", username, password);

            bumpTables();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RelationalBridge(String host, int port, String database, String username, String password) {
        try {
            // Instance the driver for older versions
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ignored) {
            throw new RuntimeException("MySQL Driver is not installed! Are you using an updated version of Bukkit?");
        }

        // Constructor for MySQL
        try {
            connection = DriverManager.getConnection("jdbc:mysql:" + host + ":" + port + "/" + database+"?autoReconnect=true", username, password);

            bumpTables();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RelationalBridge(String uri) {
        // If you want to connect your database with a JDBC URI, this method is for you
        try {
            connection = DriverManager.getConnection(uri);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public List<String> getTables() {
        return tables;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
