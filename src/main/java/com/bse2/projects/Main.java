package com.bse2.projects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =  FXMLLoader.load(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(root);
        //stage.setTitle("CIMS-2020/BSE/066/PS");
        stage.setTitle("Church Information Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}