package app.command;

import app.dao.CurrentTransactionDAO;
import app.model.CurrentTransaction;

public class CurrentTransactionUpdateCommand implements Command {
    private CurrentTransaction oldCurrentTransaction;
    private CurrentTransaction newCurrentTransaction;

    public CurrentTransactionUpdateCommand(CurrentTransaction currentTransaction) {
        this.newCurrentTransaction = currentTransaction;
    }

    @Override
    public void execute() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        this.oldCurrentTransaction = dao.find(newCurrentTransaction.get_id());
        dao.update(newCurrentTransaction.get_id(), newCurrentTransaction);
    }

    @Override
    public void undo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.update(oldCurrentTransaction.get_id(), oldCurrentTransaction);
    }

    @Override
    public void redo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.update(newCurrentTransaction.get_id(), newCurrentTransaction);
    }
}
