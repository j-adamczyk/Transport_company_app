package app.presenter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewPresenter extends SwitchPresenter {

    private MainAppPresenter appPresenter;

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
//        TODO
    }
    @FXML
    private void handleDriversButtonAction(){
        appPresenter.showDriversView();
    }
    @FXML
    private void handleVehiclesButtonAction(){
//        TODO
    }
    @FXML
    private void handleCompaniesButtonAction(){
//        TODO
    }
    @FXML
    private void handleTransactionsButtonAction(){
//        TODO
    }

    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }
}
