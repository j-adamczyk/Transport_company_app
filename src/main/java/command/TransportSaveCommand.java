package command;

import dao.TransportDAO;
import model.Transport;

public class TransportSaveCommand implements Command {
    private Transport transport;

    public TransportSaveCommand(Transport transport) {
        this.transport = transport;
    }

    @Override
    public void execute() {
        TransportDAO dao = new TransportDAO();
        dao.save(transport);
    }

    @Override
    public void undo() {
        TransportDAO dao = new TransportDAO();
        dao.delete(transport.get_id());
    }

    @Override
    public void redo() {
        TransportDAO dao = new TransportDAO();
        dao.save(transport);
    }
}
