package app.presenter.selectedPresenter;

import app.command.CommandRegistry;
import app.presenter.MainAppPresenter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public abstract class SelectedPresenter {
    protected Stage dialogStage;
    protected Object selectedObject;
    protected Object oldObject;

    protected MainAppPresenter appPresenter;
    private KeyCombination keyCombinationUndo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    private KeyCombination keyCombinationRedo = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);

    protected abstract void afterUndoRedo();


    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
    }
    public void setOldObject(Object oldObject) {
        this.oldObject = oldObject;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dialogStage.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (keyCombinationRedo.match(event)) {
                System.out.println("works redo");
                CommandRegistry.getInstance().redo();
                afterUndoRedo();
            } else if (keyCombinationUndo.match(event)) {
                System.out.println("works undo");
                CommandRegistry.getInstance().undo();
                afterUndoRedo();
            }
        });
    }
    public Object getSelectedObject() {
        return selectedObject;
    }

}
