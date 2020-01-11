package app.command;

import app.dao.DriverDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Driver;

public class DriverSaveCommand implements Command {
    DriverDAO dao;

    private Driver driver;

    public DriverSaveCommand(Driver driver) {
        this.dao = new DriverDAO();
        this.driver = driver;
    }

    @Override
    public void execute() {
        dao.save(driver);

        Logger.log(new LogEntry(EntryType.CREATE, driver.toString()));
    }

    @Override
    public void undo() {
        dao.delete(driver.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, driver.toString()));
    }

    @Override
    public void redo() {
        dao.save(driver);

        Logger.log(new LogEntry(EntryType.CREATE, driver.toString()));
    }
}
