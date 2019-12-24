package app.presenter;

import app.command.CompanySaveCommand;
import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Company;
import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class CompaniesViewPresenter extends SwitchPresenter{

    @FXML
    private TableView<Company> companyTableView;
    @FXML
    private TableColumn<Company, String> companyName;
    @FXML
    private TableColumn<Company, String> companyMail;
    @FXML
    private TableColumn<Company, String> companyRepresentative;
    @FXML
    private TableColumn<Company, String> companyPhone;
    @FXML
    private TableColumn<Company, String> companyAddress;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Label returnLabel;

    @FXML
    private void handleAddButtonAction(){
        appPresenter.showAddCompanyView();
    }
    @FXML
    private void handleDeleteButtonAction(){
//        TODO
    }
    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditCompanyView(companyTableView.getSelectionModel().getSelectedItem());
//        Company c = new Company("Kompania", new Address("Poland", "Cracow", "22-000", "Hehehihi"),
//                "999", "aaa@bbb.pl", "Pani zooosia");
//        CompanySaveCommand csv = new CompanySaveCommand(c);
//        csv.execute();
//        appPresenter.showEditCompanyView(c);
//        CompanyDAO cd = new CompanyDAO();
//        List<Company> list = cd.findAllCompanies();
//        for(Company company: list) System.out.println(company);
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }
}
