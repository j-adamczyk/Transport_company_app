package app.presenter;

import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CompaniesViewPresenter {

    @FXML
    private TableView<Driver> companyTableView;
    @FXML
    private TableColumn<Driver, String> companyName;
    @FXML
    private TableColumn<Driver, String> companyMail;
    @FXML
    private TableColumn<Driver, String> companyRepresentative;
    @FXML
    private TableColumn<Driver, String> companyPhone;
    @FXML
    private TableColumn<Driver, String> companyAddress;

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
