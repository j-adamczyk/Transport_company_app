package app.dao;

import app.model.Driver;
import app.model.Duration;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class DriverDAO extends GenericDAO<Driver> {
    String collName = "drivers";

    // basic CRUD operations

    @Override
    public void delete(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection(collName, Driver.class)
                .deleteOne(eq("_id", id));
    }

    @Override
    public Driver find(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, Driver.class)
                .find(
                        eq("_id", id),
                        Driver.class)
                .first();
    }

    @Override
    public void save(Driver toSave) {
        DbConnector
                .getDB()
                .getCollection(collName, Driver.class)
                .insertOne(toSave);
    }

    @Override
    public void update(ObjectId id, Driver toUpdate) {
        DbConnector
                .getDB()
                .getCollection(collName, Driver.class)
                .replaceOne(
                        eq("_id", id),
                        toUpdate);
    }

    // other methods

    public List<Driver> findAllDrivers() {
        return DbConnector
                .getDB()
                .getCollection(collName, Driver.class)
                .find()
                .into(new ArrayList<>());
    }

    public List<Driver> findByName(String name) {
        return DbConnector
                .getDB()
                .getCollection(collName, Driver.class)
                .find(eq("name", name))
                .into(new ArrayList<>());
    }

    /**
     * Finds all drivers that are currently not in the transport.
     * @return available drivers
     */
    public List<Driver> findAllAvailableDrivers() {
        // suppose that all drivers are available
        List<Driver> availableDrivers = findAllDrivers();

        // get drivers that are NOT available (are currently in transport)
        // it means that: departureTime < current time < departureTime + 2 * expectedTime
        // (2 * expectedTime, since driver has to go back)
        Bson project = project(fields(include("driver", "departureTime", "expectedTime")));
        List<Document> transports =
                DbConnector
                        .getDB()
                        .getCollection("transports")
                        .find(lt("departureTime", LocalDateTime.now()))
                        .projection(project)
                        .into(new ArrayList<>());

        List<Driver> notAvailableDrivers = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for (Document doc: transports)
        {
            LocalDateTime endTime = (LocalDateTime) doc.get("departureTime");
            Duration expectedTime = (Duration) doc.get("expectedTime");
            endTime = endTime.plusHours(2 * expectedTime.getHours());
            endTime = endTime.plusMinutes(2 * expectedTime.getMinutes());

            if (currentDateTime.isBefore(endTime))
                notAvailableDrivers.add((Driver) doc.get("driver"));
        }

        // remove not available drivers and return the rest
        availableDrivers.removeAll(notAvailableDrivers);
        return availableDrivers;
    }
}
