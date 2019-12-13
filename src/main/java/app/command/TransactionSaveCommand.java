package app.command;

import app.dao.TransactionDAO;
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
    }

    @Override
    public void undo() {
        TransactionDAO dao = new TransactionDAO();
        dao.delete(transaction.get_id());
    }

    @Override
    public void redo() {
        TransactionDAO dao = new TransactionDAO();
        dao.save(transaction);
    }
}
