package app.command;

import app.dao.VehicleDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Vehicle;

public class VehicleSaveCommand implements Command {
    VehicleDAO dao;

    private Vehicle vehicle;

    public VehicleSaveCommand(Vehicle vehicle) {
        this.dao = new VehicleDAO();
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        dao.save(vehicle);

        Logger.log(new LogEntry(EntryType.CREATE, vehicle.toString()));
    }

    @Override
    public void undo() {
        dao.delete(vehicle.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, vehicle.toString()));
    }

    @Override
    public void redo() {
        dao.save(vehicle);

        Logger.log(new LogEntry(EntryType.CREATE, vehicle.toString()));
    }
}
