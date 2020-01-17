package app.presenter;

import app.command.CommandRegistry;
import app.model.*;
import app.presenter.addPresenter.DialogPresenter;
import app.presenter.editPresenter.EditDialogPresenter;
import app.presenter.overviewPresenter.SwitchPresenter;
import app.presenter.selectedPresenter.SelectedPresenter;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

    public void showEditVehicleView(Vehicle vehicle) {
        showEditDialogScene(vehicle, "EditVehicleView", "Vehicle");
    }

    public void showEditTransportView(Transport transport) {
        showEditDialogScene(transport, "EditTransportView", "Transport");
    }

    public CurrentTransaction showSelectedCurrentTransaction(CurrentTransaction currentTransaction, Stage owner) {
        return (CurrentTransaction) showSelectedDialogScene(currentTransaction, "SelectedCurrentTransactionView", "Current Transaction", owner);
    }

    public Company showSelectedCompany(Company company, Stage owner) {
        return (Company) showSelectedDialogScene(company, "SelectedCompanyView", "Company", owner);
    }

    public Transaction showSelectedTransaction(Transaction transaction, Stage owner){
        return (Transaction) showSelectedDialogScene(transaction, "SelectedTransactionView", "Transaction", owner);
    }

    public Driver showSelectedDriver(Driver driver, Stage owner) {
        return (Driver) showSelectedDialogScene(driver, "SelectedDriverView", "Driver", owner);
    }

    public Vehicle showSelectedVehicle(Vehicle vehicle, Stage owner) {
        return (Vehicle) showSelectedDialogScene(vehicle, "SelectedVehicleView", "Vehicle", owner);
    }

    public void showSelectedTransport(Transport transport, Stage owner) {
        showSelectedDialogScene(transport, "SelectedTransportView", "Transport", owner);
    }


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
            CommandRegistry.getInstance().clear();
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
            presenter.setAppPresenter(this);
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
            presenter.setAppPresenter(this);
            presenter.setDialogStage(dialogStage);
            presenter.setOldObject(oldObject);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Object showSelectedDialogScene(Object oldObject, String viewName, String title, Stage owner){
        FXMLLoader loader = new FXMLLoader();
        try{
            URL url = new URL(new URL("file:"), "src/main/java/app/view/"+ viewName +".fxml");
            loader.setLocation(url);
            Pane page = loader.load();
            Scene scene = new Scene(page);

            Stage dialogStage = new Stage();
            dialogStage.setTitle(TITLE + " - Selected " + title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(owner);

            SelectedPresenter presenter = loader.getController();
            presenter.setDialogStage(dialogStage);
            presenter.setOldObject(oldObject);
            presenter.setAppPresenter(this);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            return presenter.getSelectedObject();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    static void showError(Thread t, Throwable e) {
        System.err.println("***Default exception handler***");
        if (Platform.isFxApplicationThread()) {
            if(e.getCause().toString().equalsIgnoreCase("java.lang.reflect.InvocationTargetException"))
                showErrorDialog(e, "Invalid value!");
                e.printStackTrace();
        } else {
            System.err.println("An unexpected error occurred in " + t);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void showErrorDialog(Throwable e, String message) {
        StringWriter errorMsg = new StringWriter();
        e.printStackTrace(new PrintWriter(errorMsg));
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();

        try {
            URL url = new URL(new URL("file:"), "src/main/java/app/view/ErrorView.fxml");
            loader.setLocation(url);
            Parent root = loader.load();
            ((ErrorViewPresenter)loader.getController()).setErrorText(message);
            dialog.setScene(new Scene(root, 300, 200));
            dialog.show();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
