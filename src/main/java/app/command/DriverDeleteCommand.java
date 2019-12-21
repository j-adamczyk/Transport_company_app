package app.command;

import app.dao.DriverDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Driver;
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

        Logger.log(new LogEntry(EntryType.DELETE, driver.toString()));
    }

    @Override
    public void undo() {
        DriverDAO dao = new DriverDAO();
        dao.save(driver);

        Logger.log(new LogEntry(EntryType.CREATE, driver.toString()));
    }

    @Override
    public void redo() {
        DriverDAO dao = new DriverDAO();
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, driver.toString()));
    }
}
