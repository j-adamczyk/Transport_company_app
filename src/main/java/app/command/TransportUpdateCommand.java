package app.command;

import app.dao.CurrentTransactionDAO;
import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;
import app.model.Transport;

public class TransportUpdateCommand implements Command {
    TransportDAO transportDAO;
    private CurrentTransactionDAO currentTransactionDAO;

    private Transport oldTransport;
    private Transport newTransport;

    private CurrentTransaction oldCurrentTransaction;
    private CurrentTransaction newCurrentTransaction;

    public TransportUpdateCommand(Transport transport) {
        this.transportDAO = new TransportDAO();
        this.currentTransactionDAO = new CurrentTransactionDAO();

        this.oldTransport = transportDAO.find(transport.get_id());
        this.newTransport = transport;

        this.oldCurrentTransaction = currentTransactionDAO.find(transport.getCurrentTransaction().get_id());
        this.newCurrentTransaction = transport.getCurrentTransaction();
    }

    @Override
    public void execute() {
        transportDAO.update(oldTransport.get_id(), newTransport);
        currentTransactionDAO.update(oldCurrentTransaction.get_id(), newCurrentTransaction);


        Logger.log(new LogEntry(EntryType.UPDATE, oldTransport.toString()
                + " -> " + newTransport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }

    @Override
    public void undo() {
        transportDAO.update(newTransport.get_id(), oldTransport);
        currentTransactionDAO.update(newCurrentTransaction.get_id(), oldCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, newTransport.toString()
                + " -> " + oldTransport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, newCurrentTransaction.toString()
                + " -> " + oldCurrentTransaction.toString()));
    }

    @Override
    public void redo() {
        transportDAO.update(oldTransport.get_id(), newTransport);
        currentTransactionDAO.update(oldCurrentTransaction.get_id(), newCurrentTransaction);


        Logger.log(new LogEntry(EntryType.UPDATE, oldTransport.toString()
                + " -> " + newTransport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }
}
