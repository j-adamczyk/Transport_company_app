package app.presenter.overviewPresenter;

import app.command.CommandRegistry;
import app.command.CompanyDeleteCommand;
import app.dao.CompanyDAO;
import app.model.Company;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class CompaniesViewPresenter extends SwitchPresenter {
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

    @Override
    @FXML
    protected void initialize(){
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
        companyTableView.refresh();
        System.out.println("refreshed");
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
        Company addedCompany = appPresenter.showAddCompanyView();
        if(addedCompany!=null)
            companies.add(addedCompany);
    }

    @FXML
    private void handleDeleteButtonAction(){
        Company toRemove = companyTableView.getSelectionModel().getSelectedItem();
        CompanyDeleteCommand cdc = new CompanyDeleteCommand(toRemove.get_id());
        CommandRegistry.getInstance().executeCommand(cdc);

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
