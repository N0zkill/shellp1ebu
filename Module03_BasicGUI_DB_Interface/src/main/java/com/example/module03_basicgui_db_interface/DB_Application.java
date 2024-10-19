package com.example.module03_basicgui_db_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class DB_Application extends Application {

    private ConnDbOps dbOps;

    public static void main(String[] args) {
        launch();
    }

    private Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        dbOps = new ConnDbOps();

        // Ensure the database is connected before loading the main scene
        if (dbOps.connectToDatabase()) {
            showScene1();
        } else {
            System.err.println("Database connection failed.");
        }
    }

    private void showScene1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            Scene scene = new Scene(root, 850, 560);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("db_interface_gui.fxml"));

            Scene currentScene = primaryStage.getScene();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), currentScene.getRoot());
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                Scene newScene = new Scene(newRoot, 850, 560);
                primaryStage.setScene(newScene);
            });

            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
