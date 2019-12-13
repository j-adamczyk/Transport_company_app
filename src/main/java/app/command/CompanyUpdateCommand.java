package app.command;

import app.dao.CompanyDAO;
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
    }

    @Override
    public void undo() {
        CompanyDAO dao = new CompanyDAO();
        dao.update(oldCompany.get_id(), oldCompany);
    }

    @Override
    public void redo() {
        CompanyDAO dao = new CompanyDAO();
        dao.update(newCompany.get_id(), newCompany);
    }
}
