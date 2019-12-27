package app.presenter;

import app.command.DriverSaveCommand;
import app.command.DriverUpdateCommand;
import app.dao.DriverDAO;
import app.model.Address;
import app.model.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditDriverViewPresenter extends EditDialogPresenter{
    @FXML
    private Label idLabel;
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

    private Driver currentDriver;

    @Override
    public void setOldObject(Object oldDriver){
        currentDriver = (Driver) oldDriver;
        idLabel.setText("Driver id: " + currentDriver._id);
        nameField.setText(currentDriver.getName());
        streetField.setText(currentDriver.getAddress().getStreet());
        cityField.setText(currentDriver.getAddress().getCity());
        postalCodeField.setText(currentDriver.getAddress().getPostalCode());
        countryField.setText(currentDriver.getAddress().getCountry());
        phoneField.setText(currentDriver.getPhone());
        salaryField.setText(currentDriver.getSalary().toString());
        birthDatePicker.setValue(currentDriver.getBirthDate());
        hireDatePicker.setValue(currentDriver.getHireDate());
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
        currentDriver.setName(name);
        currentDriver.setAddress(address);
        currentDriver.setPhone(phone);
        currentDriver.setSalary(salary);
        currentDriver.setBirthDate(birthDate);
        currentDriver.setHireDate(hireDate);
        DriverUpdateCommand DUC = new DriverUpdateCommand(currentDriver);
        DUC.execute();
        DriverDAO driverDao = new DriverDAO();
        System.out.println(driverDao.findAllDrivers());
        dialogStage.close();
    }
    @FXML
    private void handleCancelButtonAction(){
        dialogStage.close();
    }
}
