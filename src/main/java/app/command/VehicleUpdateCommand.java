package app.command;

import app.dao.VehicleDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Vehicle;

public class VehicleUpdateCommand implements Command {
    VehicleDAO dao;

    private Vehicle oldVehicle;
    private Vehicle newVehicle;

    public VehicleUpdateCommand(Vehicle vehicle) {
        this.dao = new VehicleDAO();
        this.newVehicle = vehicle;
    }

    @Override
    public void execute() {
        this.oldVehicle = dao.find(newVehicle.get_id());
        dao.update(oldVehicle.get_id(), newVehicle);

        Logger.log(new LogEntry(EntryType.UPDATE, oldVehicle.toString()
                + " -> " + newVehicle.toString()));
    }

    @Override
    public void undo() {
        dao.update(newVehicle.get_id(), oldVehicle);

        Logger.log(new LogEntry(EntryType.UPDATE, newVehicle.toString()
                + " -> " + oldVehicle.toString()));
    }

    @Override
    public void redo() {
        dao.update(oldVehicle.get_id(), newVehicle);

        Logger.log(new LogEntry(EntryType.UPDATE, oldVehicle.toString()
                + " -> " + newVehicle.toString()));
    }
}
