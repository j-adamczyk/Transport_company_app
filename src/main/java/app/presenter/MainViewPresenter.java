package app.presenter;

import app.presenter.overviewPresenter.SwitchPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

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
    private ImageView image;

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

    @FXML
    private void initialize(){
        try {
            URL url = new URL(new URL("file:"), "src/logo.png");
            Image im = new Image(url.toString());
            image.setImage(im);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            MainAppPresenter.showErrorDialog(e, "Cannot reach logo.");
        }

    }

    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }

    @Override
    protected void afterUndoRedo() {
    }
}
