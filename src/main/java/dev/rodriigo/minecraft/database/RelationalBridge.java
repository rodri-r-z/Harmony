package dev.rodriigo.minecraft.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class RelationalBridge {

    Connection connection;

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
