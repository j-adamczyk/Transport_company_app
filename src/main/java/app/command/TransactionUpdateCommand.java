package app.command;

import app.dao.TransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transaction;

public class TransactionUpdateCommand implements Command {
    private Transaction oldTransaction;
    private Transaction newTransaction;

    public TransactionUpdateCommand(Transaction transaction) {
        this.newTransaction = transaction;
    }

    @Override
    public void execute() {
        TransactionDAO dao = new TransactionDAO();
        this.oldTransaction = dao.find(newTransaction.get_id());
        dao.update(newTransaction.get_id(), newTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransaction.toString()
                + " -> " + newTransaction.toString()));
    }

    @Override
    public void undo() {
        TransactionDAO dao = new TransactionDAO();
        dao.update(oldTransaction.get_id(), oldTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, newTransaction.toString()
                + " -> " + oldTransaction.toString()));
    }

    @Override
    public void redo() {
        TransactionDAO dao = new TransactionDAO();
        dao.update(newTransaction.get_id(), newTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, newTransaction.toString()
                + " -> " + oldTransaction.toString()));
    }
}
