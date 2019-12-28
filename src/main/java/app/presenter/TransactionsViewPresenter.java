package app.presenter;

import app.command.TransactionDeleteCommand;
import app.dao.CurrentTransactionDAO;
import app.dao.TransactionDAO;
import app.model.Cargo;
import app.model.CurrentTransaction;
import app.model.Transaction;
import app.model.Transport;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

public class TransactionsViewPresenter extends SwitchPresenter{
    private ObservableList<Transaction> transactions;
    private ObservableList<Cargo> cargo;
    private Map<String, Cargo> cargoTypesMap = new HashMap<>();
    private Map<String, Integer> cargoUnitsMap = new HashMap<>();
    private int selectedRaw = -1;

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
    private Label returnLabel;

    @FXML
    private void initialize(){
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        transactionContractorColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getContractor().toString()));
        transactionDateColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getTransactionDate().toString()));
        transactionFromColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getOrigin().toString()));
        transactionToColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getDestination().toString()));
        transactionPurchaseColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getMoney().toString()));
        transactionIdColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().get_id().toString()));
        cargoNameColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getName()));
        cargoUnitsColumn.setCellValueFactory(value -> new SimpleStringProperty
                (transactionTableView.getSelectionModel().getSelectedItem()==null ? ""
                        : transactionTableView.getSelectionModel().getSelectedItem().getCargo().get(value.getValue().getName()).toString()));
        cargoVolumeColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getVolume().toString()));
        cargoWeightColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getWeight().toString()));
        this.transactions = FXCollections.observableArrayList();
        this.cargo = FXCollections.observableArrayList();
        transactions.addAll(transactionDAO.findAllTransactions());
        transactionTableView.setItems(transactions);
        cargoTable.setItems(cargo);

        editButton.disableProperty().bind(
                Bindings.size(
                        transactionTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        deleteButton.disableProperty().bind(
                Bindings.size(
                        transactionTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        seeCompanyButton.disableProperty().bind(
                Bindings.size(
                        transactionTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
        seeCurrentStateButton.disableProperty().bind(
                Bindings.size(
                        transactionTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));

    }

    @FXML
    private void handleAddButtonAction(){
        Transaction newTransaction = appPresenter.showAddTransactionView();
        if(newTransaction!=null) {
            transactions.add(newTransaction);
            transactionTableView.refresh();
            selectedRaw = -1;
            transactionTableView.getSelectionModel().select(newTransaction);
            handleTransactionSelected();
        }
//        TODO
//        IMPORTANT: Add Current Transaction Also!!!
    }
    @FXML
    private void handleDeleteButtonAction(){
        Transaction toRemove = transactionTableView.getSelectionModel().getSelectedItem();
        TransactionDeleteCommand tdc = new TransactionDeleteCommand(toRemove.get_id());
        tdc.execute();
        transactions.remove(toRemove);
        transactionTableView.refresh();
        selectedRaw = -1;
        handleTransactionSelected();
    }
    @FXML
    private void handleEditButtonAction(){
        appPresenter.showEditTransactionView(transactionTableView.getSelectionModel().getSelectedItem());
        transactionTableView.refresh();
        selectedRaw = -1;
        handleTransactionSelected();
//        Transaction transaction = new TransactionDAO().findAllTransactions().get(1);
//        System.out.println(transaction);
//        appPresenter.showEditTransactionView(transaction);
    }
    @FXML
    private void handleSeeCurrentTransactionButtonAction(){
        CurrentTransaction currentTransaction = new CurrentTransactionDAO()
                .findByTransactionId(transactionTableView.getSelectionModel().getSelectedItem()._id);
        appPresenter.showSelectedCurrentTransaction(currentTransaction);
    }
    @FXML
    private void handleSeeCompanyButtonAction(){
//        TODO
    }

    @FXML
    private void handleReturnLabel() {
        appPresenter.showMainView();
    }

    @FXML
    private void handleTransactionSelected(){
        if(transactionTableView.getSelectionModel().getSelectedIndex() != selectedRaw){
            selectedRaw = transactionTableView.getSelectionModel().getSelectedIndex();
            cargo.clear();
            Transaction transactionSelected = transactionTableView.getSelectionModel().getSelectedItem();
            cargo.addAll(transactionSelected.getCargoTypes().values());
            cargoTable.refresh();
        }
    }
}
