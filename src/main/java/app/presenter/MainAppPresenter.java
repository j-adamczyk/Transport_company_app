package app.presenter;

import app.TransportCompanyApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public void showAddCompanyView() {
        showAddDialogScene("AddCompanyView", "Company");
    }

    public void showAddDriverView() {
        showAddDialogScene("AddDriverView", "Driver");
    }

    public void showAddTransactionView() {
        showAddDialogScene("AddTransactionView", "Transaction");
    }

    public void showAddVehicleView(){
        showAddDialogScene("AddVehicleView", "Vehicle");
    }

    public void showAddTransportView(){
        showAddDialogScene("AddTransportView", "Transport");
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showAddDialogScene(String viewName, String title){
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

            ((DialogPresenter) loader.getController()).setDialogStage(dialogStage);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
