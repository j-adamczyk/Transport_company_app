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
     * @return available vehicles
     */
    public List<Vehicle> findAllAvailableVehicles() {
        return DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .find(eq("available", true))
                .into(new ArrayList<>());
    }
}
