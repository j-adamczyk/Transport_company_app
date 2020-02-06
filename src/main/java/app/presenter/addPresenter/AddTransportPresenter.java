package app.presenter.addPresenter;

import app.command.TransportSaveCommand;
import app.dao.CurrentTransactionDAO;
import app.dao.DriverDAO;
import app.dao.VehicleDAO;
import app.model.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddTransportPresenter extends DialogPresenter {

    private CurrentTransaction currentTransaction;
    private Driver driver;
    private Vehicle vehicle;

    private ObjectProperty<CurrentTransaction> currentTransactionProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Driver> driverObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Vehicle> vehicleObjectProperty = new SimpleObjectProperty<>();

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

        currentTransactionIdChoiceBox.valueProperty().addListener((o, ov, nv) -> {
                currentTransactionProperty.set(currentTransactionMap.get(nv));
                System.out.println(nv);
        });
        setCurrentTransactionIdChoiceBox(null);

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
        currentTransaction = currentTransactionMap.get(currentTransactionIdChoiceBox.getValue());
        driver = driverMap.get(driverChoiceBox.getValue());
        vehicle = vehicleMap.get(vehicleChoiceBox.getValue());
        LocalDateTime dateTime = datePicker.getValue()
                .atTime((Integer)hourSpinner.getValue(),(Integer) minuteSpinner.getValue());
        Transport transport = new Transport(currentTransaction, driver, vehicle, dateTime);
        new TransportSaveCommand(transport).execute();
        addedObject = transport;
        dialogStage.close();
    };

    @FXML
    private void handleViewCurrTransLabelAction(){
        Transaction selectedTransaction = appPresenter.showSelectedTransaction(
                currentTransactionMap.get(currentTransactionIdChoiceBox.getValue()).getTransaction(), dialogStage);
        CurrentTransaction currentTransaction = new CurrentTransactionDAO().findByTransactionId(selectedTransaction._id);
        setCurrentTransactionIdChoiceBox(currentTransaction._id.toString());
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

    private void setCurrentTransactionIdChoiceBox(String id){
        CurrentTransactionDAO CTdao = new CurrentTransactionDAO();
        List<CurrentTransaction> currentTransactions = CTdao.findAllCurrentTransactions();
        currentTransactionIdChoiceBox.getItems().clear();
        for(CurrentTransaction transaction: currentTransactions) {
            currentTransactionMap.put(transaction.get_id().toString(), transaction);
            currentTransactionIdChoiceBox.getItems().add(transaction.get_id().toString());
        }
        if(currentTransactions.isEmpty()) return;
        currentTransactionIdChoiceBox.setValue(id == null?currentTransactions.get(0).get_id().toString():id);
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
