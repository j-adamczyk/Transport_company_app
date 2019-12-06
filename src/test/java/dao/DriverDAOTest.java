package dao;

import com.mongodb.client.MongoDatabase;
import model.Address;
import model.Driver;
import org.bson.Document;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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
        this.db = DbConnector.getDB();

        d1 = new Driver("Jan Kowalski",
                LocalDate.of(1960, 2, 20),
                LocalDate.of(2019, 10, 20),
                "123456789",
                new Address("Poland", "Krakow", "12-345", "krakowska"),
                2000.0);
        d2 = new Driver("Aleksander Poniatowski",
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
        Assert.assertEquals(d1, driverDAO.find(d1.get_id()));
        Assert.assertNotEquals(d1, driverDAO.find(d2.get_id()));
    }

    @Test
    public void testUpdate(){
        d1.setName("Aleksander Poniatowski");
        driverDAO.update(d1.get_id(), d1);
        Assert.assertEquals(driverDAO.find(d1.get_id()).getName(), d2.getName());
    }

    @Test
    public void testFindByName(){
        Assert.assertEquals(d1.get_id(), driverDAO.findByName(d1.getName()).get(0).get_id());
    }

    @Test
    public void testFindAllDrivers(){
        List<Driver> actual = driverDAO.findAllDrivers();
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Collections.singletonList(d1));
        driverDAO.save(d2);
        actual = driverDAO.findAllDrivers();
        List<Driver> expected = Arrays.asList(d1, d2);
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDelete(){
        driverDAO.delete(d2.get_id());
        Assert.assertEquals(Collections.singletonList(d1), driverDAO.findAllDrivers());
        driverDAO.delete(d1.get_id());
        Assert.assertTrue(driverDAO.findAllDrivers().isEmpty());
    }

    @After
    public void cleanDatabase() {
        // clear all collections with empty Document filter
        for (String collectionName : db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }
}