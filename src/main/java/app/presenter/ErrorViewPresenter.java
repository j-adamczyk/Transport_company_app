package app.presenter;

import app.presenter.editPresenter.EditDialogPresenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ErrorViewPresenter extends EditDialogPresenter {

    @FXML
    private Button acceptButton;

    @FXML
    private Label errorMessage ;

    public void setErrorText(String text) {
        errorMessage.setText(text);
    }

    @FXML
    private void close() {
        errorMessage.getScene().getWindow().hide();
    }
}
