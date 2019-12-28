package app.presenter;

import javafx.stage.Stage;

public class DialogPresenter {
    protected Stage dialogStage;
    protected Object addedObject;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public Object getAddedObject() {
        return addedObject;
    }
}
