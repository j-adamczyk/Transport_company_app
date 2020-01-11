package app.command;

import app.dao.CompanyDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Company;
import org.bson.types.ObjectId;

public class CompanyDeleteCommand implements Command {
    CompanyDAO dao;

    private ObjectId _id;
    private Company company;

    public CompanyDeleteCommand(ObjectId _id) {
        this.dao = new CompanyDAO();
        this._id = _id;
    }

    @Override
    public void execute() {
        this.company = dao.find(_id);
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, company.toString()));
    }

    @Override
    public void undo() {
        dao.save(company);

        Logger.log(new LogEntry(EntryType.CREATE, company.toString()));
    }

    @Override
    public void redo() {
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, company.toString()));
    }
}
