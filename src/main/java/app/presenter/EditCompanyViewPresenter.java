package app.presenter;

import app.command.CompanySaveCommand;
import app.command.CompanyUpdateCommand;
import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditCompanyViewPresenter extends EditDialogPresenter {
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
    private TextField mailField;
    @FXML
    private TextField representativeField;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    private Company currentCompany;

    @Override
    public void setOldObject(Object company){
        this.currentCompany = (Company) company;
        nameField.setText(currentCompany.getName());
        streetField.setText(currentCompany.getAddress().getStreet());
        countryField.setText(currentCompany.getAddress().getCountry());
        cityField.setText(currentCompany.getAddress().getCity());
        postalCodeField.setText(currentCompany.getAddress().getPostalCode());
        phoneField.setText(currentCompany.getPhone());
        mailField.setText(currentCompany.getMail());
        representativeField.setText(currentCompany.getRepresentative());
        idLabel.setText("Company id: " + currentCompany._id);
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
        this.currentCompany.setAddress(address);
        this.currentCompany.setName(name);
        this.currentCompany.setPhone(phone);
        this.currentCompany.setMail(mail);
        this.currentCompany.setRepresentative(representative);
        CompanyUpdateCommand CUC = new CompanyUpdateCommand(currentCompany);
        CUC.execute();
//        CompanyDAO companyDao = new CompanyDAO();
//        System.out.println(companyDao.findAllCompanies());
        dialogStage.close();
    }
    @FXML
    private void handleCancelButtonAction(){
        dialogStage.close();
    }
}
