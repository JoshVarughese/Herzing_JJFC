package com.jjfc.superturboservice.jjfc_super_turbo_frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Loads the landing page FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/com/jjfc/superturboservice/jjfc_super_turbo_frontend/landing_page.fxml"));
            primaryStage.setTitle("JJFC Super Turbo Service");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
