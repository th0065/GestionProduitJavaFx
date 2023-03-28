package com.example.examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieController implements Initializable {
    @FXML
    public TextField id;
    @FXML
    public TextField libelle;

    @FXML
    public Button bsave;
    @FXML
    public Button bupdate;
    @FXML
    public Button bdelete;
    @FXML
    public TableView table;
    @FXML
    public TableColumn colid;
    @FXML
    public TableColumn collibelle;




    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Categorie cg = (Categorie) table.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(cg.getId()));
        libelle.setText(cg.getLibelle());

        bsave.setDisable(true);
    }


    public ObservableList<Categorie> getCategorie() throws SQLException {
        ObservableList<Categorie> list = FXCollections.observableArrayList();

        String select = "SELECT * from Categorie";
        Connection con = Connexion.getCon();
        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categorie cg  = new Categorie();
                cg.setId(rs.getInt("id"));
                cg.setLibelle(rs.getString("libelle"));

                list.add(cg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public void affiche() throws SQLException {
        ObservableList<Categorie> list = getCategorie();
        colid.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Categorie, String>("libelle"));

        table.setItems(list);

    }

    private void insert() {
        Connection con = Connexion.getCon();
        String insert = "INSERT INTO Categorie (libelle)VALUES(?)";
        try {
            PreparedStatement  st = con.prepareStatement(insert);
            st.setString(1, libelle.getText());

            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
        Connection con = Connexion.getCon();
        String delete = "DELETE FROM Categorie where id = ?";
        try {
            PreparedStatement st = con.prepareStatement(delete);
            st.setInt(1, Integer.parseInt(id.getText()));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void update() {
        Connection con = Connexion.getCon();
        String update
                = "UPDATE Categorie SET libelle =? where id =?";
        try {
            PreparedStatement st = con.prepareStatement(update);
            st.setString(1, libelle.getText());
            st.setInt(2, Integer.parseInt(id.getText()));
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    void clear() {
        id.setText(null);
        libelle.setText(null);

        bsave.setDisable(false);
    }

    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
        clear();
    }

    @FXML
    private void updateEvent(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        delete();
        clear();
    }

    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            affiche();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void retourne(ActionEvent actionEvent) {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/FXML/menu.fxml"));
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
}
