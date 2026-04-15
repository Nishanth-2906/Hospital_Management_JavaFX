package com.hospital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Main JavaFX Application entry point.
 * Loads the main dashboard FXML on startup.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the main dashboard FXML
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/hospital/view/Dashboard.fxml")
        );

        Scene scene = new Scene(loader.load(), 1000, 680);

        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
