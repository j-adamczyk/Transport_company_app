package command;

import dao.VehicleDAO;
import model.Vehicle;
import org.bson.types.ObjectId;

public class VehicleDeleteCommand implements Command {
    private ObjectId _id;
    private Vehicle vehicle;

    public VehicleDeleteCommand(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public void execute() {
        VehicleDAO dao = new VehicleDAO();
        this.vehicle = dao.find(_id);
        dao.delete(_id);
    }

    @Override
    public void undo() {
        VehicleDAO dao = new VehicleDAO();
        dao.save(vehicle);
    }

    @Override
    public void redo() {
        VehicleDAO dao = new VehicleDAO();
        dao.delete(_id);
    }
}
