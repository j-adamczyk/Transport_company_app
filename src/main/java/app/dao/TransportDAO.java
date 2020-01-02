package app.dao;

import app.model.Transport;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class TransportDAO extends GenericDAO<Transport>{
    String collName = "transports";

    // basic CRUD operations

    @Override
    public void delete(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection(collName, Transport.class)
                .deleteOne(eq("_id", id));
    }

    @Override
    public Transport find(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, Transport.class)
                .find(eq("_id", id))
                .first();
    }

    @Override
    public void save(Transport toSave) {
        DbConnector
                .getDB()
                .getCollection(collName, Transport.class)
                .insertOne(toSave);
    }

    @Override
    public void update(ObjectId id, Transport toUpdate) {
        DbConnector
                .getDB()
                .getCollection(collName, Transport.class)
                .replaceOne(
                        eq("_id", id),
                        toUpdate);
    }

    // other methods

    public List<Transport> findAllTransports() {
        return DbConnector
                .getDB()
                .getCollection(collName, Transport.class)
                .find()
                .into(new ArrayList<>());
    }

    public List<Transport> findByCurrentTransaction(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, Transport.class)
                .find(eq("currentTransaction._id", id))
                .into(new ArrayList<>());
    }
}
