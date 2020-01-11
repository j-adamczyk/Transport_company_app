package app.command;

import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transport;

public class TransportUpdateCommand implements Command {
    TransportDAO dao;

    private Transport oldTransport;
    private Transport newTransport;

    public TransportUpdateCommand(Transport transport) {
        this.dao = new TransportDAO();
        this.newTransport = transport;
    }

    @Override
    public void execute() {
        this.oldTransport = dao.find(newTransport.get_id());
        dao.update(oldTransport.get_id(), newTransport);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransport.toString()
                + " -> " + newTransport.toString()));
    }

    @Override
    public void undo() {
        dao.update(newTransport.get_id(), oldTransport);

        Logger.log(new LogEntry(EntryType.UPDATE, newTransport.toString()
                + " -> " + oldTransport.toString()));
    }

    @Override
    public void redo() {
        dao.update(oldTransport.get_id(), newTransport);

        Logger.log(new LogEntry(EntryType.UPDATE, oldTransport.toString()
                + " -> " + newTransport.toString()));
    }
}
