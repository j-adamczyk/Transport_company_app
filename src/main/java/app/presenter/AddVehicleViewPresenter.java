package app.presenter;

import app.command.VehicleSaveCommand;
import app.dao.VehicleDAO;
import app.model.Vehicle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddVehicleViewPresenter extends DialogPresenter{

    @FXML
    private TextField modelField;
    @FXML
    private TextField registrationField;
    @FXML
    private DatePicker manufactureDatePicker;
    @FXML
    private TextField cargoVolumeField;
    @FXML
    private TextField cargoWeightField;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    @FXML
    private void initialize(){
        acceptButton.disableProperty().bind(
                Bindings.isEmpty(modelField.textProperty())
                        .or(Bindings.isEmpty(registrationField.textProperty()))
                        .or(Bindings.isEmpty(cargoVolumeField.textProperty()))
                        .or(Bindings.isEmpty(cargoWeightField.textProperty())));

    }
    @FXML
    private void handleAcceptButtonAction(){
        String model = modelField.getText();
        String registrationNo = registrationField.getText();
        LocalDate manufactureDate = manufactureDatePicker.getValue();
        Double cargoWeight = Double.valueOf(cargoWeightField.getText());
        Double cargoVolume = Double.valueOf(cargoVolumeField.getText());
        addedObject = new Vehicle(model, registrationNo, manufactureDate, cargoVolume, cargoWeight);
        VehicleSaveCommand VSC = new VehicleSaveCommand((Vehicle) addedObject);
        VSC.execute();
        dialogStage.close();
    }
    @FXML
    private void handleCancelButtonAction(){
            dialogStage.close();
    }
}
