package com.example.examen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public void stat(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/statistiques.fxml"));
        Parent root = null;
        try{
            root = (Parent) Loader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
            Node source = (Node)  actionEvent.getSource();
            Stage stage0  = (Stage) source.getScene().getWindow();
            stage0.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void prod(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/produits.fxml"));
        Parent root = null;
        try{
            root = (Parent) Loader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

            Node source = (Node)  actionEvent.getSource();
            Stage stage0  = (Stage) source.getScene().getWindow();
            stage0.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cat(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/categories.fxml"));
        Parent root = null;
        try{
            root = (Parent) Loader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

            Node source = (Node)  actionEvent.getSource();
            Stage stage0  = (Stage) source.getScene().getWindow();
            stage0.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ext(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/extraires.fxml"));
        Parent root = null;
        try{
            root = (Parent) Loader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

            Node source = (Node)  actionEvent.getSource();
            Stage stage0  = (Stage) source.getScene().getWindow();
            stage0.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void quitt(ActionEvent actionEvent) {
        Platform.exit();
    }
}
