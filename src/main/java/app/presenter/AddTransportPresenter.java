package app.presenter;

import app.dao.CurrentTransactionDAO;
import app.dao.DriverDAO;
import app.dao.VehicleDAO;
import app.model.CurrentTransaction;
import app.model.Driver;
import app.model.Vehicle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AddTransportPresenter extends DialogPresenter{

    @FXML
    private ChoiceBox<String> currentTransactionIdChoiceBox;
    @FXML
    private Label viewCurrentTransactionsLabel;
    @FXML
    private ChoiceBox<String> driverChoiceBox;
    @FXML
    private Label viewDriversLabel;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ChoiceBox<String> vehicleChoiceBox;
    @FXML
    private Label viewVehiclesLabel;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void initialize(){
        //current transaction box init
        /*CurrentTransactionDAO CTdao = new CurrentTransactionDAO();
        for(CurrentTransaction transaction: CTdao.findAllCurrentTransactions())
            currentTransactionIdChoiceBox.getItems().add(transaction.get_id().toString());*/
        currentTransactionIdChoiceBox.getItems().add("abdasldkas");
        currentTransactionIdChoiceBox.getItems().add("iorjweirwejr");

        //driver box init
        /*DriverDAO driverDAO = new DriverDAO();
        for(Driver d: driverDAO.findAllDrivers())
            driverChoiceBox.getItems().add(d.getName());*/
        driverChoiceBox.getItems().add("Jan Kowalski");
        driverChoiceBox.getItems().add("Adam Małysz");

        //vehicle box init
        /*VehicleDAO vehicleDAO = new VehicleDAO();
        for(Vehicle v: vehicleDAO.findAllVehicles())
            vehicleChoiceBox.getItems().add(v.getRegistrationNo());*/
        vehicleChoiceBox.getItems().add("Ferrari");
        vehicleChoiceBox.getItems().add("KCH 12ABC");
    }

    @FXML
    private void handleCurrTransChoiceBoxAction(){

    }
    @FXML
    private void handleViewCurrTransLabelAction(){
//        TODO
    }
    @FXML
    private void handleDriverChoiceBoxAction(){
//        TODO
    }
    @FXML
    private void handleViewDriversLabelAction(){
//        TODO
    }
    @FXML
    private void handleDatePickerAction(){
//        TODO
    }
    @FXML
    private void handleVehicleChoiceBoxAction(){
//        TODO
    }
    @FXML
    private void handleViewVehiclesLabelAction(){
//        TODO
    }
    @FXML
    private void handleAcceptButtonAction(){
//        TODO
    }
    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }
}
