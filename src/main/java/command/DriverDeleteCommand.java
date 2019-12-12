package command;

import dao.DriverDAO;
import model.Driver;
import org.bson.types.ObjectId;

public class DriverDeleteCommand implements Command {
    private ObjectId _id;
    private Driver driver;

    public DriverDeleteCommand(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public void execute() {
        DriverDAO dao = new DriverDAO();
        this.driver = dao.find(_id);
        dao.delete(_id);
    }

    @Override
    public void undo() {
        DriverDAO dao = new DriverDAO();
        dao.save(driver);
    }

    @Override
    public void redo() {
        DriverDAO dao = new DriverDAO();
        dao.delete(_id);
    }
}
