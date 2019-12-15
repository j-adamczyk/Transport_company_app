package app.presenter;

import app.model.Driver;
import app.model.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VehiclesViewPresenter extends SwitchPresenter{

    @FXML
    private TableView<Vehicle> vehicleTableView;
    @FXML
    private TableColumn<Vehicle, String> vehicleModel;
    @FXML
    private TableColumn<Vehicle, String> vehicleRegistrationNo;
    @FXML
    private TableColumn<Vehicle, String> vehicleManufactureDate;
    @FXML
    private TableColumn<Vehicle, String> vehicleCargoVolume;
    @FXML
    private TableColumn<Vehicle, String> vehicleCargoWeight;

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    @FXML
    private void handleAddButtonAction(){
        appPresenter.showAddVehicleView();
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
