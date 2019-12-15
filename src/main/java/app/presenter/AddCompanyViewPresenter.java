package app.presenter;

import app.command.CompanySaveCommand;
import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddCompanyViewPresenter extends DialogPresenter{

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
        CompanySaveCommand CSC = new CompanySaveCommand(new Company(name, address, phone, mail, representative));
        CSC.execute();
        CompanyDAO companyDao = new CompanyDAO();
        System.out.println(companyDao.findAllCompanies());
    }
    @FXML
    private void handleCancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
