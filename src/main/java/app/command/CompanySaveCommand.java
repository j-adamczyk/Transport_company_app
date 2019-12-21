package app.command;

import app.dao.CompanyDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Company;

public class CompanySaveCommand implements Command {
    private Company company;

    public CompanySaveCommand(Company company) {
        this.company = company;
    }

    @Override
    public void execute() {
        CompanyDAO dao = new CompanyDAO();
        dao.save(company);

        Logger.log(new LogEntry(EntryType.CREATE, company.toString()));
    }

    @Override
    public void undo() {
        CompanyDAO dao = new CompanyDAO();
        dao.delete(company.get_id());

        Logger.log(new LogEntry(EntryType.DELETE, company.toString()));
    }

    @Override
    public void redo() {
        CompanyDAO dao = new CompanyDAO();
        dao.save(company);

        Logger.log(new LogEntry(EntryType.CREATE, company.toString()));
    }
}
