package app.presenter.selectedPresenter;

import app.dao.TransportDAO;
import app.model.Cargo;
import app.model.CurrentTransaction;
import app.model.Transport;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SelectedCurrentTransactionPresenter extends SelectedPresenter {
    private CurrentTransaction currentTransaction;
    private ObservableList<Cargo> cargo = FXCollections.observableArrayList();
    private ObservableList<Transport> transports = FXCollections.observableArrayList();


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
    private Button transportButton;
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
    private void initialize(){
        cargoNameColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getName()));
        cargoUnitsColumn.setCellValueFactory(value -> new SimpleStringProperty(
                currentTransaction.getTransaction().getCargo().get(value.getValue().getName()).toString()));
        cargoLeftColumn.setCellValueFactory(value -> new SimpleStringProperty
                (currentTransaction.getCargoLeft().containsKey(value.getValue().getName()) ?
                        currentTransaction.getCargoLeft().get(value.getValue().getName()).toString() : "0"));
        cargoVolumeColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getVolume().toString()));
        cargoWeightColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getWeight().toString()));
        cargoTable.setItems(cargo);
        transportTableView.setItems(transports);
        transportIdColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue()._id.toString()));
        transportDateColumn.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getDepartureDate().toString()));
        transportTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        transportButton.disableProperty().bind(
                Bindings.size(
                        transportTableView.getSelectionModel()
                                .getSelectedItems()).isNotEqualTo(1));
    }

    @Override
    public void setOldObject(Object object){
        this.currentTransaction = (CurrentTransaction) object;
        this.transactionLabel.setText("Transaction ID: " + currentTransaction.getTransaction()._id);
        companyLabel.setText("Company: " + currentTransaction.getTransaction().getContractor().getName());
        fromLabel.setText("From: " + currentTransaction.getTransaction().getOrigin());
        destinationLabel.setText("Destination: " + currentTransaction.getTransaction().getDestination());
        transactionDateLabel.setText("Date: " + currentTransaction.getTransaction().getTransactionDate());
        transactionPurchaseLabel.setText("Purchase: " + currentTransaction.getTransaction().getMoney().toString());
        cargo.addAll(currentTransaction.getTransaction().getCargoTypes().values());
        System.out.println(currentTransaction.getTransaction().getCargo().values());
        System.out.println(currentTransaction.getTransaction().getCargoTypes().values());
        cargoTable.refresh();
        transports.addAll(new TransportDAO().findByCurrentTransaction(currentTransaction._id));
        transportTableView.refresh();
    }

    @FXML
    private void handleCompanyLabelAction(){
        appPresenter.showSelectedCompany(currentTransaction.getTransaction().getContractor(), dialogStage);
    }

    @FXML
    private void handleTransportButtonAction(){
//        TODO
    }
}
