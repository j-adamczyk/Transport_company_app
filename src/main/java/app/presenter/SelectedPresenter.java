package app.presenter;

import javafx.stage.Stage;

public class SelectedPresenter {
    protected Stage dialogStage;
    protected Object selectedObject;
    protected Object oldObject;


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
