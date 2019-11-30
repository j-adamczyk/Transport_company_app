package dao;

import model.Company;
import model.Transaction;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class TransactionDAO extends GenericDAO<Transaction>{
    String collName = "transactions";

    // basic CRUD operations

    @Override
    public void delete(UUID id) {
        DbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public Transaction find(UUID id) {
        return DbConnector
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
        DbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, Transaction toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }

    // other methods

    public List<Transaction> findAllTransactions() {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(Transaction.class)
                .into(new ArrayList<>());
    }
}
