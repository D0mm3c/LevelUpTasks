package leveluptasks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leveluptasks.controller.DashboardView;
import leveluptasks.model.Task;
import leveluptasks.util.TaskStorage;
import leveluptasks.util.UserStatsManager;

public class Main extends Application {
    public static TaskStorage taskStorage;
    public static UserStatsManager userStatsManager;

    @Override
    public void start(Stage primaryStage) {
        DashboardView dashBoard = new DashboardView(userStatsManager, taskStorage);

        Scene scene = new Scene(dashBoard.getRoot(), 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LevelUp Tasks");
        primaryStage.show();

    }

    public static void initData(){
        taskStorage = new TaskStorage();
        userStatsManager = new UserStatsManager();
    }

    public static void saveData(){
        taskStorage.save();
        userStatsManager.save();
    }
    public static void main(String[] args) {
        initData();
        launch(args);
        saveData();
    }


}
