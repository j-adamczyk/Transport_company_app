package dao;

import model.Company;
import model.CurrentTransaction;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class CurrentTransactionDAO extends GenericDAO<CurrentTransaction> {
    String collName = "currentTransactions";

    // CRUD operations

    @Override
    public void delete(UUID id) {
        DbConnector
                .getDB()
                .getCollection(collName)
                .deleteOne(eq("_id", new ObjectId(String.valueOf(id))));
    }

    @Override
    public CurrentTransaction find(UUID id) {
        return DbConnector
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
        DbConnector
                .getDB()
                .getCollection(collName)
                .insertOne(doc);
    }

    @Override
    public void update(UUID id, CurrentTransaction toUpdate) {
        Document doc = Converter.toDocument(toUpdate);
        DbConnector
                .getDB()
                .getCollection(collName)
                .replaceOne(
                        eq("_id", new ObjectId(String.valueOf(id))),
                        doc);
    }

    // other methods

    public List<CurrentTransaction> findAllCurrentTransactions() {
        return DbConnector
                .getDB()
                .getCollection(collName)
                .find(CurrentTransaction.class)
                .into(new ArrayList<>());
    }
}
