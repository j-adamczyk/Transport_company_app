package app.presenter;

import app.model.Duration;
import app.model.Transport;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;

public class TransportsViewPresenter extends SwitchPresenter{

    @FXML
    private CheckBox presentCheckBox;
    @FXML
    private CheckBox futureCheckBox;
    @FXML
    private CheckBox pastCheckBox;
    @FXML
    private TableView transportsTable;
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
    private void initialize(){
        transportsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        /*cargoColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getCurrentTransaction()));
        cargoUnitsColumn.setCellValueFactory(dataValue -> dataValue.getValue());
        driverColumn.setCellValueFactory(dataValue -> dataValue.getValue());
        dateColumn.setCellValueFactory(dataValue -> dataValue.getValue());
        expectedTimeColumn.setCellValueFactory(dataValue -> dataValue.getValue());
        fromColumn.setCellValueFactory(dataValue -> dataValue.getValue());
        destinationColumn.setCellValueFactory(dataValue -> dataValue.getValue());
        vehicleColumn.setCellValueFactory(dataValue -> dataValue.getValue());*/
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
//        TODO
    }
    @FXML
    private void handleEditTransportAction(){
//        TODO
    }

}
