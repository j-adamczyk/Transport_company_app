package app.presenter.addPresenter;

import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Cargo;
import app.model.Company;
import app.model.Transaction;
import javafx.beans.binding.Bindings;
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
    private void initialize(){
        acceptButton.disableProperty().bind(
                Bindings.isEmpty(name.textProperty())
                        .or(Bindings.isEmpty(units.textProperty()))
                        .or(Bindings.isEmpty(volume.textProperty()))
                        .or(Bindings.isEmpty(weight.textProperty()))
        );
    }

    @FXML
    private void handleAcceptButtonAction(){
        String cargoName = name.getText();
        Integer cargoUnits = Integer.valueOf(units.getText());
        Double cargoVolume = Double.valueOf(volume.getText());
        Double cargoWeight = Double.valueOf(weight.getText());
        Cargo cargo = new Cargo(cargoName, cargoVolume, cargoWeight);
        cargoTypesMap.put(cargoName, cargo);
        cargoUnitsMap.put(cargoName, cargoUnits);
        dialogStage.close();
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
