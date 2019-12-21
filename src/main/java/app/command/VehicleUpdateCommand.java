package app.command;

import app.dao.VehicleDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Vehicle;

public class VehicleUpdateCommand implements Command {
    private Vehicle oldVehicle;
    private Vehicle newVehicle;

    public VehicleUpdateCommand(Vehicle vehicle) {
        this.newVehicle = vehicle;
    }

    @Override
    public void execute() {
        VehicleDAO dao = new VehicleDAO();
        this.oldVehicle = dao.find(newVehicle.get_id());
        dao.update(newVehicle.get_id(), newVehicle);

        Logger.log(new LogEntry(EntryType.UPDATE, oldVehicle.toString()
                + " -> " + newVehicle.toString()));
    }

    @Override
    public void undo() {
        VehicleDAO dao = new VehicleDAO();
        dao.update(oldVehicle.get_id(), oldVehicle);

        Logger.log(new LogEntry(EntryType.UPDATE, newVehicle.toString()
                + " -> " + oldVehicle.toString()));
    }

    @Override
    public void redo() {
        VehicleDAO dao = new VehicleDAO();
        dao.update(newVehicle.get_id(), newVehicle);

        Logger.log(new LogEntry(EntryType.UPDATE, oldVehicle.toString()
                + " -> " + newVehicle.toString()));
    }
}
