package app.command;

/**
 * Interface for Commands, mostly implementing CRUD operations.
 * Increases chances for database consistency with undo/redo methods
 * (it's not guaranteed due to lack of ACID in MongoDB).
 *
 * Constructors of implementing classes is the ONLY method that should be used directly,
 * with other commands use CommandRegistry.
 */
public interface Command {
    /**
     * Execute command, add it to command stack.
     * This method should NOT be called directly, use CommandRegistry executeCommand() method instead.
     */
    void execute();

    /**
     * Undo last command, put in on undo stack in CommandRegistry.
     * This method should NOT be called directly, use CommandRegistry undo() method instead.
     */
    void undo();

    /**
     * Redo command after undo().
     * Note that this method clears command stack for undo() to free space, so it should
     * be used with caution.
     * This method should NOT be called directly, use CommandRegistry redo() method instead.
     */
    void redo();
}
