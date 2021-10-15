package org.example.learnspark.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.example.learnspark.util.Path;

public class DatabaseHelper {

    private static Datastore datastore;

    private static MongoClient mongoClient;

    private static void init() {
        initDatastore();
        assert datastore != null;
        assert mongoClient != null;
        mapEntities();
    }

    private static void initDatastore() {
        String connectionString  = String.format("mongodb://%s:%d", Path.Database.HOST, Path.Database.PORT);
        mongoClient = MongoClients.create(connectionString);
        datastore = Morphia.createDatastore(mongoClient, Path.Database.LOCAL_DBNAME);
    }

    private static void mapEntities() {
        assert datastore != null;
        datastore.getMapper().mapPackage("org.example.learnspark.domain");
        datastore.ensureIndexes();
    }

    public static Datastore getDatastore() {
        if(datastore == null)
            init();
        return datastore;
    }

}
