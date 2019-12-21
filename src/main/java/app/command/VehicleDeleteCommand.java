package app.command;

import app.dao.VehicleDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Vehicle;
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

        Logger.log(new LogEntry(EntryType.DELETE, vehicle.toString()));
    }

    @Override
    public void undo() {
        VehicleDAO dao = new VehicleDAO();
        dao.save(vehicle);

        Logger.log(new LogEntry(EntryType.CREATE, vehicle.toString()));
    }

    @Override
    public void redo() {
        VehicleDAO dao = new VehicleDAO();
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, vehicle.toString()));
    }
}
