package dao;

import model.Driver;
import model.Transaction;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class DriverDAO extends GenericDAO<Driver> {
    String collName = "drivers";

    @Override
    public void delete(UUID id) {
        dbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Driver find(UUID id) {
        return dbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        Driver.class)
                .first();
    }

    @Override
    public void save(Driver toSave) {
        Document doc = Converter.toDocument(toSave);
        dbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Driver toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        dbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }
}
