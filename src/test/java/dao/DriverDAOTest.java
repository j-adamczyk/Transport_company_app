package dao;

import com.mongodb.client.MongoDatabase;
import model.Address;
import model.Driver;
import org.bson.Document;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DriverDAOTest {
    private MongoDatabase db;
    private DriverDAO driverDAO;
    private Driver d1;
    private Driver d2;

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
    }

    @Before
    public void setupDatabase() {
        d1 = new Driver("Jan Kowalski",
                LocalDate.of(1960, 2, 20),
                LocalDate.of(2019, 10, 20),
                "123456789",
                new Address("Poland", "Krakow", "12-345", "krakowska"),
                2000.0);
        d2 = new Driver("Jakub Adamczyk",
                LocalDate.of(1960, 2, 20),
                LocalDate.of(2019, 10, 20),
                "123456789",
                new Address("Poland", "Krakow", "12-345", "krakowska"),
                2000.0);
        driverDAO = new DriverDAO();
        driverDAO.save(d1);
    }

    @Test
    public void testFind() {
        Assert.assertTrue(d1.equals(driverDAO.find(d1.get_id())));
    }

    @Test
    public void testUpdate(){
        driverDAO.update(d1.get_id(), d2);
        //Assert.assertTrue(driverDAO.find(d1.get_id()).equals(d2));
    }

    @Test
    public void findByName(){
        Assert.assertEquals(d1.get_id(), driverDAO.findByName(d1.getName()).get(0).get_id());
    }

    @Test
    public void findAllDrivers(){
        driverDAO.save(d2);
        List<Driver> actual = driverDAO.findAllDrivers();
        List<Driver> expected = Arrays.asList(d1, d2);
        Assert.assertEquals(actual.size(), 2);
        //Assert.assertEquals(actual, expected);
    }

    @After
    public void cleanDatabase() {
        this.db = DbConnector.getDB();
        // clear all collections with empty Document filter
        for (String collectionName : db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }
}