package log;

import app.dao.DbConnector;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LoggerTests {
    MongoDatabase db = DbConnector.getDB();

    @BeforeClass
    public static void testConnection() {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        MongoDatabase db = DbConnector.getDB();
        // will throw an exception if connection could not be made (= db is null)
        db.getName();
    }

    @Before
    public void setupDatabase() {
        DbConnector.getInstance().setDbTypeAndLoad(false);
        this.db = DbConnector.getDB();
    }

    @Test
    public void testLogging() {
        LogEntry logEntry = new LogEntry(EntryType.CREATE, "someData");
        Logger.log(logEntry);

        assertNotNull(Logger.find(logEntry.get_id()));
    }
}
