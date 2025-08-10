package leveluptasks.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import leveluptasks.model.Task;

import leveluptasks.util.TaskStorage;
import leveluptasks.util.UserStatsManager;

public class DashboardView {

    private VBox root;
    private HBox topBar;
    private HBox xpBarBox;
    private Label usernameLabel;
    private Label levelLabel;
    private ProgressBar xpProgressBar;
    private Label xpLabel;
    private ListView<Task> taskListView;
    private Button finishTaskButton;
    private Button addTaskButton;
    private Button deleteButton;

    private UserStatsManager userStats;
    private TaskStorage taskStorage;

    private ObservableList<Task> ObservableTaskList;


    public DashboardView(UserStatsManager pUserStats, TaskStorage pTaskStorage) {
        userStats = pUserStats;
        taskStorage = pTaskStorage;
        ObservableTaskList = FXCollections.observableArrayList(pTaskStorage.getTaskList());
        createUI();
    }

    /*****************************************************************************
     ******************************************************************************
     ********************* _                             _    *********************
     *********************| |                           | |  **********************
     *********************| |     __ _ _   _  ___  _   _| |_ **********************
     *********************| |    / _` | | | |/ _ \| | | | __|**********************
     *********************| |___| (_| | |_| | (_) | |_| | |_ **********************
     *********************\_____/\__,_|\__, |\___/ \__,_|\__|**********************
     ******************************************************************************
     *****************************************************************************/


    private void createUI() {
        root = new VBox(10);
        root.setPadding(new Insets(15));

        // Top Bar: Username and Badge
        topBar = new HBox(10);
        usernameLabel = new Label(userStats.getUserStats().getName());
        levelLabel = new Label(String.format("Level: %d", userStats.getUserStats().getLevel()));
        topBar.getChildren().addAll(usernameLabel, levelLabel);

        // XP Progress Bar
        xpBarBox = new HBox(10);
        xpProgressBar = new ProgressBar((double) userStats.getUserStats().getXp() /userStats.getUserStats().getXpToNextLevel()); // Example value
        xpProgressBar.setPrefWidth(300);
        xpLabel = new Label(String.format("XP: %d/%d", userStats.getUserStats().getXp(), userStats.getUserStats().getXpToNextLevel()));
        xpBarBox.setAlignment(Pos.CENTER_LEFT);
        xpBarBox.getChildren().addAll(xpProgressBar, xpLabel);

        // Separator
        Separator separator = new Separator();

        // Task List View
        taskListView = new ListView<>(ObservableTaskList);
        taskListView.setCellFactory(lv -> new ListCell<Task>(){
            @Override
            protected void updateItem(Task task, boolean empty){
                super.updateItem(task, empty);
                if(empty || task == null){
                    setText(null);
                    setGraphic(null);
                } else {
                    Label title = new Label(task.getTaskName());
                    title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

                    Label desc = new Label(task.getTaskDescription());
                    desc.setStyle("-fx-font-size: 12px; -fx-text-fill: gray;");

                    Label xp = new Label("XP: " + task.getTaskXp());
                    xp.setStyle("-fx-font-size: 12px; -fx-text-fill: green;");

                    VBox box = new VBox(title, desc, xp);
                    box.setSpacing(2);

                    setGraphic(box);
                }
            }
        });
        VBox.setVgrow(taskListView, Priority.ALWAYS);

        // Bottom Button Bar
        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER);
        finishTaskButton = new Button("Finished Task");
        finishTaskButton.setOnAction(this::finishTask);
        addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(this::addTask);
        deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(this::deleteTask);
        buttonBar.getChildren().addAll(finishTaskButton, addTaskButton, deleteButton);

        // Combine all sections
        root.getChildren().addAll(topBar, xpBarBox, separator, taskListView, buttonBar);
    }
    public VBox getRoot() {
        return root;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    private void updateTopBar(){
        levelLabel.setText(String.format("Level: %d", userStats.getUserStats().getLevel()));
        xpLabel.setText(String.format("XP: %d/%d", userStats.getUserStats().getXp(), userStats.getUserStats().getXpToNextLevel()));
        xpProgressBar.setProgress((double) userStats.getUserStats().getXp() /userStats.getUserStats().getXpToNextLevel());
    }



     /*****************************************************************************
     ******************************************************************************
     **********______       _   _              _                 _      ***********
     **********| ___ \     | | | |            | |               (_)     ***********
     **********| |_/ /_   _| |_| |_ ___  _ __ | |     ___   __ _ _  ___ ***********
     **********| ___ \ | | | __| __/ _ \| '_ \| |    / _ \ / _` | |/ __|***********
     **********| |_/ / |_| | |_| || (_) | | | | |___| (_) | (_| | | (__ ***********
     **********\____/ \__,_|\__|\__\___/|_| |_\_____/\___/ \__, |_|\___|***********
     **********                                             __/ |       ***********
     **********                                            |___/        ***********
     ******************************************************************************
     *****************************************************************************/

    private void finishTask(ActionEvent event) {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if(selectedTask == null) return;
        if(userStats.addXp(selectedTask.getTaskXp())){
            this.showLevelUpAlert();
        }
        ObservableTaskList.remove(selectedTask);
        taskStorage.deleteTask(selectedTask);
        updateTopBar();
    }

    private void addTask(ActionEvent event) {
        showAddTaskDialog();
        //Task newTask = new Task("Task", "Description", 10);
        //ObservableTaskList.add(newTask);
        //taskStorage.addTask(newTask);
    }

    private void deleteTask(ActionEvent event) {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if(selectedTask == null) return;
        ObservableTaskList.remove(selectedTask);
        taskStorage.deleteTask(selectedTask);
    }

    private void showAddTaskDialog(){
       Stage dialogStage = new Stage();
       VBox dialogVbox = new VBox(10);
       dialogVbox.setPadding(new Insets(15));
       dialogVbox.setAlignment(Pos.TOP_CENTER);
       Label titleLabel = new Label("Add Task");
       titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
       dialogVbox.getChildren().add(titleLabel);

       Label taskNameLabel = new Label("Task Name:");
       TextField taskNameTextField = new TextField();

       Label taskDescriptionLabel = new Label("Task Description:");
       TextArea taskDescriptionTextArea = new TextArea();

       Label taskXpLabel = new Label("Task XP:");
       Slider taskXpSlider = new Slider(0, 100, 10);
       taskXpSlider.setShowTickLabels(true);
       taskXpSlider.setShowTickMarks(true);
       taskXpSlider.setMajorTickUnit(10);
       taskXpSlider.setMinorTickCount(5);
       taskXpSlider.snapToTicksProperty().setValue(true);


       Button addButton = new Button("Add");
       addButton.setOnAction(event -> {
           try{
               Task newTask = new Task(taskNameTextField.getText(), taskDescriptionTextArea.getText(), (int) taskXpSlider.getValue());
               ObservableTaskList.add(newTask);
               taskStorage.addTask(newTask);
               dialogStage.close();
           }catch (NumberFormatException e){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Invalid Input");
               alert.setContentText("Task XP must be a number!");
               alert.showAndWait();
           }

       });
       dialogVbox.getChildren().addAll(taskNameLabel, taskNameTextField, taskDescriptionLabel, taskDescriptionTextArea, taskXpLabel, taskXpSlider, addButton);
       Scene scene = new Scene(dialogVbox, 300, 400);
       dialogStage.setScene(scene);
       dialogStage.show();
    }

    private void showLevelUpAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Level Up!");
        alert.setHeaderText("Congratulations!");
        alert.setContentText("You have reached level " + userStats.getUserStats().getLevel() + "!");
        alert.showAndWait();
    }

}
