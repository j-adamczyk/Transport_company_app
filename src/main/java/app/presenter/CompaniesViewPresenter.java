package app.presenter;

import app.command.CompanyDeleteCommand;
import app.command.CompanySaveCommand;
import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Company;
import app.model.Driver;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class CompaniesViewPresenter extends SwitchPresenter{
    private ObservableList<Company> companies;

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
    private void initialize(){
        CompanyDAO companyDAO = new CompanyDAO();
        companyTableView.getSelectionModel().getTableView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        companyAddress.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getAddress().toString()));
        companyMail.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getMail()));
        companyName.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getName()));
        companyPhone.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getPhone()));
        companyRepresentative.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getRepresentative()));
        this.companies = FXCollections.observableArrayList();
        companies.addAll(companyDAO.findAllCompanies());
        companyTableView.setItems(companies);

        editButton.disableProperty().bind(
                Bindings.size(
                        companyTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        deleteButton.disableProperty().bind(
                Bindings.size(
                        companyTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
    }

    @FXML
    private void handleAddButtonAction(){
        companies.add(appPresenter.showAddCompanyView());

    }
    @FXML
    private void handleDeleteButtonAction(){
        Company toRemove = companyTableView.getSelectionModel().getSelectedItem();
        CompanyDeleteCommand cdc = new CompanyDeleteCommand(toRemove.get_id());
        cdc.execute();
        companies.remove(toRemove);

        companyTableView.refresh();
    }
    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditCompanyView(companyTableView.getSelectionModel().getSelectedItem());
        companyTableView.refresh();
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }
}
