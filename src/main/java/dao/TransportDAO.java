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
    public void delete(UUID id) {
        DbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Transport find(UUID id) {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        Transport.class)
                .first();
    }

    @Override
    public void save(Transport toSave) {
        Document doc = Converter.toDocument(toSave);
        DbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Transport toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }

    // other methods

    public List<Transport> findAllTransports() {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(Transport.class)
                .into(new ArrayList<>());
    }
}
