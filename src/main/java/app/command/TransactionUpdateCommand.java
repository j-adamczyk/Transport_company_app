package app.command;

import app.dao.TransactionDAO;
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
    }

    @Override
    public void undo() {
        TransactionDAO dao = new TransactionDAO();
        dao.update(oldTransaction.get_id(), oldTransaction);
    }

    @Override
    public void redo() {
        TransactionDAO dao = new TransactionDAO();
        dao.update(newTransaction.get_id(), newTransaction);
    }
}
