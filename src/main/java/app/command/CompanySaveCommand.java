package app.command;

import app.dao.CompanyDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Company;

public class CompanySaveCommand implements Command {
    CompanyDAO dao;

    private Company company;

    public CompanySaveCommand(Company company) {
        this.dao = new CompanyDAO();
        this.company = company;
    }

    @Override
    public void execute() {
        dao.save(company);

        Logger.log(new LogEntry(EntryType.CREATE, company.toString()));
    }

    @Override
    public void undo() {
        dao.delete(company.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, company.toString()));
    }

    @Override
    public void redo() {
        dao.save(company);

        Logger.log(new LogEntry(EntryType.CREATE, company.toString()));
    }
}
