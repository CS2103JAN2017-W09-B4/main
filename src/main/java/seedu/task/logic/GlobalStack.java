package seedu.task.logic;

import java.util.NoSuchElementException;
import java.util.Stack;

import seedu.task.model.TaskManager;
import seedu.task.model.task.ReadOnlyTask;
import seedu.task.model.task.Task;

//@@author A0139161J
/**
 * Contains 2 stacks for the undo/redo function to operate
 */
public class GlobalStack {

    private static GlobalStack instance = null;
    private Stack<Object> undoStack;
    private Stack<Object> redoStack;
    public static final String MESSAGE_NOTHING_TO_UNDO = "No commands left to undo";
    public static final String MESSAGE_NOTHING_TO_REDO = "No commands left to redo";

    private GlobalStack() {
        undoStack = new Stack<Object>();
        redoStack = new Stack<Object>();
    }

    public static GlobalStack getInstance() {
        if (instance == null) {
            instance = new GlobalStack();
        }
        return instance;
    }

    //================Undo/Redo Edit Command====================

    /**
     * For undo-edit function
     */
    public Task undoGetOriginalTask() {
        Task originalTask = (Task) undoStack.pop();
        redoStack.push(originalTask);
        return originalTask;
    }

    /**
     * For undo-edit function
     */
    public Task undoGetEditedTask() {
        Task editedTask = (Task) undoStack.pop();
        redoStack.push(editedTask);
        return editedTask;
    }

    /**
     * For redo-edit function
     */
    public Task redoGetEditedTask() {
        Task editedTask = (Task) redoStack.pop();
        undoStack.push(editedTask);
        return editedTask;
    }

    /**
     * For redo-edit function
     */
    public Task redoGetOriginalTask() {
        Task originalTask = (Task) redoStack.pop();
        undoStack.push(originalTask);
        return originalTask;
    }

    //================Undo/Redo Add Command====================

    public Task undoAdd() throws NoSuchElementException {
        try {
            Task temp = (Task) undoStack.pop();
            redoStack.push(temp);
            return temp;
        } catch (NoSuchElementException ne) {
            throw new NoSuchElementException(MESSAGE_NOTHING_TO_UNDO);
        }
    }

    public Task redoAdd() throws NoSuchElementException {
        try {
            Task temp = (Task) redoStack.pop();
            undoStack.push(temp);
            return temp;
        } catch (NoSuchElementException ne) {
            throw new NoSuchElementException(MESSAGE_NOTHING_TO_REDO);
        }
    }

    //================Undo/Redo Delete Command===================

    public ReadOnlyTask undoDelete() {
        ReadOnlyTask undoTask = (ReadOnlyTask) undoStack.pop();
        redoStack.push(undoTask);
        return undoTask;
    }

    public ReadOnlyTask redoDelete() {
        ReadOnlyTask redoTask = (ReadOnlyTask) redoStack.pop();
        undoStack.push(redoTask);
        return redoTask;
    }

    //================Undo/Redo Clear Command====================

    public TaskManager undoClear() {
        TaskManager toUndo = (TaskManager) undoStack.pop();
        redoStack.push(toUndo);
        return toUndo;
    }

    public void redoClear() {
        undoStack.push(redoStack.pop());
    }

    //================ Util/Getter Methods ==============================

    public Stack<Object> getUndoStack() {
        return undoStack;
    }

    public Stack<Object> getRedoStack() {
        return redoStack;
    }
}
