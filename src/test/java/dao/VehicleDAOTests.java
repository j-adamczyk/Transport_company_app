package dao;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class VehicleDAOTests {
    private MongoDatabase db;

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbType(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
    }

    @Before
    public void setupDatabase() {
    }

    @Test
    public void testFind(){

    }


    @After
    public void cleanDatabase(){
        this.db = DbConnector.getDB();
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }
}
