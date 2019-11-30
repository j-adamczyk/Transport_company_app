package dao;

import model.CurrentTransaction;
import model.Driver;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class CurrentTransactionDAO extends GenericDAO<CurrentTransaction> {
    String collName = "currentTransactions";

    @Override
    public void delete(UUID id) {
        dbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public CurrentTransaction find(UUID id) {
        return dbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        CurrentTransaction.class)
                .first();
    }

    @Override
    public void save(CurrentTransaction toSave) {
        Document doc = Converter.toDocument(toSave);
        dbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, CurrentTransaction toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        dbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }
}
