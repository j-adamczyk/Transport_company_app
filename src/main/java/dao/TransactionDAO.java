package dao;

import model.Transaction;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class TransactionDAO extends GenericDAO<Transaction>{
    String collName = "transactions";

    @Override
    public void delete(UUID id) {
        dbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Transaction find(UUID id) {
        return dbConnector
                .getDB()
                .getCollection(collName)
                .find(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        Transaction.class)
                .first();
    }

    @Override
    public void save(Transaction toSave) {
        Document doc = Converter.toDocument(toSave);
        dbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Transaction toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        dbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }
}
