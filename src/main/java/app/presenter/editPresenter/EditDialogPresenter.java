package app.presenter.editPresenter;

import app.presenter.MainAppPresenter;
import javafx.stage.Stage;

public class EditDialogPresenter {
    protected Stage dialogStage;
    protected Object oldObject;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    protected MainAppPresenter appPresenter;

    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }
    public void setOldObject(Object oldObject) {
        this.oldObject = oldObject;
    }
}
