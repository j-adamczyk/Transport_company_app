package app.presenter.selectedPresenter;

import app.command.VehicleDeleteCommand;
import app.dao.VehicleDAO;
import app.model.Vehicle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SelectedVehiclePresenter extends SelectedPresenter{
    private ObservableList<Vehicle> vehicles;
    private ObjectProperty<Vehicle> selectedVehicleProperty = new SimpleObjectProperty<>();

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
        vehicleTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                selectedVehicleProperty.set(nv);
            }
        });

    }

    @Override
    public void setOldObject(Object object){
        Vehicle selectedDriver = (Vehicle) object;
        vehicleTableView.getSelectionModel().select(selectedDriver);
    }

    @Override
    public Vehicle getSelectedObject(){
        return this.selectedVehicleProperty.get();
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
        vdc.execute();
        vehicles.remove(toRemove);

        vehicleTableView.refresh();
    }

    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditVehicleView(vehicleTableView.getSelectionModel().getSelectedItem());
        vehicleTableView.refresh();
    }

}
