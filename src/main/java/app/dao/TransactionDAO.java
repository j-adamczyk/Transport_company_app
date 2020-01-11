package app.dao;

import app.model.Transaction;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class TransactionDAO extends GenericDAO<Transaction>{
    String collName = "transactions";

    // basic CRUD operations

    @Override
    public void delete(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection(collName, Transaction.class)
                .deleteOne(eq("_id", id));
    }

    @Override
    public Transaction find(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, Transaction.class)
                .find(eq("_id", id))
                .first();
    }

    @Override
    public void save(Transaction toSave) {
        DbConnector
                .getDB()
                .getCollection(collName, Transaction.class)
                .insertOne(toSave);
    }

    @Override
    public void update(ObjectId id, Transaction toUpdate) {
        DbConnector
                .getDB()
                .getCollection(collName, Transaction.class)
                .replaceOne(
                        eq("_id", id),
                        toUpdate);
    }

    // other methods

    public List<Transaction> findAllTransactions() {
        return DbConnector
                .getDB()
                .getCollection(collName, Transaction.class)
                .find()
                .into(new ArrayList<>());
    }

    public List<Transaction> findAllUndoneTransactions() {
        return DbConnector
                .getDB()
                .getCollection(collName, Transaction.class)
                .find(eq("done", false))
                .into(new ArrayList<>());
    }
}
