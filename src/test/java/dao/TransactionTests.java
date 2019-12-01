package dao;

import com.mongodb.client.MongoDatabase;
import model.Address;
import model.Company;
import model.Transaction;
import org.bson.Document;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.*;

public class TransactionTests {
    private MongoDatabase db;
    private TransactionDAO transactionDAO;
    private Transaction t1;
    private Transaction t2;

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
        Address address = new Address("kraj", "miasto", "kod", "ulica");
        Company c = new Company("imie", address,"telefon", "mail", "reprezentant");
        Map<String, Integer> cargo = new HashMap<>();
        t1 = new Transaction(c, cargo, address, address, 10.0,
                LocalDate.of(2019, 10, 10));
        t2 = new Transaction(c, cargo, address, address, 220.0,
                LocalDate.of(2000, 11, 30));
        transactionDAO = new TransactionDAO();
        transactionDAO.save(t1);
    }

    @Test
    public void testFind() {
        Assert.assertEquals(t1, transactionDAO.find(t1.get_id()));
        Assert.assertNotEquals(t2, transactionDAO.find(t1.get_id()));
    }

    @Test
    public void testUpdate(){
        t1.setMoney(40.0);
        transactionDAO.update(t1.get_id(), t1);
        Assert.assertEquals((double) transactionDAO.find(t1.get_id()).getMoney(), 40.0, 0);
        t1.setTransactionDate(LocalDate.of(1900, 10, 10));
        Assert.assertNotEquals(transactionDAO.find(t1.get_id()).getTransactionDate(), LocalDate.of(1900, 10, 10));
        transactionDAO.update(t1.get_id(), t1);
        Assert.assertEquals(transactionDAO.find(t1.get_id()).getTransactionDate(), LocalDate.of(1900, 10, 10));
    }

    @Test
    public void testFindAllTransactions(){
        List<Transaction> actual = transactionDAO.findAllTransactions();
        Assert.assertEquals(actual.size(), 1);
        Assert.assertEquals(actual, Collections.singletonList(t1));
        transactionDAO.save(t2);
        actual = transactionDAO.findAllTransactions();
        List<Transaction> expected = Arrays.asList(t1, t2);
        Assert.assertEquals(actual.size(), 2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDelete(){
        transactionDAO.delete(t2.get_id());
        Assert.assertEquals(Collections.singletonList(t1), transactionDAO.findAllTransactions());
        transactionDAO.delete(t1.get_id());
        Assert.assertTrue(transactionDAO.findAllTransactions().isEmpty());
    }

    @After
    public void cleanDatabase(){
        for (String collectionName: db.listCollectionNames())
            db.getCollection(collectionName).deleteMany(new Document());
    }
}
