package dev.rodriigo.minecraft.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class RelationalBridge {

    static Connection connection;

    public RelationalBridge(String database) {
        try {
            // Instance the driver for older versions
            Class.forName("org.sqlite.JDBC");
        } catch (Exception ignored) {
            throw new RuntimeException("SQLite Driver is not installed! Are you using an updated version of Bukkit?");
        }

        // Constructor for SQLite
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + database);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RelationalBridge() {
        // Check if the connection is null
        // This method should only be instanced without parameters
        // If you used the "fromUri" method

        if (connection == null) {
            throw new RuntimeException("Connection is null! You're probably using the wrong constructor.");
        }
    }

    public static RelationalBridge fromUri(String uri) {
        // If you want to connect your database with a JDBC URI, this method is for you
        try {
            connection = DriverManager.getConnection(uri);
            return new RelationalBridge();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RelationalBridge fromUri(String uri, String user, String password) {
        // If you want to connect your database with a JDBC URI, but also with username and password
        // this method is for you
        try {
            connection = DriverManager.getConnection(uri, user, password);
            return new RelationalBridge();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Connection getConnection() {
        return connection;
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
