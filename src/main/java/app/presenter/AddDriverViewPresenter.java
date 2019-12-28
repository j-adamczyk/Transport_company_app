package app.presenter;

import app.command.DriverSaveCommand;
import app.dao.DriverDAO;
import app.model.Address;
import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;


public class AddDriverViewPresenter extends DialogPresenter {
    @FXML
    private TextField nameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField salaryField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private DatePicker hireDatePicker;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void handleAcceptButtonAction(){
        String name = nameField.getText();
        String street = streetField.getText();
        String country = countryField.getText();
        String city = cityField.getText();
        String postalCode = postalCodeField.getText();
        String phone = phoneField.getText();
        Double salary = Double.valueOf(salaryField.getText());
        LocalDate birthDate = birthDatePicker.getValue();
        LocalDate hireDate = hireDatePicker.getValue();
        Address address = new Address(country, city, postalCode, street);
        addedObject = new Driver(name, birthDate, hireDate, phone, address, salary);
        DriverSaveCommand DSC = new DriverSaveCommand((Driver) addedObject);
        DSC.execute();
        dialogStage.close();
    }
    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }

}
