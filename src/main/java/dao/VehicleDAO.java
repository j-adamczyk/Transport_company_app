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
    public void delete(UUID id) {
        DbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Vehicle find(UUID id) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        Vehicle.class)
                .first();

    }

    @Override
    public void save(Vehicle toSave) {
        Document doc = Converter.toDocument(toSave);
        DbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Vehicle toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }

    // other methods

    public List<Vehicle> findAllVehicles() {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(Vehicle.class)
                .into(new ArrayList<>());
    }

    public List<Vehicle> findAvailableVehicles() {
        ArrayList<Vehicle> inUse = DbConnector
                .getDB()
                .getCollection("transports")
                .find(Vehicle.class)
                .projection(fields(include("vehicle"), excludeId()))
                .into(new ArrayList<>());

        Set<Vehicle> allVehicles = new HashSet<>(findAllVehicles());
        allVehicles.removeAll(inUse);

        return new ArrayList<>(allVehicles);
    }
}
