package app.command;

import app.dao.CurrentTransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;

public class CurrentTransactionSaveCommand implements Command{
    private CurrentTransaction currentTransaction;

    public CurrentTransactionSaveCommand(CurrentTransaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public void execute() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.save(currentTransaction);

        Logger.log(new LogEntry(EntryType.CREATE, currentTransaction.toString()));
    }

    @Override
    public void undo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.delete(currentTransaction.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, currentTransaction.toString()));
    }

    @Override
    public void redo() {
        CurrentTransactionDAO dao = new CurrentTransactionDAO();
        dao.save(currentTransaction);

        Logger.log(new LogEntry(EntryType.CREATE, currentTransaction.toString()));
    }
}
