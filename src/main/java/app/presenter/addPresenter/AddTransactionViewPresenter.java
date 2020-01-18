package app.presenter.addPresenter;

import app.command.CommandRegistry;
import app.command.TransactionSaveCommand;
import app.dao.CompanyDAO;
import app.model.*;
import app.presenter.editPresenter.EditCargoPresenter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddTransactionViewPresenter extends DialogPresenter {
    private Map<String, Cargo> cargoTypesMap = new HashMap<>();
    private Map<String, Integer> cargoUnitsMap = new HashMap<>();
    private ObservableList<Cargo> cargoes;
    @FXML
    private ChoiceBox<String> contractorChoiceBox;
    @FXML
    private TextField purchaseField;
    @FXML
    private DatePicker transactionDatePicker;
    //from address
    @FXML
    private TextField fromStreetField;
    @FXML
    private TextField fromCityField;
    @FXML
    private TextField fromPostalCodeField;
    @FXML
    private TextField fromCountryField;
    //destination address
    @FXML
    private TextField destinationStreetField;
    @FXML
    private TextField destinationCityField;
    @FXML
    private TextField destinationPostalCodeField;
    @FXML
    private TextField destinationCountryField;
   //cargo table
   @FXML
   private TableView<Cargo> cargoTable;
    @FXML
    private TableColumn<Cargo, String> cargoNameColumn;
    @FXML
    private TableColumn<Cargo, String> cargoUnitsColumn;
    @FXML
    private TableColumn<Cargo, String> cargoVolumeColumn;
    @FXML
    private TableColumn<Cargo, String> cargoWeightColumn;
    @FXML
    private Button addCargoButton;
    @FXML
    private Button editCargoButton;
    @FXML
    private Button deleteCargoButton;

    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    public final ObservableList<Cargo> getCargoes() {
        return cargoes;
    }

    @FXML
    private void initialize(){
        CompanyDAO companyDao = new CompanyDAO();
        for(Company company: companyDao.findAllCompanies())
            contractorChoiceBox.getItems().add(company.getName());
        cargoNameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getName()));
        cargoUnitsColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(this.cargoUnitsMap.get(
                dataValue.getValue().getName()).toString()));
        cargoVolumeColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getVolume().toString()));
        cargoWeightColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getWeight().toString()));
        this.cargoes = FXCollections.observableArrayList();
        cargoTable.setItems(cargoes);

        acceptButton.disableProperty().bind(
                Bindings.isEmpty(purchaseField.textProperty())
                        .or(Bindings.isEmpty(fromCityField.textProperty()))
                        .or(Bindings.isEmpty(fromCountryField.textProperty()))
                        .or(Bindings.isEmpty(fromPostalCodeField.textProperty()))
                        .or(Bindings.isEmpty(fromStreetField.textProperty()))
                        .or(Bindings.isEmpty(destinationCityField.textProperty()))
                        .or(Bindings.isEmpty(destinationCountryField.textProperty()))
                        .or(Bindings.isEmpty(destinationPostalCodeField.textProperty()))
                        .or(Bindings.isEmpty(destinationStreetField.textProperty()))
        );
    }

    private void updateCargoTable(){
        cargoes.clear();
        cargoes.addAll(cargoTypesMap.values());
    }

    @FXML
    private void handleAcceptButtonAction(){
        String contractorName = contractorChoiceBox.getValue();
        Double money = Double.valueOf(purchaseField.getText()); //money money money
        LocalDate transactionDate = transactionDatePicker.getValue();
        //from
        String street = fromStreetField.getText();
        String city = fromCityField.getText();
        String postalCode = fromPostalCodeField.getText();
        String country = fromCountryField.getText();
        //destination
        String destStreet = destinationStreetField.getText();
        String destCity = destinationCityField.getText();
        String destPostalCode = destinationPostalCodeField.getText();
        String destCountry = destinationCountryField.getText();
        Address from = new Address(country, city, postalCode, street);
        Address destination = new Address(destCountry, destCity, destPostalCode, destStreet);

        CompanyDAO companyDAO = new CompanyDAO();
        Company company = companyDAO.findByName(contractorName).get(0);
        Transaction transaction = new Transaction(company, cargoTypesMap, cargoUnitsMap,
                from, destination, money, transactionDate);
        addedObject = transaction;
        TransactionSaveCommand TSC = new TransactionSaveCommand(transaction);
        CommandRegistry.getInstance().executeCommand(TSC);

        dialogStage.close();
    }

    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }

    @FXML
    private void handleAddCargoButtonAction(){
        FXMLLoader loader = new FXMLLoader();
        try {
            URL url = new URL(new URL("file:"), "src/main/java/app/view/AddCargo.fxml");
            loader.setLocation(url);
            Pane page = loader.load();
            Scene scene = new Scene(page);
            URL url2 = new URL(new URL("file:"), "src/main/resources/darkTheme.css");
            File f = new File(url2.getFile());
            scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

            Stage dialogStage2 = new Stage();
            dialogStage2.setTitle("Transport Company Application - Add Transaction - Add Cargo");
            dialogStage2.initModality(Modality.WINDOW_MODAL);
            dialogStage2.initOwner(dialogStage);

            AddCargoPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage2);
            presenter.setCargoTypes(cargoTypesMap);
            presenter.setCargoUnits(cargoUnitsMap);
            dialogStage2.setScene(scene);
            dialogStage2.showAndWait();
            updateCargoTable();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditCargoButtonAction() {
        FXMLLoader loader = new FXMLLoader();
        try {
            URL url = new URL(new URL("file:"), "src/main/java/app/view/EditCargo.fxml");
            loader.setLocation(url);


            Stage dialogStage2 = new Stage();
            dialogStage2.setTitle("Transport Company Application - Add Transaction - Edit Cargo");
            dialogStage2.initModality(Modality.WINDOW_MODAL);
            dialogStage2.initOwner(dialogStage);

            Pane page = loader.load();
            EditCargoPresenter presenter = loader.getController();
            presenter.setCargoUnits(cargoUnitsMap);
            presenter.setCargoTypes(cargoTypesMap);
            presenter.setOldObject(cargoTable.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(page);
            URL url2 = new URL(new URL("file:"), "src/main/resources/darkTheme.css");
            File f = new File(url2.getFile());
            scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
            presenter.setDialogStage(dialogStage2);
            dialogStage2.setScene(scene);
            dialogStage2.showAndWait();
            updateCargoTable();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteCargoButtonAction() {
        cargoTypesMap.remove(cargoTable.getSelectionModel().getSelectedItem().getName());
        cargoUnitsMap.remove(cargoTable.getSelectionModel().getSelectedItem().getName());
        updateCargoTable();
    }
}
