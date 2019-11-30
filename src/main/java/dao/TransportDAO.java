package dao;

import model.Transaction;
import model.Transport;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class TransportDAO extends GenericDAO<Transport>{
    String collName = "transports";

    @Override
    public void delete(UUID id) {
        dbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Transport find(UUID id) {
        return dbConnector
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
        dbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Transport toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        dbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }
}
