package dao;

import model.Company;
import model.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class DriverDAO extends GenericDAO<Driver> {
    String collName = "drivers";

    // basic CRUD operations

    @Override
    public void delete(UUID id) {
        DbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Driver find(UUID id) {
        return DbConnector
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
        DbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Driver toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }

    // other methods

    public List<Driver> findAllDrivers() {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(Driver.class)
                .into(new ArrayList<>());
    }

    public List<Driver> findByName(String name) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("name", name),
                        Driver.class)
                .into(new ArrayList<>());
    }
}
