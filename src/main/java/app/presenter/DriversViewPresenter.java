package app.presenter;

import app.dao.DriverDAO;
import app.model.Driver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DriversViewPresenter extends SwitchPresenter{
    private ObservableList<Driver> drivers;
    private Driver driver;

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
    private void initialize(){
        DriverDAO driverDAO = new DriverDAO();
        driverTableView.getSelectionModel().getTableView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        driverName.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getName()));
        driverBirthDay.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getBirthDate().toString()));
        driverHireDate.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getHireDate().toString()));
        driverAddress.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getAddress().toString()));
        driverPhone.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getPhone()));
        driverSalary.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getSalary().toString()));
        this.drivers = FXCollections.observableArrayList();
        drivers.addAll(driverDAO.findAllDrivers());
        driverTableView.setItems(drivers);
    }

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
        driverTableView.refresh();
//        DriverDAO dd = new DriverDAO();
//        Driver driver = dd.findAllDrivers().get(0);
//        appPresenter.showEditDriverView(driver);
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }
}
