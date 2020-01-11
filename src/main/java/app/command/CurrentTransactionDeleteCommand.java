package app.command;

import app.dao.CurrentTransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;
import org.bson.types.ObjectId;

public class CurrentTransactionDeleteCommand implements Command {
    CurrentTransactionDAO dao;

    private ObjectId _id;
    private CurrentTransaction currentTransaction;

    public CurrentTransactionDeleteCommand(ObjectId _id) {
        this.dao = new CurrentTransactionDAO();
        this._id = _id;
        this.currentTransaction = dao.find(_id);
    }

    @Override
    public void execute() {
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, currentTransaction.toString()));
    }

    @Override
    public void undo() {
        dao.save(currentTransaction);

        Logger.log(new LogEntry(EntryType.CREATE, currentTransaction.toString()));
    }

    @Override
    public void redo() {
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, currentTransaction.toString()));
    }
}
