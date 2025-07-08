package leveluptasks.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import leveluptasks.model.Task;
import leveluptasks.model.UserStats;

public class DashboardView {

    private VBox root;
    private Label usernameLabel;
    private Label levelLabel;
    private ProgressBar xpProgressBar;
    private Label xpLabel;
    private ListView<Task> taskListView;
    private Button finishTaskButton;
    private Button addTaskButton;
    private Button saveButton;

    private UserStats userStats;

    public DashboardView(UserStats pUserStats) {
        userStats = pUserStats;
        createUI();
    }

    private void createUI() {
        root = new VBox(10);
        root.setPadding(new Insets(15));

        // Top Bar: Username and Badge
        HBox topBar = new HBox(10);
        usernameLabel = new Label(userStats.getName());
        levelLabel = new Label(String.format("Level: %d", userStats.getLevel()));
        topBar.getChildren().addAll(usernameLabel, levelLabel);

        // XP Progress Bar
        HBox xpBarBox = new HBox(10);
        xpProgressBar = new ProgressBar((double) userStats.getXp() /userStats.getXpToNextLevel()); // Example value
        xpProgressBar.setPrefWidth(300);
        xpLabel = new Label(String.format("XP: %d/%d", userStats.getXp(), userStats.getXpToNextLevel()));
        xpBarBox.setAlignment(Pos.CENTER_LEFT);
        xpBarBox.getChildren().addAll(xpProgressBar, xpLabel);

        // Separator
        Separator separator = new Separator();

        // Task List View
        taskListView = new ListView<>();
        taskListView.setPlaceholder(new Label("No tasks yet."));
        VBox.setVgrow(taskListView, Priority.ALWAYS);

        // Bottom Button Bar
        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER);
        finishTaskButton = new Button("Finished Task");
        addTaskButton = new Button("Add Task");
        saveButton = new Button("SAVE");
        buttonBar.getChildren().addAll(finishTaskButton, addTaskButton, saveButton);

        // Combine all sections
        root.getChildren().addAll(topBar, xpBarBox, separator, taskListView, buttonBar);
    }
    public VBox getRoot() {
        return root;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public ProgressBar getXpProgressBar() {
        return xpProgressBar;
    }

    public Label getXpLabel() {
        return xpLabel;
    }

    public ListView<Task> getTaskListView() {
        return taskListView;
    }

    public Button getFinishTaskButton() {
        return finishTaskButton;
    }

    public Button getAddTaskButton() {
        return addTaskButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }
}
