package app.presenter.addPresenter;

import app.command.CommandRegistry;
import app.command.TransportSaveCommand;
import app.dao.CurrentTransactionDAO;
import app.dao.DriverDAO;
import app.dao.TransactionDAO;
import app.dao.VehicleDAO;
import app.model.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddTransportPresenter extends DialogPresenter {

    private ObjectProperty<Transaction> transactionProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Driver> driverObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Vehicle> vehicleObjectProperty = new SimpleObjectProperty<>();

    @FXML
    private ChoiceBox<String> transactionIdChoiceBox;
    private Map<String, Transaction> transactionMap = new LinkedHashMap<>();
    @FXML
    private Label viewTransactionsLabel;
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
        //transaction box init

        transactionIdChoiceBox.valueProperty().addListener((o, ov, nv) -> {
                transactionProperty.set(transactionMap.get(nv));
        });
        setTransactionChoiceBox(null);

        //driver box init
        driverChoiceBox.valueProperty().addListener((o, ov, nv) -> {
            driverObjectProperty.set(driverMap.get(nv));
        });
        setDriverChoiceBox(null);


        //vehicle box init
        vehicleChoiceBox.valueProperty().addListener((o, ov, nv) -> {
            vehicleObjectProperty.set(vehicleMap.get(nv));
        });
        setVehicleChoiceBox(null);

        //date picker init
        datePicker.setValue(LocalDate.now());
        //spinner init

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
        CurrentTransactionDAO CTD = new CurrentTransactionDAO();
        CurrentTransaction currentTransaction = CTD.findByTransactionId(new ObjectId(transactionIdChoiceBox.getValue()));
        Driver driver = driverMap.get(driverChoiceBox.getValue());
        Vehicle vehicle = vehicleMap.get(vehicleChoiceBox.getValue());
        LocalDateTime dateTime = datePicker.getValue()
                .atTime((Integer)hourSpinner.getValue(),(Integer) minuteSpinner.getValue());
        Transport transport = new Transport(currentTransaction, driver, vehicle, dateTime);
        CommandRegistry.getInstance().executeCommand(new TransportSaveCommand(transport));
        addedObject = transport;
        dialogStage.close();
    };

    @FXML
    private void handleViewTransLabelAction(){
        Transaction selectedTransaction = appPresenter.showSelectedTransaction(
                transactionMap.get(transactionIdChoiceBox.getValue()), dialogStage);
        setTransactionChoiceBox(selectedTransaction._id.toString());
    };

    @FXML
    private void handleDatePickerAction(){
//        TODO
    };

    @FXML
    private void handleViewDriversLabelAction(){
        Driver selectedDriver = appPresenter.showSelectedDriver(
                driverMap.get(driverChoiceBox.getValue()), dialogStage
        );
        setDriverChoiceBox(selectedDriver.getName() + ", " + selectedDriver.getPhone());
    }

    @FXML
    private void handleViewVehiclesLabelAction(){
        Vehicle selectedVehicle = appPresenter.showSelectedVehicle(
                vehicleMap.get(vehicleChoiceBox.getValue()), dialogStage
        );
        setVehicleChoiceBox(selectedVehicle.getRegistrationNo());
    }

    private void setTransactionChoiceBox(String id){
        TransactionDAO Tdao = new TransactionDAO();
        List<Transaction> transactions = Tdao.findAllUndoneTransactions();
        transactionIdChoiceBox.getItems().clear();
        for(Transaction transaction: transactions) {
            transactionMap.put(transaction.get_id().toString(), transaction);
            transactionIdChoiceBox.getItems().add(transaction.get_id().toString());
        }
        if(transactions.isEmpty()) return;
        transactionIdChoiceBox.setValue(id == null?transactions.get(0).get_id().toString():id);
    }

    private void setDriverChoiceBox(String nameAndPhone){
        DriverDAO driverDAO = new DriverDAO();
        List<Driver> drivers = driverDAO.findAllDrivers();
        driverChoiceBox.getItems().clear();
        for(Driver d: drivers) {
            driverMap.put(d.getName() + ", " + d.getPhone(), d);
            driverChoiceBox.getItems().addAll(d.getName() + ", " + d.getPhone());
        }
        if (drivers.isEmpty()) return;
        driverChoiceBox.setValue(nameAndPhone == null?drivers.get(0).getName() + ", " + drivers.get(0).getPhone():nameAndPhone);
    }

    private void setVehicleChoiceBox(String registrationNo){
        VehicleDAO vehicleDAO = new VehicleDAO();
        List<Vehicle> vehicles = vehicleDAO.findAllVehicles();
        vehicleChoiceBox.getItems().clear();
        for(Vehicle v: vehicles) {
            vehicleMap.put(v.getRegistrationNo(), v);
            vehicleChoiceBox.getItems().add(v.getRegistrationNo());
        }
        if (vehicles.isEmpty()) return;
        vehicleChoiceBox.setValue(registrationNo == null?vehicles.get(0).getRegistrationNo():registrationNo);
    }

}
