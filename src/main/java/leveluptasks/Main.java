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
        Label xpLabel = new Label("Level 1 – XP: 0 / 100");
        ProgressBar xpBar = new ProgressBar(0);

        ListView<Task> taskList = new ListView<>();
        taskList.getItems().addAll(
                new Task("Mathe lernen", "10 XP"),
                new Task("JavaFX starten", "20 XP")
        );

        Button completeBtn = new Button("Aufgabe abschließen");
        completeBtn.setOnAction(e -> {
            Task selected = taskList.getSelectionModel().getSelectedItem();
            if (selected != null) {
                System.out.println("🎉 Erledigt: " + selected.getName());
            }
        });

        VBox root = new VBox(10, xpLabel, xpBar, taskList, completeBtn);
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
