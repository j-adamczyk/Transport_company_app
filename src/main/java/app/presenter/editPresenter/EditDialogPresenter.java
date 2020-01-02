package app.presenter.editPresenter;

import javafx.stage.Stage;

public class EditDialogPresenter {
    protected Stage dialogStage;
    protected Object oldObject;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOldObject(Object oldObject) {
        this.oldObject = oldObject;
    }
}
