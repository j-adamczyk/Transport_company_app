package app.presenter;

import app.model.Company;
import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private void handleAddButtonAction(){
//        TODO
    }
    @FXML
    private void handleDeleteButtonAction(){
//        TODO
    }
    @FXML
    private void handleEditButtonAction(){
//        TODO
    }
}
