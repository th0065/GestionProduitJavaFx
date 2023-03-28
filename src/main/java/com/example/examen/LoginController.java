package com.example.examen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML private Button btnLogin;

    @FXML private Button btnRegister;

    @FXML
    void OnloginButtonClick(ActionEvent event) {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        String username = usernameField.getText();
        String password = passwordField.getText();
         /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(""));
        Parent root1 = null;
       try{
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        try {
            Connection conn = Connexion.getCon();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Connexion réussie");

                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté");

                alert.showAndWait();

                FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/menu.fxml"));
                Parent root = null;
                try{
                    root = (Parent) Loader.load();

                    Stage stage = new Stage();

                    stage.setScene(new Scene(root));
                    stage.show();

                    Node source = (Node)  event.getSource();
                    Stage stage0  = (Stage) source.getScene().getWindow();
                    stage0.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Nom d'utilisateur ou mot de passe incorrect");
                alert.showAndWait();
            }


            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la connexion");
            alert.showAndWait();

        }

    }



    @FXML
    void OnregistersButtonClick(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/register.fxml"));
        Parent root1 = null;
        try{
            root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
