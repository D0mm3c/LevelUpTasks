package leveluptasks.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import leveluptasks.model.Task;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskStorage{
    private static final Path USER_STATS_PATH = Paths.get("data", "usertasks.json");
    private final Gson gson = new Gson();

    ArrayList<Task> taskList;

    Type taskListType = new TypeToken<ArrayList<Task>>() {}.getType();

    public TaskStorage() {
        load();
    }

    // Loads user stats from file or creates default if not found/corrupt
    public void load() {
        try {
            if (Files.notExists(USER_STATS_PATH)) {
                createDefaultStats();
                save();
                return;
            }

            try (Reader reader = Files.newBufferedReader(USER_STATS_PATH)) {
                taskList = gson.fromJson(reader, taskListType);
            }

            // If file is empty or invalid, create defaults
            if (taskList == null) {
                createDefaultStats();
                save();
            }
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading user stats: " + e.getMessage());
            createDefaultStats();
            save();
        }
    }

    private void createDefaultStats() {
        taskList = new ArrayList<>(); // constructor sets level=1, xp=0, etc.
    }

    // Saves current userStats to file
    public void save() {
        try {
            Files.createDirectories(USER_STATS_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(USER_STATS_PATH)) {
                gson.toJson(taskList, writer);
                System.out.println("Saved tasks to file");
            }
        } catch (IOException e) {
            System.err.println("Error saving user stats: " + e.getMessage());
        }
    }


    public void addTask(Task pTask){
        taskList.add(pTask);
        save();
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public void deleteTask(Task pTask){
        taskList.remove(pTask);
        save();
    }

    public void deleteTaskList(){
        taskList.clear();
        save();
    }
}