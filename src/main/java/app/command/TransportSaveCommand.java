package app.command;

import app.dao.CurrentTransactionDAO;
import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;
import app.model.Transport;

public class TransportSaveCommand implements Command {
    private TransportDAO transportDAO;
    private CurrentTransactionDAO currentTransactionDAO;
    
    private Transport transport;

    private CurrentTransaction oldCurrentTransaction;
    private CurrentTransaction newCurrentTransaction;

    public TransportSaveCommand(Transport transport) {
        this.transportDAO = new TransportDAO();
        this.currentTransactionDAO = new CurrentTransactionDAO();

        this.transport = transport;
        this.oldCurrentTransaction = currentTransactionDAO.find(transport.getCurrentTransaction().get_id());
        this.newCurrentTransaction = transport.getCurrentTransaction();
    }

    @Override
    public void execute() {
        transportDAO.save(transport);
        currentTransactionDAO.update(oldCurrentTransaction.get_id(), newCurrentTransaction);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }

    @Override
    public void undo() {
        transportDAO.delete(transport.get_id());
        currentTransactionDAO.update(newCurrentTransaction.get_id(), oldCurrentTransaction);

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, newCurrentTransaction.toString()
                + " -> " + oldCurrentTransaction.toString()));
    }

    @Override
    public void redo() {
        transportDAO.save(transport);
        currentTransactionDAO.update(oldCurrentTransaction.get_id(), newCurrentTransaction);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }
}
