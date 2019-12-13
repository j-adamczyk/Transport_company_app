package app.command;


import app.dao.DriverDAO;
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
        dao.update(newDriver.get_id(), newDriver);
    }

    @Override
    public void undo() {
        DriverDAO dao = new DriverDAO();
        dao.update(oldDriver.get_id(), oldDriver);
    }

    @Override
    public void redo() {
        DriverDAO dao = new DriverDAO();
        dao.update(newDriver.get_id(), newDriver);
    }
}
