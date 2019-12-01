package dao;

import model.Company;
import model.Transaction;
import model.Transport;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
