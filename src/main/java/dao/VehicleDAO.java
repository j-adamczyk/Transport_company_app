package dao;

import model.Transport;
import model.Vehicle;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class VehicleDAO extends GenericDAO<Vehicle> {
    String collName = "vehicles";

    @Override
    public void delete(UUID id) {
        dbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Vehicle find(UUID id) {
        return dbConnector
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
        dbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Vehicle toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        dbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }
}
