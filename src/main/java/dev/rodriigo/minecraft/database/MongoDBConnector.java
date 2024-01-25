package dev.rodriigo.minecraft.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class MongoDBConnector {

    MongoClient client;
    MongoDatabase database;
    Map<String, MongoCollection<Document>> collections = new HashMap<>();

    public MongoDBConnector(String uri) {
        client = MongoClients.create(uri);
    }

    public MongoDatabase setDatabase(String name) {
        database = client.getDatabase(name);
        return database;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getCollection(String name) {
        if (!collections.containsKey(name)) {
            collections.put(name, database.getCollection(name));
        }
        return collections.get(name);
    }

    public void unloadCollection(String name) {
        collections.remove(name);
    }

    public void close() {
        client.close();
    }
}
