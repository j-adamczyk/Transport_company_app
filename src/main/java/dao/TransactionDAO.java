package dao;

import com.mongodb.client.MongoCollection;
import model.CurrentTransaction;
import model.Transaction;
import org.bson.Document;

public class TransactionDAO extends GenericDAO<Transaction>{
    @Override
    public void save(Transaction toSave) {
    }

    @Override
    public void update(Transaction toUpdate) {

    }

    @Override
    public void delete(Transaction toDelete) {

    }

    public Transaction findTransaction(String id) {
    }
}
