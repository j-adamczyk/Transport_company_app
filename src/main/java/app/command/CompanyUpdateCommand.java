package app.command;

import app.dao.CompanyDAO;
import app.log.EntryType;
import app.log.LogEntry;
import app.log.Logger;
import app.model.Company;

public class CompanyUpdateCommand implements Command {
    CompanyDAO dao;

    private Company oldCompany;
    private Company newCompany;

    public CompanyUpdateCommand(Company company) {
        this.dao = new CompanyDAO();

        this.oldCompany = dao.find(company.get_id());
        this.newCompany = company;
    }

    @Override
    public void execute() {
        dao.update(oldCompany.get_id(), newCompany);

        Logger.log(new LogEntry(EntryType.UPDATE, oldCompany.toString() + " -> " + newCompany.toString()));
    }

    @Override
    public void undo() {
        dao.update(newCompany.get_id(), oldCompany);

        Logger.log(new LogEntry(EntryType.UPDATE, newCompany.toString() + " -> " + oldCompany.toString()));
    }

    @Override
    public void redo() {
        dao.update(oldCompany.get_id(), newCompany);

        Logger.log(new LogEntry(EntryType.UPDATE, oldCompany.toString() + " -> " + newCompany.toString()));
    }
}
