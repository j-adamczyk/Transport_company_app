package app.presenter;

import app.dao.DriverDAO;
import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label returnLabel;

    @FXML
    private void handleAddButtonAction(){
        appPresenter.showAddDriverView();
    }
    @FXML
    private void handleDeleteButtonAction(){
//        TODO
    }
    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditDriverView(driverTableView.getSelectionModel().getSelectedItem());
//        DriverDAO dd = new DriverDAO();
//        Driver driver = dd.findAllDrivers().get(0);
//        appPresenter.showEditDriverView(driver);
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }
}
