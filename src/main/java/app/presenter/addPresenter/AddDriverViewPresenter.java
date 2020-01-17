package app.presenter.addPresenter;

import app.command.CommandRegistry;
import app.command.DriverSaveCommand;
import app.model.Address;
import app.model.Driver;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    private void initialize(){
        acceptButton.disableProperty().bind(
                Bindings.isEmpty(nameField.textProperty())
                        .or(Bindings.isEmpty(streetField.textProperty()))
                        .or(Bindings.isEmpty(cityField.textProperty()))
                        .or(Bindings.isEmpty(postalCodeField.textProperty()))
                        .or(Bindings.isEmpty(countryField.textProperty()))
                        .or(Bindings.isEmpty(salaryField.textProperty()))
                        .or(Bindings.isEmpty(phoneField.textProperty()))
        );
    }

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
        CommandRegistry.getInstance().executeCommand(DSC);

        dialogStage.close();
    }

    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }

}
