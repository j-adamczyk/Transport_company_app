package app.dao;

import app.model.Driver;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

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
}
