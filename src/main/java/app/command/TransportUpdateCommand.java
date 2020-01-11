package app.command;

import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transport;

public class TransportUpdateCommand implements Command {
    private Transport oldTransport;
    private Transport newTransport;

    public TransportUpdateCommand(Transport transport) {
        this.newTransport = transport;
    }

    @Override
    public void execute() {
        TransportDAO dao = new TransportDAO();
        this.oldTransport = dao.find(newTransport.get_id());
        dao.update(oldTransport.get_id(), newTransport);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransport.toString()
                + " -> " + newTransport.toString()));
    }

    @Override
    public void undo() {
        TransportDAO dao = new TransportDAO();
        dao.update(newTransport.get_id(), oldTransport);

        Logger.log(new LogEntry(EntryType.UPDATE, newTransport.toString()
                + " -> " + oldTransport.toString()));
    }

    @Override
    public void redo() {
        TransportDAO dao = new TransportDAO();
        dao.update(oldTransport.get_id(), newTransport);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransport.toString()
                + " -> " + newTransport.toString()));
    }
}
