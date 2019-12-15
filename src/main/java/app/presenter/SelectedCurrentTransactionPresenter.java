package app.presenter;

import app.model.Cargo;
import app.model.CurrentTransaction;
import app.model.Transport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class SelectedCurrentTransactionPresenter {
    private CurrentTransaction currentTransaction;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setData(CurrentTransaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @FXML
    private Label transactionLabel;
    @FXML
    private Label companyLabel;
    @FXML
    private Label fromLabel;
    @FXML
    private Label destinationLabel;
    @FXML
    private Label transactionDateLabel;
    @FXML
    private Label transactionPurchaseLabel;
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
    private TableColumn<Cargo, String> cargoLeftColumn;
    @FXML
    private Button editCurrentTransaction;
    @FXML
    private TableView<Transport> transportTableView;
    @FXML
    private TableColumn<Transport, String> transportIdColumn;
    @FXML
    private TableColumn<Transport, String> transportDateColumn;
    @FXML
    private TableColumn<Transport, String> transportCargoColumn;
    @FXML
    private TableColumn<Transport, String> transportCargoUnitsColumn;

    @FXML
    private void handleEditCurrentTransactionButton(){
//        TODO
    }

    @FXML
    private void handleCompanyLabelAction(){
//        TODO
    }
}
