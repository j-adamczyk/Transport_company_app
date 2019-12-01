package dao;

import model.Vehicle;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

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

    public List<Vehicle> findAvailableVehicles() {
        ArrayList<Vehicle> inUse = DbConnector
                .getDB()
                .getCollection(collName, Vehicle.class)
                .find()
                .projection(fields(include("vehicle"), excludeId()))
                .into(new ArrayList<>());

        Set<Vehicle> allVehicles = new HashSet<>(findAllVehicles());
        allVehicles.removeAll(inUse);

        return new ArrayList<>(allVehicles);
    }
}
