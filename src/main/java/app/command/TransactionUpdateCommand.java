package app.command;

import app.dao.CurrentTransactionDAO;
import app.dao.TransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;
import app.model.Transaction;

public class TransactionUpdateCommand implements Command {
    TransactionDAO transactionDAO;
    CurrentTransactionDAO currentTransactionDAO;

    private Transaction oldTransaction;
    private CurrentTransaction oldCurrentTransaction;

    private Transaction newTransaction;
    private CurrentTransaction newCurrentTransaction;

    public TransactionUpdateCommand(Transaction transaction) {
        this.transactionDAO = new TransactionDAO();
        this.currentTransactionDAO = new CurrentTransactionDAO();

        this.newTransaction = transaction;
        this.newCurrentTransaction = new CurrentTransaction(transaction.getCurrentTransactionId(),
                transaction, transaction.getCargo());
    }

    @Override
    public void execute() {
        this.oldTransaction = transactionDAO.find(newTransaction.get_id());
        this.oldCurrentTransaction = currentTransactionDAO.findByTransactionId(oldTransaction.get_id());

        transactionDAO.update(oldTransaction.get_id(), newTransaction);
        currentTransactionDAO.update(oldCurrentTransaction.get_id(), newCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransaction.toString()
                + " -> " + newTransaction.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }

    @Override
    public void undo() {
        transactionDAO.update(newTransaction.get_id(), oldTransaction);
        currentTransactionDAO.update(newCurrentTransaction.get_id(), oldCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, newTransaction.toString()
                + " -> " + oldTransaction.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, newCurrentTransaction.toString()
                + " -> " + oldCurrentTransaction.toString()));
    }

    @Override
    public void redo() {
        transactionDAO.update(oldTransaction.get_id(), newTransaction);
        currentTransactionDAO.update(oldCurrentTransaction.get_id(), newCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransaction.toString()
                + " -> " + newTransaction.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }
}
