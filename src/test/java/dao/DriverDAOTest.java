package dao;

import com.mongodb.client.MongoDatabase;
import jdk.vm.ci.meta.Local;
import model.Address;
import model.Driver;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class DriverDAOTest {
    private MongoDatabase db;
    private DriverDAO driverDAO;
    private Driver d1;

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbType(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
    }

    @Before
    public void setupDatabase() {
        this.db = DbConnector.getDB();
        // clear all collections with empty Document filter
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
        d1 = new Driver("Jan Kowalski",
                LocalDate.of(1960, 2, 20),
                LocalDate.of(2019, 10, 20),
                "123456789",
                new Address("Poland", "Krakow", "12-345", "krakowska"),
                2000.0);
        driverDAO = new DriverDAO();
        driverDAO.save(d1);
    }

    @Test
    public void testFind(){
//        Assert.assertEquals(d1, driverDAO.find(d1.get_id()));
    }

}
