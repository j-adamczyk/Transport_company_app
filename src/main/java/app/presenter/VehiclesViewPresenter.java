package app.presenter;

import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VehiclesViewPresenter {

    @FXML
    private TableView<Driver> vehicleTableView;
    @FXML
    private TableColumn<Driver, String> vehicleModel;
    @FXML
    private TableColumn<Driver, String> vehicleRegistrationNo;
    @FXML
    private TableColumn<Driver, String> vehicleManufactureDate;
    @FXML
    private TableColumn<Driver, String> vehicleCargoVolume;
    @FXML
    private TableColumn<Driver, String> vehicleCargoWeight;

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
