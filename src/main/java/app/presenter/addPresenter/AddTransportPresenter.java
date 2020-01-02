package app.presenter.addPresenter;

import app.dao.CurrentTransactionDAO;
import app.dao.DriverDAO;
import app.dao.VehicleDAO;
import app.model.CurrentTransaction;
import app.model.Driver;
import app.model.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class AddTransportPresenter extends DialogPresenter {

    private CurrentTransaction currentTransaction;
    private Driver driver;
    private Vehicle vehicle;

    @FXML
    private ChoiceBox<String> currentTransactionIdChoiceBox;
    private Map<String, CurrentTransaction> currentTransactionMap = new LinkedHashMap<>();
    @FXML
    private Label viewCurrentTransactionsLabel;
    @FXML
    private ChoiceBox<String> driverChoiceBox;
    private Map<String, Driver> driverMap = new LinkedHashMap<>();
    @FXML
    private Label viewDriversLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> vehicleChoiceBox;
    private Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
    @FXML
    private Label viewVehiclesLabel;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;
    @FXML
    Spinner hourSpinner;
    @FXML
    Spinner minuteSpinner;


    @FXML
    private void initialize(){
        //current transaction box init
        CurrentTransactionDAO CTdao = new CurrentTransactionDAO();
        for(CurrentTransaction transaction: CTdao.findAllCurrentTransactions()) {
            currentTransactionMap.put(transaction.get_id().toString(), transaction);
            currentTransactionIdChoiceBox.getItems().add(transaction.get_id().toString());
        }

        //driver box init
        DriverDAO driverDAO = new DriverDAO();
        for(Driver d: driverDAO.findAllDrivers()) {
            driverMap.put(d.getName(), d);
            driverChoiceBox.getItems().addAll(d.getName());
        }

        //vehicle box init
        VehicleDAO vehicleDAO = new VehicleDAO();
        for(Vehicle v: vehicleDAO.findAllVehicles()) {
            vehicleMap.put(v.getRegistrationNo(), v);
            vehicleChoiceBox.getItems().add(v.getRegistrationNo());
        }

        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23,12));
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30));
        hourSpinner.setEditable(true);
        minuteSpinner.setEditable(true);
    }

    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }
    @FXML
    private void handleAcceptButtonAction(){
        currentTransaction = currentTransactionMap.get(currentTransactionIdChoiceBox.getValue());
        driver = driverMap.get(driverChoiceBox.getValue());
        vehicle = vehicleMap.get(vehicleChoiceBox.getValue());
        LocalDateTime dateTime = datePicker.getValue()
                .atTime((Integer)hourSpinner.getValue(),(Integer) minuteSpinner.getValue());
//        Transport transport = new Transport(currentTransaction, driver, vehicle, dateTime);
//        new TransportSaveCommand(transport).execute();
//        addedObject = transport;
        dialogStage.close();
    };

    @FXML
    private void handleViewCurrTransLabelAction(){
//        TODO
    };
    @FXML
    private void handleDatePickerAction(){
//        TODO
    };
    @FXML
    private void handleViewDriversLabelAction(){
//        TODo
    };
    @FXML
    private void handleViewVehiclesLabelAction(){
//        todo
    };

}
