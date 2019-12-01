package dao;

import com.mongodb.client.MongoDatabase;
import model.*;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TransportDAOTests {

    MongoDatabase db;
    Transport t1;
    Transport t2;
    TransportDAO transportDAO;
    Address destination = new Address("Poland", "Krakow", "33-333", "Krakowska 17");
    Company company = new Company("Company1", destination,"444555666", "xxx@xxx.pl", "Adam Kowalski");
    Address from =  new Address("Germany", "Berlin", "G33-333", "Gute Strasse 88");
    Driver driver = new Driver("Jan Kowalski", LocalDate.of(1960, 2, 20),
                LocalDate.of(2019, 10, 20),"123456789",
                new Address("Poland", "Krakow", "12-345", "krakowska"),2000.0);
    Vehicle vehicle = new Vehicle("Honda", "XXXXXXX", LocalDate.of(1999, 03, 26), 6.0, 70.0);


    Map<String, Integer> cargo = new HashMap<>();
    Map<String, Integer> cargoLeft = new HashMap<>();

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
        cargoLeft.put("Carbon", 150);
        t1 = new Transport(new CurrentTransaction(new Transaction(company, cargo, from, destination, 2000.0, LocalDate.of(2019, 2, 8)),
                cargoLeft), driver, vehicle, 1, LocalDateTime.of(2019, 2, 12, 15, 0), new Duration(10, 0));
        t2 = new Transport(new CurrentTransaction(new Transaction(company, cargo, from, destination, 2000.0, LocalDate.of(2019, 2, 8)),
                cargoLeft), driver, vehicle, 1, LocalDateTime.of(2019, 2, 17, 15, 0), new Duration(10, 0));

        transportDAO = new TransportDAO();
        transportDAO.save(t1);
        transportDAO.save(t2);

    }

    @After
    public void cleanDatabase(){
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }

    @Test
    public void transportDeleteTest()
    {
        transportDAO.delete(t1.get_id());
        List<Transport> allTransports = transportDAO.findAllTransports();
        for(Transport t : allTransports){
            System.out.println(t);
        }
        assertEquals(allTransports.size(), 1);
        assertTrue(allTransports.contains(t2));
        assertFalse(allTransports.contains(t1));
    }

    @Test
    public void transportFindTest(){
        Transport found = transportDAO.find(t1.get_id());
        assertEquals(found, t1);
    }

    @Test
    public void transportUpdateTest(){
        t1.setDepartureDate(LocalDateTime.of(2019, 2, 7, 13, 0));
        transportDAO.update(t1.get_id(), t1);
        Transport foundTransport = transportDAO.find(t1.get_id());
        assertEquals(foundTransport.getDepartureDate(), (LocalDateTime.of(2019, 2, 7, 13, 0)));
    }

    @Test
    public void findAllTransportsTest(){
        List<Transport> allTransports = transportDAO.findAllTransports();
        assertEquals(allTransports.size(), 2);
        assertTrue(allTransports.contains(t2));
        assertTrue(allTransports.contains(t1));
    }

}
