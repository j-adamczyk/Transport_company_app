package app.command;

import app.dao.TransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transaction;
import org.bson.types.ObjectId;

public class TransactionDeleteCommand implements Command {
    private ObjectId _id;
    private Transaction transaction;

    public TransactionDeleteCommand(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public void execute() {
        TransactionDAO dao = new TransactionDAO();
        this.transaction = dao.find(_id);
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, transaction.toString()));
    }

    @Override
    public void undo() {
        TransactionDAO dao = new TransactionDAO();
        dao.save(transaction);

        Logger.log(new LogEntry(EntryType.CREATE, transaction.toString()));
    }

    @Override
    public void redo() {
        TransactionDAO dao = new TransactionDAO();
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, transaction.toString()));
    }
}
