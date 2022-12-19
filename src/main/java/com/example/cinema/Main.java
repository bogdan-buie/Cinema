package com.example.cinema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(root, 700,500);
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //DataBase DB = new DataBase();
        //DB.connectToDB();
        //DB.InsertLoc(30,"1");
        //DB.InsertEcranizare("2022-12-10 17:00:00",DB.getIDFilm("%Jurassic Park%"),3);
        //DB.InsertEcranizare("2022-12-11 17:01:00",DB.getIDFilm("%Nunta pe bani%"),2);
    }

    public static void main(String[] args) {
        launch();
    }
}