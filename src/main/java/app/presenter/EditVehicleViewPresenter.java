package app.presenter;

import app.command.VehicleUpdateCommand;
import app.dao.VehicleDAO;
import app.model.Address;
import app.model.Vehicle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class EditVehicleViewPresenter extends EditDialogPresenter{
    @FXML
    private Label idLabel;
    @FXML
    private TextField modelField;
    @FXML
    private TextField registrationNoField;
    @FXML
    private DatePicker manufactureDateField;
    @FXML
    private TextField cargoVolumeField;
    @FXML
    private TextField cargoWeightField;
    @FXML
    private Button acceptButton;
    @FXML
    private Button cancelButton;

    private Vehicle currentVehicle;

    @Override
    public void setOldObject(Object oldVehicle){
        currentVehicle = (Vehicle) oldVehicle;
        idLabel.setText("Vehicle id: " + currentVehicle._id);
        modelField.setText(currentVehicle.getModel());
        registrationNoField.setText(currentVehicle.getRegistrationNo());
        cargoVolumeField.setText(currentVehicle.getCargoVolume().toString());
        cargoWeightField.setText(currentVehicle.getCargoWeight().toString());
        manufactureDateField.setValue(currentVehicle.getManufactureDate());
    }

    @FXML
    private void handleAcceptButtonAction(){
        String model = modelField.getText();
        String registrationNo = registrationNoField.getText();
        Double cargoVolume = Double.valueOf(cargoVolumeField.getText());
        Double cargoWeight = Double.valueOf(cargoWeightField.getText());
        LocalDate manufactureDate = manufactureDateField.getValue();
        currentVehicle.setModel(model);
        currentVehicle.setRegistrationNo(registrationNo);
        currentVehicle.setCargoVolume(cargoVolume);
        currentVehicle.setCargoWeight(cargoWeight);
        currentVehicle.setManufactureDate(manufactureDate);

        VehicleUpdateCommand VUC = new VehicleUpdateCommand(currentVehicle);
        VUC.execute();
        VehicleDAO vehicleDao = new VehicleDAO();
        System.out.println(vehicleDao.findAllVehicles());
        dialogStage.close();
    }
    @FXML
    private void handleCancelButtonAction(){
        dialogStage.close();
    }

}
