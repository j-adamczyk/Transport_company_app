package app.presenter.selectedPresenter;

import app.command.TransactionDeleteCommand;
import app.dao.CurrentTransactionDAO;
import app.dao.TransactionDAO;
import app.model.Cargo;
import app.model.Company;
import app.model.CurrentTransaction;
import app.model.Transaction;
import app.presenter.overviewPresenter.SwitchPresenter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;
import java.util.Map;

public class SelectedTransactionPresenter extends SelectedPresenter{

        private ObservableList<Transaction> transactions;
        private ObservableList<Cargo> cargo;
        private Map<String, Cargo> cargoTypesMap = new HashMap<>();
        private Map<String, Integer> cargoUnitsMap = new HashMap<>();
        private ObjectProperty<Transaction> selectedTransactionProperty = new SimpleObjectProperty<>();

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
        private void initialize(){
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionTableView.setRowFactory( tv -> {
                TableRow<Transaction> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        dialogStage.close();
                    }
                });
                return row ;
            });
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

            transactionTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
                if (nv != null) {
                    cargo.clear();
                    selectedTransactionProperty.set(nv);
                    cargo.addAll(selectedTransactionProperty.get().getCargoTypes().values());
                    cargoTable.refresh();
                }
            });

        }

        @FXML
        private void handleAddButtonAction(){
            Transaction newTransaction = appPresenter.showAddTransactionView();
            if(newTransaction!=null) {
                transactions.add(newTransaction);
                transactionTableView.refresh();
                transactionTableView.getSelectionModel().select(newTransaction);
            }
        }

        @FXML
        private void handleDeleteButtonAction(){
            Transaction toRemove = transactionTableView.getSelectionModel().getSelectedItem();
            TransactionDeleteCommand tdc = new TransactionDeleteCommand(toRemove.get_id());
            tdc.execute();
            transactions.remove(toRemove);
            transactionTableView.refresh();
        }

        @FXML
        private void handleEditButtonAction(){
            appPresenter.showEditTransactionView(transactionTableView.getSelectionModel().getSelectedItem());
            transactionTableView.refresh();
        }

        @FXML
        private void handleSeeCurrentTransactionButtonAction(){
            CurrentTransaction currentTransaction = new CurrentTransactionDAO()
                    .findByTransactionId(transactionTableView.getSelectionModel().getSelectedItem()._id);
            appPresenter.showSelectedCurrentTransaction(currentTransaction, appPresenter.getPrimaryStage());
        }

        @FXML
        private void handleSeeCompanyButtonAction(){
            Company company = transactionTableView.getSelectionModel().getSelectedItem().getContractor();
            appPresenter.showSelectedCompany(company, appPresenter.getPrimaryStage());
        }

    @Override
    public void setOldObject(Object object){
        Transaction selectedTransaction = (Transaction) object;
        transactionTableView.getSelectionModel().select(selectedTransaction);
    }

    @Override
    public Transaction getSelectedObject(){
        return this.selectedTransactionProperty.get();
    }

}
