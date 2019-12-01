package dao;

import com.mongodb.client.MongoDatabase;
import model.Address;
import model.Vehicle;
import org.bson.Document;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehicleDAOTests {
    private MongoDatabase db;
    private VehicleDAO vehicleDAO;
    private Vehicle v1;
    private Vehicle v2;

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
    }

    @Before
    public void setupDatabase() {
        v1 = new Vehicle("bmw", "123",
                LocalDate.of(2010, 10, 10), 1000.0, 1000.0);
        v2 = new Vehicle("audi", "234",
                LocalDate.of(2018, 2, 2), 200.0, 200.0);
        vehicleDAO = new VehicleDAO();
        vehicleDAO.save(v1);
    }

    @Test
    public void testAFind() {
        Assert.assertEquals(v1, vehicleDAO.find(v1.get_id()));
        Assert.assertNotEquals(v2, vehicleDAO.find(v1.get_id()));
    }

    @Test
    public void testBUpdate(){
        v1.setModel("ferrari");
        vehicleDAO.update(v1.get_id(), v1);
        Assert.assertEquals(vehicleDAO.find(v1.get_id()).getModel(), "ferrari");
        v1.setCargoVolume(10.0);
        vehicleDAO.update(v1.get_id(), v1);
        Assert.assertEquals((double) vehicleDAO.find(v1.get_id()).getCargoVolume(), 10.0, 0);
    }

    @Test
    public void testCFindAllVehicles(){
        List<Vehicle> actual = vehicleDAO.findAllVehicles();
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Collections.singletonList(v1));
        vehicleDAO.save(v2);
        actual = vehicleDAO.findAllVehicles();
        List<Vehicle> expected = Arrays.asList(v1, v2);
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDFindVehicleModel(){
        //todo
    }

    @Test
    public void testEDelete(){
        vehicleDAO.delete(v2.get_id());
        Assert.assertEquals(Arrays.asList(v1), vehicleDAO.findAllVehicles());
        vehicleDAO.delete(v1.get_id());
        Assert.assertTrue(vehicleDAO.findAllVehicles().isEmpty());
    }

    @After
    public void cleanDatabase(){
        this.db = DbConnector.getDB();
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }
}
