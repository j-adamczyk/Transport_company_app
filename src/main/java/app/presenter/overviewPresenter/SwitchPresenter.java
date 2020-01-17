package app.presenter.overviewPresenter;

import app.command.CommandRegistry;
import app.presenter.MainAppPresenter;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public abstract class SwitchPresenter {
    protected MainAppPresenter appPresenter;
    private KeyCombination keyCombinationUndo = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    private KeyCombination keyCombinationRedo = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);

    protected abstract void afterUndoRedo();


    public void setAppPresenter(MainAppPresenter presenter){
        this.appPresenter = presenter;
        appPresenter.getPrimaryStage().getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
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
}
