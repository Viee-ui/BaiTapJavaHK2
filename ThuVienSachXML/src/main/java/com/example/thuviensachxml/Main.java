package com.example.thuviensachxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thuviensachxml/library.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Book Library Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}