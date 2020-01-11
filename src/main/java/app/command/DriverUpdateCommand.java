package app.command;


import app.dao.DriverDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Driver;

public class DriverUpdateCommand implements Command {
    private Driver oldDriver;
    private Driver newDriver;

    public DriverUpdateCommand(Driver driver) {
        this.newDriver = driver;
    }

    @Override
    public void execute() {
        DriverDAO dao = new DriverDAO();
        this.oldDriver = dao.find(newDriver.get_id());
        dao.update(oldDriver.get_id(), newDriver);

        Logger.log(new LogEntry(EntryType.UPDATE, oldDriver.toString()
                + " -> " + newDriver.toString()));
    }

    @Override
    public void undo() {
        DriverDAO dao = new DriverDAO();
        dao.update(newDriver.get_id(), oldDriver);

        Logger.log(new LogEntry(EntryType.UPDATE, newDriver.toString()
                + " -> " + oldDriver.toString()));
    }

    @Override
    public void redo() {
        DriverDAO dao = new DriverDAO();
        dao.update(oldDriver.get_id(), newDriver);

        Logger.log(new LogEntry(EntryType.UPDATE, oldDriver.toString()
                + " -> " + newDriver.toString()));
    }
}
