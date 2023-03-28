package com.example.examen;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {
    @FXML
    public Button retour;
    @FXML
    public PieChart piechart;

    public void retourne(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/menu.fxml"));
        Parent root = null;
        try {
            root = (Parent) Loader.load();

            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();

            Node source = (Node) actionEvent.getSource();
            Stage stage0 = (Stage) source.getScene().getWindow();
            stage0.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
              /* new PieChart.Data("Categorie A",12),
               new PieChart.Data("Categorie B",23),
               new PieChart.Data("Categorie C",9),
               new PieChart.Data("Categorie D",13),
               new PieChart.Data("Categorie E",19)*/
       );
        String select = "SELECT c.Libelle as nom,SUM(p.Quantite) as nombre FROM produit p INNER JOIN categorie c WHERE p.idcategorie=c.id GROUP BY p.idcategorie;";
        Connection con = Connexion.getCon();
        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               pieChartData.add(new PieChart.Data(rs.getString("nom"),rs.getInt("nombre")));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        piechart.setTitle("PieChart");
        piechart.setData(pieChartData);

        pieChartData.stream().forEach(pieData -> {
            pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Bounds b1 = pieData.getNode().getBoundsInLocal();
                double newX = (b1.getWidth()) / 2 + b1.getMinX();
                double newY = (b1.getHeight()) / 2 + b1.getMinY();
                // Make sure pie wedge location is reset
                pieData.getNode().setTranslateX(0);
                pieData.getNode().setTranslateY(0);
                TranslateTransition tt = new TranslateTransition(
                        Duration.millis(1500), pieData.getNode());
                tt.setByX(newX);
                tt.setByY(newY);
                tt.setAutoReverse(true);
                tt.setCycleCount(2);
                tt.play();
            });
        });
    }
}

