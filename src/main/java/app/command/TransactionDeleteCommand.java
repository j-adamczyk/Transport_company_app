package app.command;

import app.dao.CurrentTransactionDAO;
import app.dao.TransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;
import app.model.Transaction;
import org.bson.types.ObjectId;

public class TransactionDeleteCommand implements Command {
    private TransactionDAO transactionDAO;
    private CurrentTransactionDAO currentTransactionDAO;

    private ObjectId transaction_id;
    private Transaction transaction;

    private ObjectId currentTransaction_id;
    private CurrentTransaction currentTransaction;

    public TransactionDeleteCommand(ObjectId transaction_id) {
        this.transactionDAO = new TransactionDAO();
        this.currentTransactionDAO = new CurrentTransactionDAO();

        this.transaction_id = transaction_id;
    }

    @Override
    public void execute() {
        this.transaction = transactionDAO.find(transaction_id);
        this.currentTransaction = transaction.getCurrentTransaction();
        this.currentTransaction_id = currentTransaction.get_id();

        transactionDAO.delete(transaction_id);
        currentTransactionDAO.delete(currentTransaction_id);

        Logger.log(new LogEntry(EntryType.DELETE, transaction.toString()));
        Logger.log(new LogEntry(EntryType.DELETE, currentTransaction.toString()));
    }

    @Override
    public void undo() {
        transactionDAO.save(transaction);
        currentTransactionDAO.save(currentTransaction);

        Logger.log(new LogEntry(EntryType.CREATE, transaction.toString()));
        Logger.log(new LogEntry(EntryType.CREATE, currentTransaction.toString()));
    }

    @Override
    public void redo() {
        transactionDAO.delete(transaction_id);
        currentTransactionDAO.delete(currentTransaction_id);

        Logger.log(new LogEntry(EntryType.DELETE, transaction.toString()));
        Logger.log(new LogEntry(EntryType.DELETE, currentTransaction.toString()));
    }
}
