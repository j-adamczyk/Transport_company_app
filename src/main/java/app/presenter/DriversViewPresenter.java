package app.presenter;

import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DriversViewPresenter extends SwitchPresenter{
    @FXML
    private TableView<Driver> driverTableView;
    @FXML
    private TableColumn<Driver, String> driverName;
    @FXML
    private TableColumn<Driver, String> driverBirthDay;
    @FXML
    private TableColumn<Driver, String> driverHireDate;
    @FXML
    private TableColumn<Driver, String> driverPhone;
    @FXML
    private TableColumn<Driver, String> driverAddress;
    @FXML
    private TableColumn<Driver, String> driverSalary;
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
