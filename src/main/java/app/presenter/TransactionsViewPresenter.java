package app.presenter;

import app.model.Cargo;
import app.model.Transaction;
import app.model.Transport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TransactionsViewPresenter {
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
    private TableView<Transaction> transactionTableView;
    @FXML
    private TableColumn<Transaction, String> transactionIdColumn;
    @FXML
    private TableColumn<Transaction, String> transactionDateColumn;
    @FXML
    private TableColumn<Transaction, String> transactionContractorColumn;
    @FXML
    private TableColumn<Transaction, String> transactionFromColumn;
    @FXML
    private TableColumn<Transaction, String> transactionToColumn;
    @FXML
    private TableColumn<Transaction, String> transactionPurchaseColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button seeCompanyButton;
    @FXML
    private Button seeCurrentStateButton;

    @FXML
    private void handleAddButtonAction(){
//        TODO
    }
    @FXML
    private void handleDeleteButtonAction(){
//        TODO
    }
    @FXML
    private void handleEditButtonAction(){
//        TODO
    }
    @FXML
    private void handleSeeCurrentTransactionButtonAction(){
//        TODO
    }
    @FXML
    private void handleSeeCompanyButtonAction(){
//        TODO
    }
}
