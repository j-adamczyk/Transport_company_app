package app.presenter;

import app.TransportCompanyApp;
import app.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainAppPresenter {
    private Stage primaryStage;
    private String TITLE = "Transport Company Application";

    public MainAppPresenter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            this.primaryStage.setTitle(TITLE);

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            URL url = new URL(new URL("file:"), "src/main/java/app/view/MainView.fxml");
//            URL url = new URL(new URL("file:"), "src/main/java/app/view/AddCompanyView.fxml");
            loader.setLocation(url);
            Pane rootLayout = loader.load();
            MainViewPresenter presenter = loader.getController();
            presenter.setAppPresenter(this);

            // add layout to a scene and show them all
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainView(){
        switchScene("MainView", "");
    }

    public void showDriversView(){
        switchScene("DriversView", "Drivers");
    }

    public void showTransportsView(){
        switchScene("TransportsView", "Transports");
    }

    public void showCompaniesView() {
        switchScene("CompaniesView", "Companies");
    }

    public void showTransactionsView() {
        switchScene("TransactionsView", "Transactions");
    }

    public void showVehiclesView() {
        switchScene("VehiclesView", "Vehicles");
    }

    public Company showAddCompanyView() {
        return (Company)showAddDialogScene("AddCompanyView", "Company");
    }

    public Driver showAddDriverView() {
        return (Driver) showAddDialogScene("AddDriverView", "Driver");
    }

    public Transaction showAddTransactionView() {
        return (Transaction) showAddDialogScene("AddTransactionView", "Transaction");
    }

    public Vehicle showAddVehicleView(){
        return (Vehicle) showAddDialogScene("AddVehicleView", "Vehicle");
    }

    public Transport showAddTransportView(){
        return (Transport) showAddDialogScene("AddTransportView", "Transport");
    }

    public void showEditCompanyView(Company company) {showEditDialogScene(company, "EditCompanyView", "Company");}
    public void showEditDriverView(Driver driver) {showEditDialogScene(driver, "EditDriverView", "Driver");}
    public void showEditTransactionView(Transaction transaction) {showEditDialogScene(transaction, "EditTransactionView", "Transaction");}
    public void showEditVehicleView(Vehicle vehicle){showEditDialogScene(vehicle, "EditVehicleView", "Vehicle");}

    public void showSelectedCurrentTransaction(CurrentTransaction currentTransaction) {
        showSelectedDialogScene(currentTransaction, "SelectedCurrentTransactionView", "Current Transaction");}

    private void switchScene(String viewName, String title){
        this.primaryStage.setTitle(TITLE + " - " + title);
        FXMLLoader loader = new FXMLLoader();
        try {
            URL url = new URL(new URL("file:"), "src/main/java/app/view/"+ viewName +".fxml");
            loader.setLocation(url);
            Pane page = loader.load();
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            ((SwitchPresenter) loader.getController()).setAppPresenter(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Object showAddDialogScene(String viewName, String title){
        FXMLLoader loader = new FXMLLoader();
        try {
            URL url = new URL(new URL("file:"), "src/main/java/app/view/"+ viewName +".fxml");
            loader.setLocation(url);
            Pane page = loader.load();
            Scene scene = new Scene(page);

            Stage dialogStage = new Stage();
            dialogStage.setTitle(TITLE + " - Add " + title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            DialogPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            return presenter.getAddedObject();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private void showEditDialogScene(Object oldObject, String viewName, String title){
        FXMLLoader loader = new FXMLLoader();
        try{
            URL url = new URL(new URL("file:"), "src/main/java/app/view/"+ viewName +".fxml");
            loader.setLocation(url);
            Pane page = loader.load();
            Scene scene = new Scene(page);

            Stage dialogStage = new Stage();
            dialogStage.setTitle(TITLE + " - Edit " + title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            EditDialogPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setOldObject(oldObject);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Object showSelectedDialogScene(Object oldObject, String viewName, String title){
        FXMLLoader loader = new FXMLLoader();
        try{
            URL url = new URL(new URL("file:"), "src/main/java/app/view/"+ viewName +".fxml");
            loader.setLocation(url);
            Pane page = loader.load();
            Scene scene = new Scene(page);

            Stage dialogStage = new Stage();
            dialogStage.setTitle(TITLE + " - Selected " + title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            SelectedPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setOldObject(oldObject);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            return presenter.getSelectedObject();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
