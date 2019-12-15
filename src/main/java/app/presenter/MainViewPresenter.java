package app.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewPresenter extends SwitchPresenter {

    @FXML
    private Button transportsButton;
    @FXML
    private Button driversButton;
    @FXML
    private Button vehiclesButton;
    @FXML
    private Button companiesButton;
    @FXML
    private Button transactionsButton;

    @FXML
    private void handleTransportsButtonAction(){
        appPresenter.showTransportsView();
    }
    @FXML
    private void handleDriversButtonAction(){
        appPresenter.showDriversView();
    }
    @FXML
    private void handleVehiclesButtonAction(){
        appPresenter.showVehiclesView();
    }
    @FXML
    private void handleCompaniesButtonAction(){
        appPresenter.showCompaniesView();
    }
    @FXML
    private void handleTransactionsButtonAction(){
        appPresenter.showTransactionsView();
    }

    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }
}
