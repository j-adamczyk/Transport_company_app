package app.presenter.addPresenter;

import app.command.CompanySaveCommand;
import app.model.Address;
import app.model.Company;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCompanyViewPresenter extends DialogPresenter {

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
    private TextField mailField;
    @FXML
    private TextField representativeField;
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
                        .or(Bindings.isEmpty(phoneField.textProperty()))
                        .or(Bindings.isEmpty(representativeField.textProperty()))
                        .or(Bindings.isEmpty(mailField.textProperty()))
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
        String mail = mailField.getText();
        String representative = representativeField.getText();
        Address address = new Address(country, city, postalCode, street);
        addedObject = new Company(name, address, phone, mail, representative);
        CompanySaveCommand CSC = new CompanySaveCommand((Company) addedObject);
        CSC.execute();
        dialogStage.close();
    }

    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }
}
