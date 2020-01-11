package app.command;

import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transport;

public class TransportSaveCommand implements Command {
    private TransportDAO dao;
    
    private Transport transport;

    public TransportSaveCommand(Transport transport) {
        this.dao = new TransportDAO();
        this.transport = transport;
    }

    @Override
    public void execute() {
        dao.save(transport);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
    }

    @Override
    public void undo() {
        dao.delete(transport.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
    }

    @Override
    public void redo() {
        dao.save(transport);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
    }
}
