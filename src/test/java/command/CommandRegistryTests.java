package command;

import app.command.Command;
import app.command.CommandRegistry;
import javafx.collections.FXCollections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CommandRegistryTests {
    CommandRegistry commandRegistry = CommandRegistry.getInstance();

    @Before
    public void clearRegistryStacks() {
        commandRegistry.setCommandStack(FXCollections
                .observableArrayList());
        commandRegistry.setUndoCommandStack(FXCollections
                .observableArrayList());
    }

    @Test
    public void testExecute() {
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        assertEquals(0, commandRegistry.getCommandStack().size());

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        assertEquals(2, commandRegistry.getCommandStack().size());
    }

    @Test
    public void testUndo() {
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        commandRegistry.undo();
        assertEquals(1, commandRegistry.getCommandStack().size());
        assertEquals(1, commandRegistry.getUndoCommandStack().size());

        commandRegistry.undo();
        assertEquals(0, commandRegistry.getCommandStack().size());
        assertEquals(2, commandRegistry.getUndoCommandStack().size());
    }

    @Test
    public void testRedo() {
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        commandRegistry.undo();
        commandRegistry.undo();
        commandRegistry.redo();
        assertEquals(1, commandRegistry.getCommandStack().size());
        assertEquals(1, commandRegistry.getUndoCommandStack().size());
    }

    @Test
    public void testClearingUndoCommandStackAfterExecute() {
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);
        Command command3 = Mockito.mock(Command.class);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        commandRegistry.undo();
        commandRegistry.undo();
        commandRegistry.executeCommand(command3);
        assertEquals(1, commandRegistry.getCommandStack().size());
        assertEquals(0, commandRegistry.getUndoCommandStack().size());
    }
}
