package duke;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import duke.task.Task;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    public void showExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList tasks) {
        ArrayList<Task> taskArrayList = tasks.getTasks();
        if (taskArrayList.isEmpty()) {
            System.out.println("The task list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskArrayList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showNumTasks(TaskList tasks) {
        System.out.println("Now you have " + tasks.getTasksSize() + " tasks in the list.");
    }


    public void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public String showMarkAsUndoneMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + task.toString();
    }

    public void showDeleteMessage(Task removedTask, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n" + removedTask.toString());
        showNumTasks(tasks);
    }

    public void showAddTaskMessage(Task newTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n " + newTask.toString());
        showNumTasks(tasks);
    }
}
