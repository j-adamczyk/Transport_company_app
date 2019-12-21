package app.log;

import app.dao.DbConnector;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

/**
 * Logging class, should contain only static methods for logging and reading logs.
 */
public class Logger {
    public static void log(LogEntry entry) {
        DbConnector
                .getDB()
                .getCollection("log", LogEntry.class)
                .insertOne(entry);
    }

    public static void find(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection("log", LogEntry.class)
                .find(eq("_id", id))
                .first();
    }
}
