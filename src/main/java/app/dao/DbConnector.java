package app.dao;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Singleton class existing to encapsulate access to the only existing database instance.
 */
public class DbConnector
{
    private static final DbConnector DB = new DbConnector();
    private MongoDatabase database;
    private boolean dbType = true;

    private MongoClient mainMongoClient = MongoClients.create(
            "mongodb+srv://mainuser:MainUser10@maincluster-af4rk.gcp.mongodb.net/test?retryWrites=true&w=majority");
    private MongoClient testMongoClient = MongoClients.create(
            "mongodb+srv://mainuser:MainUser10@testcluster-5qtaz.gcp.mongodb.net/test?retryWrites=true&w=majority");

    private CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

    private DbConnector() {}

    public static DbConnector getInstance() {
        return DB;
    }

    public boolean getDbType() {
        return DB.dbType;
    }

    /**
     * Set which database to use (main - true, test - false).
     * @param dbType true - use main database, false - use test database
     */
    public void setDbTypeAndLoad(boolean dbType) {
        DB.dbType = dbType;
        if (dbType)
            DB.database = DB.mainMongoClient.getDatabase("mainDB");
        else
            DB.database = DB.testMongoClient.getDatabase("testDB");
    }

    /**
     * Return instance of currently used database (default - main, can be changed with
     * {@link #setDbTypeAndLoad(boolean)}).
     *
     * @return database instance
     */
    public static MongoDatabase getDB() {
        if (DB.database == null)
        {
            if (DB.dbType)
                DB.setDbTypeAndLoad(true);
            else
                DB.setDbTypeAndLoad(false);
        }
        return DB.database.withCodecRegistry(DB.pojoCodecRegistry);
    }
}
