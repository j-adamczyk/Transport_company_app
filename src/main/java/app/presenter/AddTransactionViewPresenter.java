package app.presenter;

import app.model.Cargo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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

            Stage dialogStage2 = new Stage();
            dialogStage2.setTitle("Transport Company Application - Add Transaction - Add Cargo");
            dialogStage2.initModality(Modality.WINDOW_MODAL);
            dialogStage2.initOwner(dialogStage);

            ((AddCargoPresenter) loader.getController()).setDialogStage(dialogStage2);
            dialogStage2.setScene(scene);
            dialogStage2.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
