package app.command;

import app.dao.DriverDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Driver;

public class DriverSaveCommand implements Command {
    private Driver driver;

    public DriverSaveCommand(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        DriverDAO dao = new DriverDAO();
        dao.save(driver);

        Logger.log(new LogEntry(EntryType.CREATE, driver.toString()));
    }

    @Override
    public void undo() {
        DriverDAO dao = new DriverDAO();
        dao.delete(driver.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, driver.toString()));
    }

    @Override
    public void redo() {
        DriverDAO dao = new DriverDAO();
        dao.save(driver);

        Logger.log(new LogEntry(EntryType.CREATE, driver.toString()));
    }
}
