package app.command;

import app.dao.TransportDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Transport;
import org.bson.types.ObjectId;

public class TransportDeleteCommand implements Command {
    TransportDAO dao;

    private ObjectId _id;
    private Transport transport;

    public TransportDeleteCommand(ObjectId _id) {
        this.dao = new TransportDAO();
        this._id = _id;
    }

    @Override
    public void execute() {
        this.transport = dao.find(_id);
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
    }

    @Override
    public void undo() {
        dao.save(transport);

        Logger.log(new LogEntry(EntryType.CREATE, transport.toString()));
    }

    @Override
    public void redo() {
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, transport.toString()));
    }
}
