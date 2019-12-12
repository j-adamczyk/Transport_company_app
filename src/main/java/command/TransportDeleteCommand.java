package command;

import dao.TransportDAO;
import model.Transport;
import org.bson.types.ObjectId;

public class TransportDeleteCommand implements Command {
    private ObjectId _id;
    private Transport transport;

    public TransportDeleteCommand(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public void execute() {
        TransportDAO dao = new TransportDAO();
        this.transport = dao.find(_id);
        dao.delete(_id);
    }

    @Override
    public void undo() {
        TransportDAO dao = new TransportDAO();
        dao.save(transport);
    }

    @Override
    public void redo() {
        TransportDAO dao = new TransportDAO();
        dao.delete(_id);
    }
}
