package command;

import dao.CurrentTransactionDAO;
import model.CurrentTransaction;
import org.bson.types.ObjectId;

public class CurrentTransactionDeleteCommand implements Command {
    private ObjectId _id;
    private CurrentTransaction currentTransaction;

    public CurrentTransactionDeleteCommand(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public void execute() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        this.currentTransaction = dao.find(_id);
        dao.delete(_id);
    }

    @Override
    public void undo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.save(currentTransaction);
    }

    @Override
    public void redo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.delete(_id);
    }
}
