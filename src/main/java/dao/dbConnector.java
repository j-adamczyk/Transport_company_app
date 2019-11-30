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

    private MongoDatabase mainDatabase;
    private MongoDatabase testDatabase;

    private dbConnector() {
    }

    /**
     * Return instance of appropriate database.
     *
     * @param mainCluster if true, return main database instance; else return test database instance
     * @return database instance
     */
    public static MongoDatabase getDB(boolean mainCluster) {
        if (mainCluster) {
            if (DB.mainDatabase == null)
                DB.mainDatabase = DB.mainMongoClient.getDatabase("mainDB");

            return DB.mainDatabase;
        }
        else {
            if (DB.testDatabase == null)
                DB.testDatabase = DB.mainMongoClient.getDatabase("testDB");

            return DB.testDatabase;
        }
    }
}
