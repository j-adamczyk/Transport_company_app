package app.command;


import app.dao.DriverDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Driver;

public class DriverUpdateCommand implements Command {
    DriverDAO dao;

    private Driver oldDriver;
    private Driver newDriver;

    public DriverUpdateCommand(Driver driver) {
        this.dao = new DriverDAO();
        this.newDriver = driver;
    }

    @Override
    public void execute() {
        this.oldDriver = dao.find(newDriver.get_id());
        dao.update(oldDriver.get_id(), newDriver);

        Logger.log(new LogEntry(EntryType.UPDATE, oldDriver.toString()
                + " -> " + newDriver.toString()));
    }

    @Override
    public void undo() {
        dao.update(newDriver.get_id(), oldDriver);

        Logger.log(new LogEntry(EntryType.UPDATE, newDriver.toString()
                + " -> " + oldDriver.toString()));
    }

    @Override
    public void redo() {
        dao.update(oldDriver.get_id(), newDriver);

        Logger.log(new LogEntry(EntryType.UPDATE, oldDriver.toString()
                + " -> " + newDriver.toString()));
    }
}
