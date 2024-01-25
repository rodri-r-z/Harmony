package dev.rodriigo.minecraft.database;

import java.util.List;
import java.util.Map;

public interface NormalizedDatabaseBridge {

    List<Map<String, Object>> findFrom(String table, Map<String, Object> query, int... limit);
    List<Map<String, Object>> findFrom(String table, Map<String, Object> query);
    List<Map<String, Object>> findOne(String table, Map<String, Object> query);
    void updateFor(String table, Map<String, Object> query, Map<String, Object> update);
    void deleteOne(String table, Map<String, Object> query);
    void deleteFor(String table, Map<String, Object> query, int... limit);
    void deleteFor(String table, Map<String, Object> query);
    void insertInto(String table, Map<String, Object> query);
    void insertInto(String table, Iterable<Map<String, Object>> query);
    void dropTable(String table);
    void dropDatabase(String table);

}
