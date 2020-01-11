package app.command;

import app.dao.DriverDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Driver;
import org.bson.types.ObjectId;

public class DriverDeleteCommand implements Command {
    DriverDAO dao;

    private ObjectId _id;
    private Driver driver;

    public DriverDeleteCommand(ObjectId _id) {
        this.dao = new DriverDAO();
        this._id = _id;
    }

    @Override
    public void execute() {
        this.driver = dao.find(_id);
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, driver.toString()));
    }

    @Override
    public void undo() {
        dao.save(driver);

        Logger.log(new LogEntry(EntryType.CREATE, driver.toString()));
    }

    @Override
    public void redo() {
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, driver.toString()));
    }
}
