package dao;

import app.dao.CompanyDAO;
import app.dao.DbConnector;
import com.mongodb.client.MongoDatabase;
import app.model.Address;
import app.model.Company;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CompanyDAOTests {
    MongoDatabase db;
    CompanyDAO companyDAO;
    Company c1;
    Company c2;

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
        db = DbConnector.getDB();
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }

    @Before
    public void setupDatabase() {
        this.db = DbConnector.getDB();
        c1 = new Company("Company1", new Address("Poland", "Krakow", "33-333", "Krakowska 17"),
                "444555666", "xxx@xxx.pl", "Adam Kowalski");
        c2 = new Company("Company2", new Address("Germany", "Berlin", "G33-333", "Gute Strasse 88"),
                "123123123", "www@xxx.pl", "Bernard Krausse");
        companyDAO = new CompanyDAO();
        companyDAO.save(c1);
        companyDAO.save(c2);
    }

    @After
    public void cleanDatabase() {
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }


    @Test
    public void companyDeleteTest() {
        companyDAO.delete(c1.get_id());
        List<Company> allCompanies = companyDAO.findAllCompanies();
        assertEquals(allCompanies.size(), 1);
        assertTrue(allCompanies.contains(c2));
        assertFalse(allCompanies.contains(c1));
    }

    @Test
    public void companyFindTest() {
        Company found = companyDAO.find(c1.get_id());
        assertEquals(found, c1);
    }

    @Test
    public void companyUpdateTest() {
       c1.setName("Changed name.");
        companyDAO.update(c1.get_id(), c1);
        Company foundCompany = companyDAO.find(c1.get_id());
        assertEquals(foundCompany.getName(), "Changed name.");
    }
    @Test
    public void findAllCompaniesTest() {
        List<Company> allCompanies = companyDAO.findAllCompanies();
        assertEquals(allCompanies.size(), 2);
        assertTrue(allCompanies.contains(c2));
        assertTrue(allCompanies.contains(c1));
    }

    @Test
    public void findByName() {
        List<Company> foundCompany = companyDAO.findByName("Company1");
        assertEquals(foundCompany.size(), 1);
        assertTrue(foundCompany.contains(c1));
    }

    @Test
    public void findCompaniesFromCityTest() {
        Company c3 = new Company("Company3", new Address("Germany", "Berlin", "G33-333", "Gute Strasse 88"),
                "123123123", "www@xxx.pl", "Bernard Krausse");
        companyDAO.save(c3);
        List<Company> foundCompany = companyDAO.findCompaniesFromCity("Berlin");
        assertEquals(foundCompany.size(), 2);
        assertTrue(foundCompany.contains(c2));
        assertTrue(foundCompany.contains(c3));
    }

    @Test
    public void findCompaniesFromCountryTest() {
        Company c3 = new Company("Company3", new Address("Germany", "Berlin", "G33-333", "Gute Strasse 88"),
                "123123123", "www@xxx.pl", "Bernard Krausse");
        companyDAO.save(c3);
        List<Company> foundCompany = companyDAO.findCompaniesFromCountry("Germany");
        assertEquals(foundCompany.size(), 2);
        assertTrue(foundCompany.contains(c2));
        assertTrue(foundCompany.contains(c3));
    }
}
