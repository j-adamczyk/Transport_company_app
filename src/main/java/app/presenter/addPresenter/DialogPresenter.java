package app.presenter.addPresenter;

import app.presenter.MainAppPresenter;
import javafx.stage.Stage;

public class DialogPresenter {
    protected Stage dialogStage;
    protected Object addedObject;
    protected MainAppPresenter appPresenter;

    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public Object getAddedObject() {
        return addedObject;
    }
}
