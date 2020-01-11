package dao;

import app.dao.DbConnector;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class DbConnectorTests {
    MongoDatabase db;

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
    }

    @After
    public void setupDatabase() {
        DbConnector.getInstance().setDbTypeAndLoad(true);
        this.db = DbConnector.getDB();
        // clear all collections with empty Document filter
        for (String collectionName: db.listCollectionNames())
            db.getCollection("transports").deleteMany(new Document());
    }

    @Test
    public void test(){

    }
}
