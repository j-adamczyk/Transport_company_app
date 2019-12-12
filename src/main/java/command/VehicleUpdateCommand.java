package command;

import dao.VehicleDAO;
import model.Vehicle;

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
    }

    @Override
    public void undo() {
        VehicleDAO dao = new VehicleDAO();
        dao.update(oldVehicle.get_id(), oldVehicle);
    }

    @Override
    public void redo() {
        VehicleDAO dao = new VehicleDAO();
        dao.update(newVehicle.get_id(), newVehicle);
    }
}
