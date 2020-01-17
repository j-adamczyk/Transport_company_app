package app.presenter.selectedPresenter;

import app.command.CommandRegistry;
import app.command.CompanyDeleteCommand;
import app.dao.CompanyDAO;
import app.model.Company;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SelectedCompanyPresenter extends SelectedPresenter{

    private ObservableList<Company> companies;
    private ObjectProperty<Company> selectedCompanyProperty = new SimpleObjectProperty<>();

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
        companyTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                selectedCompanyProperty.set(nv);
            }
        });

    }

    @Override
    public void setOldObject(Object object){
        Company selectedCompany = (Company) object;
        companyTableView.getSelectionModel().select(selectedCompany);
    }

    @Override
    public Company getSelectedObject(){
        return this.selectedCompanyProperty.get();
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
}
