package seedu.task.model;

import javafx.collections.ObservableList;
import seedu.task.model.tag.Tag;
import seedu.task.model.task.ReadOnlyTask;

/**
 * Unmodifiable view of an task manager
 */
public interface ReadOnlyTaskManager {

    /**
     * Returns an unmodifiable view of the tasks list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<ReadOnlyTask> getTaskList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    //@@author A0139161J
    /**
     * Returns an unmodifiable view of the completed tasks list
     * This list will not contain any duplicate tasks.
     */
    ObservableList<ReadOnlyTask> getCompletedTaskList();

    /**
     * Returns an unmodifiable view of the overdue tasks list
     * This list will not contain any duplicate tasks.
     * @return
     */
    ObservableList<ReadOnlyTask> getOverdueTaskList();

}
