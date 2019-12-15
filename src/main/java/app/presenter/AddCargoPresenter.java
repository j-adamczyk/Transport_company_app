package app.presenter;

import app.command.CompanySaveCommand;
import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Cargo;
import app.model.Company;
import app.model.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;

public class AddCargoPresenter {
    private Stage dialogStage;
    private Map<String, Cargo> cargoTypesMap;  // map Cargo.name -> Cargo
    private Map<String, Integer> cargoUnitsMap;
    @FXML
    private TextField name;
    @FXML
    private TextField units;
    @FXML
    private TextField volume;
    @FXML
    private TextField weight;

    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;
    @FXML
    private void handleAcceptButtonAction(){
        String cargoName = name.getText();
        Integer cargoUnits = Integer.valueOf(units.getText());
        Double cargoVolume = Double.valueOf(volume.getText());
        Double cargoWeight = Double.valueOf(weight.getText());
        Cargo cargo = new Cargo(cargoName, cargoVolume, cargoWeight);
        cargoTypesMap.put(cargoName, cargo);
        cargoUnitsMap.put(cargoName, cargoUnits);
    }
    @FXML
    private void handleCancelButtonAction(){
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setCargoTypes(Map<String, Cargo> cargoTypes) {
        this.cargoTypesMap = cargoTypes;
    }

    public void setCargoUnits(Map<String, Integer> cargoUnits) {
        this.cargoUnitsMap = cargoUnits;
    }
}
