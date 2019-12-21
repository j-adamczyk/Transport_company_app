package app.command;

import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transport;

public class TransportSaveCommand implements Command {
    private Transport transport;

    public TransportSaveCommand(Transport transport) {
        this.transport = transport;
    }

    @Override
    public void execute() {
        TransportDAO dao = new TransportDAO();
        dao.save(transport);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
    }

    @Override
    public void undo() {
        TransportDAO dao = new TransportDAO();
        dao.delete(transport.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
    }

    @Override
    public void redo() {
        TransportDAO dao = new TransportDAO();
        dao.save(transport);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
    }
}
