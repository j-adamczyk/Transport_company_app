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


public class SelectedCurrentTransactionPresenter extends SelectedPresenter {
    private CurrentTransaction currentTransaction;



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
    private void initialize(CurrentTransaction currentTransaction){
    }

    @Override
    public void setOldObject(Object object){
        this.currentTransaction = (CurrentTransaction) object;
        this.transactionLabel.setText("Transaction ID: " + currentTransaction.getTransaction()._id);
        companyLabel.setText("Company: " + currentTransaction.getTransaction().getContractor().toString());
        fromLabel.setText("From: " + currentTransaction.getTransaction().getOrigin());
        destinationLabel.setText("Destination");

    }

    @FXML
    private void handleCompanyLabelAction(){
//        TODO
    }
}
