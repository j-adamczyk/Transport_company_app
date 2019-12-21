package app.command;

import app.dao.CompanyDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Company;
import org.bson.types.ObjectId;

public class CompanyDeleteCommand implements Command {
    private ObjectId _id;
    private Company company;

    public CompanyDeleteCommand(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public void execute() {
        CompanyDAO dao = new CompanyDAO();
        this.company = dao.find(_id);
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, company.toString()));
    }

    @Override
    public void undo() {
        CompanyDAO dao = new CompanyDAO();
        dao.save(company);

        Logger.log(new LogEntry(EntryType.CREATE, company.toString()));
    }

    @Override
    public void redo() {
        CompanyDAO dao = new CompanyDAO();
        dao.delete(_id);

        Logger.log(new LogEntry(EntryType.DELETE, company.toString()));
    }
}
