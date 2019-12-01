package dao;

import model.CurrentTransaction;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CurrentTransactionDAO extends GenericDAO<CurrentTransaction> {
    String collName = "currentTransactions";

    // CRUD operations

    @Override
    public void delete(ObjectId id) {
        DbConnector
                .getDB()
                .getCollection(collName, CurrentTransaction.class)
                .deleteOne(eq("_id", id));
    }

    @Override
    public CurrentTransaction find(ObjectId id) {
        return DbConnector
                .getDB()
                .getCollection(collName, CurrentTransaction.class)
                .find(
                        eq("_id", id),
                        CurrentTransaction.class)
                .first();
    }

    @Override
    public void save(CurrentTransaction toSave) {
        Document doc = Converter.toDocument(toSave);
        DbConnector
                .getDB()
                .getCollection(collName, CurrentTransaction.class)
                .insertOne(toSave);
    }

    @Override
    public void update(ObjectId id, CurrentTransaction toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName, CurrentTransaction.class)
                .replaceOne(
                        eq("_id", id),
                        toUpdate);
    }

    // other methods

    public List<CurrentTransaction> findAllCurrentTransactions() {
        return DbConnector
                .getDB()
                .getCollection(collName, CurrentTransaction.class)
                .find()
                .into(new ArrayList<>());
    }
}
