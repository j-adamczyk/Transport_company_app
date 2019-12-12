package command;

import dao.CurrentTransactionDAO;
import model.CurrentTransaction;

public class CurrentTransactionSaveCommand implements Command{
    private CurrentTransaction currentTransaction;

    public CurrentTransactionSaveCommand(CurrentTransaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public void execute() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.save(currentTransaction);
    }

    @Override
    public void undo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.delete(currentTransaction.get_id());
    }

    @Override
    public void redo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.save(currentTransaction);
    }
}
