package app.command;

import app.dao.TransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transaction;

public class TransactionSaveCommand implements Command {
    private Transaction transaction;

    public TransactionSaveCommand(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void execute() {
        TransactionDAO dao = new TransactionDAO();
        dao.save(transaction);

        Logger.log(new LogEntry(EntryType.CREATE, transaction.toString()));
    }

    @Override
    public void undo() {
        TransactionDAO dao = new TransactionDAO();
        dao.delete(transaction.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, transaction.toString()));
    }

    @Override
    public void redo() {
        TransactionDAO dao = new TransactionDAO();
        dao.save(transaction);

        Logger.log(new LogEntry(EntryType.CREATE, transaction.toString()));
    }
}
