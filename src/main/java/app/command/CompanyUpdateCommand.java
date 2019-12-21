package app.command;

import app.dao.CompanyDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Company;

public class CompanyUpdateCommand implements Command {
    private Company oldCompany;
    private Company newCompany;

    public CompanyUpdateCommand(Company company) {
        this.newCompany = company;
    }

    @Override
    public void execute() {
        CompanyDAO dao = new CompanyDAO();
        this.oldCompany = dao.find(newCompany.get_id());
        dao.update(newCompany.get_id(), newCompany);

        Logger.log(new LogEntry(EntryType.UPDATE, oldCompany.toString() + " -> " + newCompany.toString()));
    }

    @Override
    public void undo() {
        CompanyDAO dao = new CompanyDAO();
        dao.update(oldCompany.get_id(), oldCompany);

        Logger.log(new LogEntry(EntryType.UPDATE, newCompany.toString() + " -> " + oldCompany.toString()));
    }

    @Override
    public void redo() {
        CompanyDAO dao = new CompanyDAO();
        dao.update(newCompany.get_id(), newCompany);

        Logger.log(new LogEntry(EntryType.UPDATE, oldCompany.toString() + " -> " + newCompany.toString()));
    }
}
