package net.brydget.harmony.database;

import net.brydget.harmony.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class SQLConnectionBridge implements NormalizedDatabaseBridge {

    final Connection connection;
    public SQLConnectionBridge(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Map<String, Object>> findFrom(String table, Map<String, Object> query, int... limit) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Using a map provides us a more flexible query for SQL
            // Check the service using
            final List<Map<String, Object>> result = new ArrayList<>();
            final int queryLimit = limit.length > 0 ? Math.max(limit[0], 1) : 1;


            if (connection != null) {
                // We're using SQL. Query the database
                final String[] keySet = query.keySet().toArray(new String[0]);
                final String keys = Arrays.stream(keySet).map(a -> a + " = ?").collect(Collectors.joining(" AND "));
                final PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + keys + " LIMIT " + queryLimit);

                for (int i = 0; i < keySet.length; i++) {
                    statement.setObject(i + 1, keySet[i]);
                }

                final ResultSet resultSet = statement.executeQuery();
                // We have used a list to store the results since the query can result in multiple rows
                while (resultSet.next()) {
                    final Map<String, Object> row = new HashMap<>();
                    for (String key : query.keySet()) {
                        row.put(key, resultSet.getObject(key));
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

    @Override
    public List<Map<String, Object>> findFrom(String table, Map<String, Object> query) {
        return findFrom(table, query, 1);
    }

    @Override
    public List<Map<String, Object>> findOne(String table, Map<String, Object> query) {
        // Recycle the same method but with a limit of 1
        return findFrom(table, query);
    }

    @Override
    public void updateFor(String table, Map<String, Object> query, Map<String, Object> update) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }
            // Check the service using

            if (connection != null) {
                // We're using SQL. Query the database
                final String[] queryKeySet = query.keySet().toArray(new String[0]);
                final String[] updateKeySet = update.keySet().toArray(new String[0]);

                final String keys = Arrays.stream(queryKeySet).map(a -> a + " = ?").collect(Collectors.joining(" AND "));
                final String updates = Arrays.stream(updateKeySet).map(a -> a + " = ?").collect(Collectors.joining(", "));

                final PreparedStatement statement = connection.prepareStatement("UPDATE " + table + " WHERE " + keys + " SET " + updates);

                for (int i = 0; i < queryKeySet.length; i++) {
                    statement.setObject(i + 1, queryKeySet[i]);
                }

                for (int i = 0; i < updateKeySet.length; i++) {
                    statement.setObject(queryKeySet.length + 1 + i, updateKeySet[i]);
                }

                statement.executeUpdate();
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOne(String table, Map<String, Object> query) {
        deleteFor(table, query);
    }

    @Override
    public void deleteFor(String table, Map<String, Object> query, int... limit) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Check the service using
            final int queryLimit = limit.length > 0 ? Math.max(limit[0], 1) : 1;

            if (connection != null) {
                // We're using SQL. Query the database
                final String[] queryKeySet = query.keySet().toArray(new String[0]);

                final String keys = Arrays.stream(queryKeySet).map(a -> a + " = ?").collect(Collectors.joining(" AND "));
                final PreparedStatement statement = connection.prepareStatement("DELETE FROM " + table + " WHERE " + keys + " LIMIT " + queryLimit);

                for (int i = 0; i < queryKeySet.length; i++) {
                    statement.setObject(i + 1, queryKeySet[i]);
                }

                statement.execute();
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFor(String table, Map<String, Object> query) {
        deleteFor(table, query, 1);
    }

    @Override
    public void insertInto(String table, Map<String, Object> query) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Check the service using

            if (connection != null) {
                // We're using SQL. Query the database
                final String[] queryKeySet = query.keySet().toArray(new String[0]);

                if (Arrays.stream(queryKeySet).anyMatch(this::isTableNameInvalid)) {
                    throw new IllegalArgumentException("Invalid column name: " + table);
                }

                final String keys = String.join(", ", queryKeySet);
                final String preparedSymbols = String.join(", ", Collections.nCopies(queryKeySet.length, "?"));
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + " (" + keys + ") VALUES " + "(" + preparedSymbols + ")");

                for (int i = 0; i < queryKeySet.length; i++) {
                    statement.setObject(i + 1, queryKeySet[i]);
                }

                statement.execute();
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertInto(String table, Iterable<Map<String, Object>> query) {
        query.forEach(a -> insertInto(table, a));
    }

    @Override
    public void dropTable(String table) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Check the service using
            if (connection != null) {
                // We're using SQL. Query the database
                connection.prepareStatement("DROP TABLE " + table).execute();
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropDatabase(String table) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid database name: " + table);
            }

            // Check the service using
            if (connection != null) {
                // We're using SQL. Query the database
                connection.prepareStatement("DROP DATABASE IF EXISTS " + table).execute();
            } else {
                throw new Exception("Connection is null. Please check the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void syncTables() { /* Ignore */ }

    public List<String> syncTables(RelationalBridge bridge) {
        try {
            bridge.bumpTables();
            return bridge.getTables();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    boolean isTableNameInvalid(String table) {
        return !StringUtil.strictMatches(table, "^[a-zA-Z_][a-zA-Z0-9_]*$");
    }

}
