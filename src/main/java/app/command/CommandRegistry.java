package app.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Main class for Command pattern and passing commands between DAO and thr rest of app.
 */
public class CommandRegistry {
    private static final CommandRegistry instance = new CommandRegistry();

    private CommandRegistry() {}

    public static CommandRegistry getInstance() {
        return instance;
    }

    private ObservableList<Command> commandStack = FXCollections
            .observableArrayList();

    private ObservableList<Command> undoCommandStack = FXCollections
            .observableArrayList();

    /**
     * Execute passed command and add it to stack. Beware that this method clears undoCommandStack!
     * @param command command to be executed
     */
    public void executeCommand(Command command) {
        command.execute();
        commandStack.add(command);

        undoCommandStack.clear();
    }

    public ObservableList<Command> getCommandStack() {
        return commandStack;
    }

    public ObservableList<Command> getUndoCommandStack() {
        return undoCommandStack;
    }

    /**
     * Undo last executed command and add it to undoCommandStack.
     */
    public void undo() {
        if (commandStack.isEmpty())
            return;

        Command lastCommand = commandStack.get(commandStack.size() - 1);
        commandStack.remove(commandStack.size() - 1);

        lastCommand.undo();

        undoCommandStack.add(lastCommand);
    }

    /**
     * Redo last command from undoCommandStack and att it to commandStack.
     */
    public void redo() {
        if (undoCommandStack.isEmpty())
            return;

        Command lastUndoCommand = undoCommandStack.get(undoCommandStack.size() - 1);
        undoCommandStack.remove(undoCommandStack.size() - 1);

        lastUndoCommand.redo();

        commandStack.add(lastUndoCommand);
    }
}
