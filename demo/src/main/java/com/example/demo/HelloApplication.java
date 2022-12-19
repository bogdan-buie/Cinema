package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class HelloApplication extends Application {

    //private double x = 0; //pt setarea dimeniunii (!)
    //private double y = 0; //(!)

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 630, 638);
            stage.initStyle(StageStyle.TRANSPARENT); //dispare bara de sus a aplicatiei

            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            database obiect = new database();
            obiect.FiltruGen("%Horror%");


        }catch(Exception e){
            System.out.println(e+"\n");
            System.out.println(e.getCause());
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
