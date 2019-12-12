package command;

import dao.CompanyDAO;
import model.Company;
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
    }

    @Override
    public void undo() {
        CompanyDAO dao = new CompanyDAO();
        dao.save(company);
    }

    @Override
    public void redo() {
        CompanyDAO dao = new CompanyDAO();
        dao.delete(_id);
    }
}
