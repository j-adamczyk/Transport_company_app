package command;

import dao.DriverDAO;
import model.Driver;

public class DriverSaveCommand implements Command {
    private Driver driver;

    public DriverSaveCommand(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void execute() {
        DriverDAO dao = new DriverDAO();
        dao.save(driver);
    }

    @Override
    public void undo() {
        DriverDAO dao = new DriverDAO();
        dao.delete(driver.get_id());
    }

    @Override
    public void redo() {
        DriverDAO dao = new DriverDAO();
        dao.save(driver);
    }
}
