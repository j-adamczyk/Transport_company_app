package app.command;

import app.dao.CompanyDAO;
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
    }

    @Override
    public void undo() {
        CompanyDAO dao = new CompanyDAO();
        dao.delete(company.get_id());
    }

    @Override
    public void redo() {
        CompanyDAO dao = new CompanyDAO();
        dao.save(company);
    }
}
