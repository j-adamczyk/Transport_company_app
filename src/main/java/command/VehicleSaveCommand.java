package command;

import dao.VehicleDAO;
import model.Vehicle;

public class VehicleSaveCommand implements Command {
    private Vehicle vehicle;

    public VehicleSaveCommand(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void execute() {
        VehicleDAO dao = new VehicleDAO();
        dao.save(vehicle);
    }

    @Override
    public void undo() {
        VehicleDAO dao = new VehicleDAO();
        dao.delete(vehicle.get_id());
    }

    @Override
    public void redo() {
        VehicleDAO dao = new VehicleDAO();
        dao.save(vehicle);
    }
}
