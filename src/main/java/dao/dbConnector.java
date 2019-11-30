package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Singleton class existing to encapsulate access to the only existing database instance.
 */
public class dbConnector
{
    private static final dbConnector DB = new dbConnector();

    private MongoClient mainMongoClient = MongoClients.create(
            "mongodb+srv://mainuser:MainUser10@maincluster-af4rk.gcp.mongodb.net/test?retryWrites=true&w=majority");
    private MongoClient testMongoClient = MongoClients.create(
            "mongodb+srv://mainuser:MainUser10@testcluster-5qtaz.gcp.mongodb.net/test?retryWrites=true&w=majority");

    private MongoDatabase database;
    private boolean usedDB = true;

    private dbConnector() {
        DB.setUsedDB(true);
    }

    public boolean getUsedDB() {
        return DB.usedDB;
    }

    /**
     * Set which database to use (main - true, test - false).
     * @param usedDB true - use main database, false - use test database
     */
    public void setUsedDB(boolean usedDB) {
        DB.usedDB = usedDB;
        if (usedDB)
            DB.database = DB.mainMongoClient.getDatabase("mainDB");
        else
            DB.database = DB.testMongoClient.getDatabase("testDB");
    }

    /**
     * Return instance of currently used database (default - main, can be changed with
     * {@link #setUsedDB(boolean)}).
     *
     * @return database instance
     */
    public static MongoDatabase getDB() {
        return DB.database;
    }
}
