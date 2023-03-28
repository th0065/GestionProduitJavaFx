package com.example.examen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        Scene scene1 = new Scene(loader.load());
        stage.setScene(scene1);
        stage.show();

    }
    public static void main(String[] args){
        launch();
    }
}
