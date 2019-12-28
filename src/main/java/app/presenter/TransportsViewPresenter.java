package app.presenter;

import app.command.TransportDeleteCommand;
import app.dao.TransactionDAO;
import app.dao.TransportDAO;
import app.model.Cargo;
import app.model.Duration;
import app.model.Transport;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TransportsViewPresenter extends SwitchPresenter{
    private ObservableList<Transport> transports;
    private ObservableList<Cargo> cargo;
    private Map<String, Cargo> cargoTypesMap = new HashMap<>();
    private Map<String, Integer> cargoUnitsMap = new HashMap<>();

    @FXML
    private CheckBox presentCheckBox;
    @FXML
    private CheckBox futureCheckBox;
    @FXML
    private CheckBox pastCheckBox;
    @FXML
    private TableView<Transport> transportsTable;
    @FXML
    private TableColumn<Transport, String> cargoColumn;
    @FXML
    private TableColumn<Transport, Integer> cargoUnitsColumn;
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
        //todo cargoes
        //cargoUnitsColumn.setCellValueFactory(value -> new SimpleIntegerProperty(value.));

        this.transports = FXCollections.observableArrayList();
        transports.addAll(transportDAO.findAllTransports());
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
    }

    @FXML
    private void handleDatePickerAction(){
//        TODO
    }
    @FXML
    private void handleDriverDetAction(){
//        TODO
    }
    @FXML
    private void handleCurrTransDetAction(){
//        TODO
    }
    @FXML
    private void handleVehicleDetAction(){
//        TODO
    }
    @FXML
    private void handleAddTransportAction(){
        appPresenter.showAddTransportView();
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
//        TODO
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }

}
