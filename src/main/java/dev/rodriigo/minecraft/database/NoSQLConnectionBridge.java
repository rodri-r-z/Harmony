package dev.rodriigo.minecraft.database;

import com.mongodb.client.FindIterable;
import dev.rodriigo.minecraft.util.StringUtil;
import org.bson.Document;

import java.util.*;

public class NoSQLConnectionBridge implements NormalizedDatabaseBridge {

    final MongoDBConnector mongoDB;

    public NoSQLConnectionBridge(MongoDBConnector connector) {
        mongoDB = connector;
    }

    @Override
    public List<Map<String, Object>> findFrom(String table, Map<String, Object> query, int... limit) {
        try {
            if (isTableNameInvalid(table)) {
                throw new IllegalArgumentException("Invalid table name: " + table);
            }

            // Using a map provides us a more flexible query for both SQL and MongoDB
            // Check the service using
            final List<Map<String, Object>> result = new ArrayList<>();
            final int queryLimit = limit.length > 0 ? Math.max(limit[0], 1) : 1;


            if (mongoDB != null) {
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

            if (mongoDB != null) {
                mongoDB.getCollection(table)
                        .updateMany(new Document(query), new Document(update));
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

            if (mongoDB != null) {
                if (queryLimit == 1) {
                    mongoDB.getCollection(table)
                            .deleteOne(new Document(query));
                    return;
                }

                mongoDB.getCollection(table)
                        .deleteMany(new Document(query));
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

            // Using a map provides us a more flexible query for both SQL and MongoDB
            // Check the service using
            if (mongoDB != null) {
                mongoDB.getCollection(table)
                        .insertOne(new Document(query));
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
            if (mongoDB != null) {
                mongoDB.getCollection(table).drop();
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
            if (mongoDB != null) {
                mongoDB.client.getDatabase(table).drop();
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
            if (mongoDB != null) {
                mongoDB.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void syncTables() {
        // Ignore, this is not supported here
    }

    boolean isTableNameInvalid(String table) {
        return !StringUtil.strictMatches(table, "^[a-zA-Z_][a-zA-Z0-9_]*$");
    }
}
