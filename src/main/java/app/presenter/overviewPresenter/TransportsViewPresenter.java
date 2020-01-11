package app.presenter.overviewPresenter;

import app.command.TransportDeleteCommand;
import app.dao.TransportDAO;
import app.model.Cargo;
import app.model.Duration;
import app.model.Transport;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TransportsViewPresenter extends SwitchPresenter {
    private ObservableList<Transport> transports;
    private ObservableList<Cargo> cargo;
    private Map<String, Cargo> cargoTypesMap = new HashMap<>();
    private Map<String, Integer> cargoUnitsMap = new HashMap<>();
    private ObjectProperty<Transport> selectedTransportProperty = new SimpleObjectProperty<>();

    @FXML
    private CheckBox presentCheckBox;
    @FXML
    private CheckBox futureCheckBox;
    @FXML
    private CheckBox pastCheckBox;
    @FXML
    private TableView<Transport> transportsTable;
    @FXML
    private TableColumn<Transport, String> driverColumn;
    @FXML
    private TableColumn<Transport, String> vehicleColumn;
    @FXML
    private TableColumn<Transport, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Transport, Duration> expectedTimeColumn;
    @FXML
    private TableColumn<Transport, String> fromColumn;
    @FXML
    private TableColumn<Transport, String> destinationColumn;
    @FXML
    private TableView<Cargo> cargoTable;
    @FXML
    private TableColumn<Cargo, String> cargoNameColumn;
    @FXML
    private TableColumn<Cargo, String> cargoUnitsColumn;
    @FXML
    private TableColumn<Cargo, String> cargoVolumeColumn;
    @FXML
    private TableColumn<Cargo, String> cargoWeightColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button driverDetButton;
    @FXML
    private Button currTransDetButton;
    @FXML
    private Button vehicleDetButton;
    @FXML
    private Button deleteTransportButton;
    @FXML
    private Button addTransportButton;
    @FXML
    private Button editTransportButton;
    @FXML
    private Label returnLabel;


    @FXML
    private void initialize(){
        transportsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TransportDAO transportDAO = new TransportDAO();
        dateColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDepartureDate()));
        driverColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getDriver().toString()));
        vehicleColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getVehicle().toString()));
        expectedTimeColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getExpectedTime()));
        fromColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCurrentTransaction().getTransaction().getOrigin().toString()));
        destinationColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCurrentTransaction().getTransaction().getDestination().toString()));
        cargoNameColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getName()));
        cargoUnitsColumn.setCellValueFactory(value -> new SimpleStringProperty
                (transportsTable.getSelectionModel().getSelectedItem()==null ? ""
                        : transportsTable.getSelectionModel().getSelectedItem().getCargoUnits().get(value.getValue().getName()).toString()));
        cargoVolumeColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getVolume().toString()));
        cargoWeightColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getWeight().toString()));
        this.cargo = FXCollections.observableArrayList();
        cargoTable.setItems(cargo);
        this.transports = FXCollections.observableArrayList();
        transports.addAll(transportDAO.findAllTransports());
//        TODO checkboxes
        transportsTable.setItems(transports);

        editTransportButton.disableProperty().bind(
                Bindings.size(
                        transportsTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        deleteTransportButton.disableProperty().bind(
                Bindings.size(
                        transportsTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        vehicleDetButton.disableProperty().bind(
                Bindings.size(
                        transportsTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        driverDetButton.disableProperty().bind(
                Bindings.size(
                        transportsTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        currTransDetButton.disableProperty().bind(
                Bindings.size(
                        transportsTable.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        transportsTable.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                cargo.clear();
                selectedTransportProperty.set(nv);
                cargo.addAll(selectedTransportProperty.get().getCargoTypes().values());
                cargoTable.refresh();
            }
        });
    }

    @FXML
    private void handleDatePickerAction(){
//        TODO
    }

    @FXML
    private void handleDriverDetAction(){
        appPresenter.showSelectedDriver(transportsTable.getSelectionModel().getSelectedItem().getDriver(),
                appPresenter.getPrimaryStage());
    }

    @FXML
    private void handleCurrTransDetAction(){
        appPresenter.showSelectedTransaction(
                transportsTable.getSelectionModel().getSelectedItem().getCurrentTransaction().getTransaction(),
                appPresenter.getPrimaryStage());
    }

    @FXML
    private void handleVehicleDetAction(){
        appPresenter.showSelectedVehicle(transportsTable.getSelectionModel().getSelectedItem().getVehicle(),
                appPresenter.getPrimaryStage());
    }

    @FXML
    private void handleAddTransportAction(){
        Transport addedTransport = appPresenter.showAddTransportView();
        if(addedTransport != null){
            this.transports.add(addedTransport);
        }
    }

    @FXML
    private void handleDeleteTransportAction(){
        Transport toRemove = transportsTable.getSelectionModel().getSelectedItem();
        TransportDeleteCommand tdc = new TransportDeleteCommand(toRemove.get_id());
        tdc.execute();
        transports.remove(toRemove);
//todo maybe
        transportsTable.refresh();
    }

    @FXML
    private void handleEditTransportAction(){
        appPresenter.showEditTransportView(transportsTable.getSelectionModel().getSelectedItem());
        transportsTable.refresh();
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }

}
