package app.presenter;

import app.command.CompanySaveCommand;
import app.dao.CompanyDAO;
import app.model.Address;
import app.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCargoPresenter {
    private Stage dialogStage;
    @FXML
    TextField name;
    @FXML
    TextField units;
    @FXML
    TextField volume;
    @FXML
    TextField weight;

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
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
}
