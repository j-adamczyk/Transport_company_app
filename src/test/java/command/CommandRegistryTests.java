package command;

import app.command.Command;
import app.command.CommandRegistry;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CommandRegistryTests {
    @Test
    public void testExecute() {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        assertEquals(commandRegistry.getCommandStack().size(), 0);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        assertEquals(commandRegistry.getCommandStack().size(), 2);
    }

    @Test
    public void testUndo() {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        commandRegistry.undo();
        assertEquals(commandRegistry.getCommandStack().size(), 1);
        assertEquals(commandRegistry.getUndoCommandStack().size(), 1);

        commandRegistry.undo();
        assertEquals(commandRegistry.getCommandStack().size(), 0);
        assertEquals(commandRegistry.getUndoCommandStack().size(), 2);
    }

    @Test
    public void testRedo() {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        commandRegistry.undo();
        commandRegistry.undo();
        commandRegistry.redo();
        assertEquals(commandRegistry.getCommandStack().size(), 1);
        assertEquals(commandRegistry.getUndoCommandStack().size(), 1);
    }

    @Test
    public void testClearingUndoCommandStackAfterExecute() {
        CommandRegistry commandRegistry = CommandRegistry.getInstance();
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);
        Command command3 = Mockito.mock(Command.class);

        commandRegistry.executeCommand(command1);
        commandRegistry.executeCommand(command2);

        commandRegistry.undo();
        commandRegistry.undo();
        commandRegistry.executeCommand(command3);
        assertEquals(commandRegistry.getCommandStack().size(), 1);
        assertEquals(commandRegistry.getUndoCommandStack().size(), 0);
    }
}
