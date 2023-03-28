package com.example.examen;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;
    @FXML
    private TextField telField;
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void OnregisterButtonClick(ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root1 = null;
        /*try{
            root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        try {
            Connection conn = Connexion.getCon();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (nom, prenom, telephone, email, username, password) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, tel);
            ps.setString(5, email);
            ps.setString(4, username);
            ps.setString(6, password);
            ps.executeUpdate();
            conn.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inscription réussie");
            alert.setHeaderText(null);
            alert.setContentText("Votre compte a été créé avec succès");
            alert.showAndWait();


        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la création de votre compte");
            alert.showAndWait();
        }
    }
}
