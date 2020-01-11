package app.command;

import app.dao.CurrentTransactionDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.CurrentTransaction;

public class CurrentTransactionUpdateCommand implements Command {
    CurrentTransactionDAO dao;

    private CurrentTransaction oldCurrentTransaction;
    private CurrentTransaction newCurrentTransaction;

    public CurrentTransactionUpdateCommand(CurrentTransaction currentTransaction) {
        this.dao = new CurrentTransactionDAO();
        this.newCurrentTransaction = currentTransaction;
    }

    @Override
    public void execute() {
        this.oldCurrentTransaction = dao.find(newCurrentTransaction.get_id());
        dao.update(oldCurrentTransaction.get_id(), newCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }

    @Override
    public void undo() {
        dao.update(newCurrentTransaction.get_id(), oldCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, newCurrentTransaction.toString()
                + " -> " + oldCurrentTransaction.toString()));
    }

    @Override
    public void redo() {
        dao.update(oldCurrentTransaction.get_id(), newCurrentTransaction);

        Logger.log(new LogEntry(EntryType.UPDATE, oldCurrentTransaction.toString()
                + " -> " + newCurrentTransaction.toString()));
    }
}
