package leveluptasks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import leveluptasks.model.Task;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20");

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LevelUp Tasks");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
