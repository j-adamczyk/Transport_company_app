package app.command;

import app.dao.CurrentTransactionDAO;
import app.dao.TransactionDAO;
import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;
import app.model.Transaction;
import app.model.Transport;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Map;

public class TransportDeleteCommand implements Command {
    TransportDAO transportDAO;
    CurrentTransactionDAO currentTransactionDAO;
    TransactionDAO transactionDAO;

    private ObjectId _id;
    private Transport transport;
    private CurrentTransaction currentTransaction;
    private Transaction transaction;

    String oldCurrentTransactionString;
    String newCurrentTransactionString;

    String oldTransactionString;
    String newTransactionString;

    public TransportDeleteCommand(ObjectId _id) {
        this.transportDAO = new TransportDAO();
        this.currentTransactionDAO = new CurrentTransactionDAO();
        this.transactionDAO = new TransactionDAO();
        this._id = _id;
    }

    @Override
    public void execute() {
        this.transport = transportDAO.find(_id);
        this.transaction = currentTransaction.getTransaction();
        this.currentTransaction = currentTransactionDAO.findByTransactionId(transaction.get_id());

        oldCurrentTransactionString = currentTransaction.toString();
        oldTransactionString = transport.toString();

        // removing transport -> put cargo back
        currentTransaction.addCargo(transport.getCargoUnits());
        transaction.removeTransport(transport);

        newCurrentTransactionString = currentTransaction.toString();
        newTransactionString = transaction.toString();

        transportDAO.delete(_id);
        currentTransactionDAO.update(currentTransaction.get_id(), currentTransaction);
        transactionDAO.update(transaction.get_id(), transaction);

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransactionString
                + " -> " + newCurrentTransactionString));
        Logger.log(new LogEntry(EntryType.UPDATE, oldTransactionString
                + " -> " + newTransactionString));
    }

    @Override
    public void undo() {
        // adding transport -> take cargo
        currentTransaction.removeCargo(transport.getCargoUnits());
        transaction.addTransport(transport);

        transportDAO.save(transport);
        currentTransactionDAO.update(currentTransaction.get_id(), currentTransaction);
        transactionDAO.update(transaction.get_id(), transaction);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, newCurrentTransactionString
                + " -> " + oldCurrentTransactionString));
        Logger.log(new LogEntry(EntryType.UPDATE, newTransactionString
                + " -> " + oldTransactionString));
    }

    @Override
    public void redo() {
        // removing transport -> put cargo back
        currentTransaction.addCargo(transport.getCargoUnits());
        transaction.removeTransport(transport);

        transportDAO.delete(_id);
        currentTransactionDAO.update(currentTransaction.get_id(), currentTransaction);
        transactionDAO.update(transaction.get_id(), transaction);

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransactionString
                + " -> " + newCurrentTransactionString));
        Logger.log(new LogEntry(EntryType.UPDATE, oldTransactionString
                + " -> " + newTransactionString));
    }
}
