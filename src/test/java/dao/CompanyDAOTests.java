package dao;

import com.mongodb.client.MongoDatabase;
import model.Address;
import model.Company;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompanyDAOTests {

    MongoDatabase db;
    Company c1;

    @BeforeClass
    public void testConnection()
    {
        DbConnector.getInstance().setDbType(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
        this.db = DbConnector.getDB();
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }

    @Before
    public void setupDatabase()
    {
        c1 = new Company("Company1", new Address("Poland", "Krakow", "33-333", "Krakowska 17"),
                "444555666", "xxx@xxx.pl", "Adam Kowalski");

    }

    @After
    public void cleanDatabase(){
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }

    @Test
    public void tmp()
    {
    }


}
