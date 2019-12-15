package app.dao;

import app.model.Duration;
import app.model.Vehicle;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

public class VehicleDAO extends GenericDAO<Vehicle> {
    String collName = "vehicles";

    // basic CRUD operations

    @Override
    public void delete(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .deleteOne(eq("_id", id));
    }

    @Override
    public Vehicle find(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .find(eq("_id", id))
                .first();

    }

    @Override
    public void save(Vehicle toSave) {
        DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .insertOne(toSave);
    }

    @Override
    public void update(ObjectId id, Vehicle toUpdate) {
        DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .replaceOne(
                        eq("_id", id),
                        toUpdate);
    }

    // other methods

    public List<Vehicle> findAllVehicles() {
        return DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .find()
                .into(new ArrayList<>());
    }

    public Vehicle findByRegistrationNo(String registrationNo) {
        return DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .find(eq("registrationNo", registrationNo))
                .first();
    }

    /**
     * Finds all vehicles that are currently not in the transport.
     * @return available vehicle
     */
    public List<Vehicle> findAllAvailableVehicles() {
        // suppose that all vehicles are available
        List<Vehicle> availableVehicles = findAllVehicles();

        // get drivers that are NOT available (are currently in transport)
        // it means that: departureTime < current time < departureTime + 2 * expectedTime
        // (2 * expectedTime, since vehicle has to go back)
        Bson project = project(fields(include("vehicle", "departureTime", "expectedTime")));
        List<Document> transports =
                DbConnector
                        .getDB()
                        .getCollection("transports")
                        .find(lt("departureTime", LocalDateTime.now()))
                        .projection(project)
                        .into(new ArrayList<>());

        List<Vehicle> notAvailableVehicles = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for (Document doc: transports)
        {
            LocalDateTime endTime = (LocalDateTime) doc.get("departureTime");
            Duration expectedTime = (Duration) doc.get("expectedTime");
            endTime = endTime.plusHours(2 * expectedTime.getHours());
            endTime = endTime.plusMinutes(2 * expectedTime.getMinutes());

            if (currentDateTime.isBefore(endTime))
                notAvailableVehicles.add((Vehicle) doc.get("vehicle"));
        }

        // remove not available drivers and return the rest
        availableVehicles.removeAll(notAvailableVehicles);
        return availableVehicles;
    }
}
