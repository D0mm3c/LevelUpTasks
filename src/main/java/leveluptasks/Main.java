package leveluptasks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import leveluptasks.controller.DashboardView;
import leveluptasks.util.TaskStorage;
import leveluptasks.util.UserStatsManager;

public class Main extends Application {
    public static TaskStorage taskStorage;
    public static UserStatsManager userStatsManager;

    @Override
    public void start(Stage primaryStage) {
        DashboardView dashBoard = new DashboardView(userStatsManager.getUserStats());

        Scene scene = new Scene(dashBoard.getRoot(), 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LevelUp Tasks");
        primaryStage.show();

    }

    public static void initData(){
        taskStorage = new TaskStorage();
        userStatsManager = new UserStatsManager();
    }
    public static void main(String[] args) {
        initData();
        launch(args);
    }


}
