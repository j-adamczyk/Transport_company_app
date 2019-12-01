package dao;

import com.mongodb.client.MongoDatabase;
import model.Address;
import model.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void testAFind() {
        Assert.assertTrue(d1.equals(driverDAO.find(d1.get_id())));
    }

    @Test
    public void testBUpdate(){
        d1.setName("Aleksander Poniatowski");
        driverDAO.update(d1.get_id(), d1);
        Assert.assertTrue(driverDAO.find(d1.get_id()).getName().equals(d2.getName()));
    }

    @Test
    public void testCFindByName(){
        Assert.assertEquals(d1.get_id(), driverDAO.findByName(d1.getName()).get(0).get_id());
    }

    @Test
    public void testDFindAllDrivers(){
        driverDAO.save(d2);
        List<Driver> actual = driverDAO.findAllDrivers();
        List<Driver> expected = Arrays.asList(d1, d2);
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testEDelete(){
        driverDAO.delete(d2.get_id());
        Assert.assertEquals(Arrays.asList(d1), driverDAO.findAllDrivers());
    }

    @After
    public void cleanDatabase() {
        this.db = DbConnector.getDB();
        // clear all collections with empty Document filter
        for (String collectionName : db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }
}