package app.command;

import app.dao.VehicleDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Vehicle;
import org.bson.types.ObjectId;

public class VehicleDeleteCommand implements Command {
    VehicleDAO dao;

    private ObjectId _id;
    private Vehicle vehicle;

    public VehicleDeleteCommand(ObjectId _id) {
        this.dao = new VehicleDAO();
        this._id = _id;
    }

    @Override
    public void execute() {
        this.vehicle = dao.find(_id);
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, vehicle.toString()));
    }

    @Override
    public void undo() {
        dao.save(vehicle);

        Logger.log(new LogEntry(EntryType.CREATE, vehicle.toString()));
    }

    @Override
    public void redo() {
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, vehicle.toString()));
    }
}
