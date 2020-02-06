package app.presenter.selectedPresenter;

import app.presenter.MainAppPresenter;
import javafx.stage.Stage;

public class SelectedPresenter {
    protected Stage dialogStage;
    protected Object selectedObject;
    protected Object oldObject;

    protected MainAppPresenter appPresenter;

    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }
    public void setOldObject(Object oldObject) {
        this.oldObject = oldObject;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public Object getSelectedObject() {
        return selectedObject;
    }

}
