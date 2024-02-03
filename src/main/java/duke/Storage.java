package duke;

import duke.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * Handles the storage of task data to and from a file.
 * This includes loading tasks from a file when the program starts
 * and saving tasks to the file when they are added, deleted, or modified.
 */
public class Storage {

    private final String file;
    private static final String FILE_PATH = "./data/jamie.txt";

    /**
     * Constructs a new Storage object.
     *
     * @param file The path of the file where tasks are stored.
     */
    public Storage(String file) {
        this.file = file;
    }


    /**
     * Loads tasks from the specified file into an ArrayList.
     * If the file does not exist, an empty list is returned.
     *
     * @return An ArrayList containing the loaded Task objects.
     * @throws FileNotFoundException If the specified file does not exist.
     * @throws JamieException If an error occurs while parsing the file data.
     */
    public ArrayList<Task> load() throws FileNotFoundException, JamieException {
        File file = new File(this.file);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (!file.exists()) {
            return loadedTasks; // Return an empty list if the file doesn't exist
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine();
            String[] splits = taskString.split(" \\| "); // Splitting each part of the task

            switch (splits[0]) {
                case "T": {
                    Task toAdd = new ToDo(splits[2], splits[1].equals("1"));
                    loadedTasks.add(toAdd);
                    break;
                }
                case "E": {
                    Task toAdd = new Event(splits[2], splits[3], splits[4], splits[1].equals("1"));
                    loadedTasks.add(toAdd);
                    break;
                }
                case "D": {
                    Task toAdd = new Deadline(splits[2], splits[3], splits[1].equals("1"));
                    loadedTasks.add(toAdd);
                    break;
                }
                default: {
                    throw new JamieException("Error occurred when reading data from storage file.\n"
                            + "Therefore, creating a new task list.");
                }
            }
        }
        scanner.close();

        return loadedTasks;
    }

    /**
     * Saves the current list of tasks to the file.
     * The tasks are converted into a specific string format before being written to the file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        String textToAdd = convertToString((tasks.getTasks()));
        writer.write(textToAdd);
        writer.close();
    }

    /**
     * Converts a list of tasks into a string format suitable for file storage.
     *
     * @param taskList The list of Task objects to be converted.
     * @return A string representation of the task list for file storage.
     */
    public String convertToString(ArrayList<Task> taskList) {
        StringBuilder textToAdd = new StringBuilder();
        for (Task curr : taskList) {
            if (curr instanceof ToDo) {
                textToAdd.append(curr.toFileString()).append("\n");
            }
        }
        return textToAdd.toString();
    }
}
