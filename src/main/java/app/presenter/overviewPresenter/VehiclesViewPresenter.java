package app.presenter.overviewPresenter;

import app.command.CommandRegistry;
import app.command.VehicleDeleteCommand;
import app.dao.VehicleDAO;
import app.model.Vehicle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VehiclesViewPresenter extends SwitchPresenter {
    private ObservableList<Vehicle> vehicles;

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
    private Label returnLabel;

    @FXML
    private void initialize(){
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleTableView.getSelectionModel().getTableView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        vehicleCargoVolume.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCargoVolume().toString()));
        vehicleCargoWeight.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCargoWeight().toString()));
        vehicleManufactureDate.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getManufactureDate().toString()));
        vehicleModel.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getModel()));
        vehicleRegistrationNo.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getRegistrationNo()));
        this.vehicles = FXCollections.observableArrayList();
        vehicles.addAll(vehicleDAO.findAllVehicles());
        vehicleTableView.setItems(vehicles);

        editButton.disableProperty().bind(
                Bindings.size(
                        vehicleTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        deleteButton.disableProperty().bind(
                Bindings.size(
                        vehicleTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
    }

    @FXML
    private void handleAddButtonAction(){
        Vehicle addedVehicle = appPresenter.showAddVehicleView();
        if(addedVehicle != null)
            vehicles.add(addedVehicle);
    }

    @FXML
    private void handleDeleteButtonAction(){
        Vehicle toRemove = vehicleTableView.getSelectionModel().getSelectedItem();
        VehicleDeleteCommand vdc = new VehicleDeleteCommand(toRemove.get_id());
        CommandRegistry.getInstance().executeCommand(vdc);

        vehicles.remove(toRemove);

        vehicleTableView.refresh();
    }

    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditVehicleView(vehicleTableView.getSelectionModel().getSelectedItem());
        vehicleTableView.refresh();
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }

    @Override
    protected void afterUndoRedo() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        this.vehicles.clear();
        vehicles.addAll(vehicleDAO.findAllVehicles());
    }
}
