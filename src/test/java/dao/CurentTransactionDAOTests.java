package dao;

import app.dao.CurrentTransactionDAO;
import app.dao.DbConnector;
import app.model.*;
import com.mongodb.client.MongoDatabase;
import app.model.*;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CurentTransactionDAOTests {

    MongoDatabase db;
    CurrentTransactionDAO currentTransactionDAO;

    Map<String, Integer> cargo = new HashMap<>();
    Map<String, Integer> cargoLeft1 = new HashMap<>();
    Map<String, Integer> cargoLeft2 = new HashMap<>();
    Address destination = new Address("Poland", "Krakow", "33-333", "Krakowska 17");
    Company company = new Company("Company1", destination,"444555666", "xxx@xxx.pl", "Adam Kowalski");
    Address from =  new Address("Germany", "Berlin", "G33-333", "Gute Strasse 88");
    Driver driver = new Driver("Jan Kowalski", LocalDate.of(1960, 2, 20),
            LocalDate.of(2019, 10, 20),"123456789",
            new Address("Poland", "Krakow", "12-345", "krakowska"),2000.0);
    Cargo carbon = new Cargo("carbon", 100.0, 1000.0);
    Map<String, Cargo> cargo_map = new HashMap<>();
    Transaction transaction1 = new Transaction(company, cargo_map,cargo, from, destination, 2000.0, LocalDate.of(2019, 2, 8));
    Transaction transaction2 = new Transaction(company, cargo_map,cargo, destination, from, 5000.0, LocalDate.of(2019, 2, 8));
    CurrentTransaction ct1;
    CurrentTransaction ct2;





    @BeforeClass
    public static void testConnection()
    {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
        db = DbConnector.getDB();
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }

    @Before
    public void setupDatabase()
    {
        this.db = DbConnector.getDB();
        cargo.put("Carbon", 200);
        cargoLeft1.put("Carbon", 150);
        cargoLeft2.put("Carbon", 50);
        ct1 = new CurrentTransaction(transaction1, cargoLeft1);
        ct2 = new CurrentTransaction(transaction2, cargoLeft2);
        currentTransactionDAO = new CurrentTransactionDAO();
        currentTransactionDAO.save(ct1);
        currentTransactionDAO.save(ct2);
        cargo_map.put("Carbon", carbon);

    }

    @After
    public void cleanDatabase(){
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }

    @Test
    public void currentTransactionDeleteTest()
    {
        currentTransactionDAO.delete(ct1.get_id());
        List<CurrentTransaction> allTransports = currentTransactionDAO.findAllCurrentTransactions();
        assertEquals(allTransports.size(), 1);
        assertTrue(allTransports.contains(ct2));
        assertFalse(allTransports.contains(ct1));
    }

    @Test
    public void currentTransactionFindTest(){
        CurrentTransaction found = currentTransactionDAO.find(ct1.get_id());
        assertEquals(found, ct1);
    }

    @Test
    public void setCurrentTransactionUpdateTest(){
        ct1.subtractCargo("Carbon", 100);
        currentTransactionDAO.update(ct1.get_id(), ct1);
        CurrentTransaction found = currentTransactionDAO.find(ct1.get_id());
        HashMap<String, Integer> newLeft = new HashMap<>();
        newLeft.put("Carbon", 50);
        assertEquals(found.getCargoLeft(), newLeft);
    }

    @Test
    public void findAllTransportsTest(){
        List<CurrentTransaction> allTransports = currentTransactionDAO.findAllCurrentTransactions();
        assertEquals(allTransports.size(), 2);
        assertTrue(allTransports.contains(ct2));
        assertTrue(allTransports.contains(ct1));
    }
}
