package app.presenter;

import app.model.Cargo;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddTransactionViewPresenter extends DialogPresenter{
    @FXML
    private ChoiceBox<String> contractor;
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
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void handleAcceptButtonAction(){
//        TODO
    }
    @FXML
    private void handleCancelButtonAction(){
//        TODO
    }
    @FXML
    private void handleAddCargoButtonAction(){
//        TODO
    }
}
