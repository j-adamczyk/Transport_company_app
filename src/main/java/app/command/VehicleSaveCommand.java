package app.command;

import app.dao.VehicleDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Vehicle;

public class VehicleSaveCommand implements Command {
    private Vehicle vehicle;

    public VehicleSaveCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        VehicleDAO dao = new VehicleDAO();
        dao.save(vehicle);

        Logger.log(new LogEntry(EntryType.CREATE, vehicle.toString()));
    }

    @Override
    public void undo() {
        VehicleDAO dao = new VehicleDAO();
        dao.delete(vehicle.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, vehicle.toString()));
    }

    @Override
    public void redo() {
        VehicleDAO dao = new VehicleDAO();
        dao.save(vehicle);

        Logger.log(new LogEntry(EntryType.CREATE, vehicle.toString()));
    }
}
