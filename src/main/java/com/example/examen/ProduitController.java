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

public class ProduitController implements Initializable {
    @FXML
    public TextField id;
    @FXML
    public TextField libelle;
    @FXML
    public TextField prixUnitaire;
    @FXML
    public TextField quantite;
    @FXML
    public ComboBox idcategorie;
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
    public TableColumn colprixUnitaire;
    @FXML
    public TableColumn colquantite;
    @FXML
    public TableColumn colidcategorie;
    @FXML
    public Button bsearch;
    @FXML
    public TextField search;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String select = "SELECT * from Categorie";
        Connection con = Connexion.getCon();
        ObservableList<Integer> idcat
                = FXCollections.observableArrayList();

        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categorie pt  = new Categorie();
                pt.setId(rs.getInt("id"));
                pt.setLibelle(rs.getString("libelle"));
                idcat.add(pt.id);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        idcategorie.setItems(idcat);
        idcategorie.setValue(idcat.get(0));
        // TODO
        try {
            affiche();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Produit pt = (Produit) table.getSelectionModel().getSelectedItem();
        id.setText(String.valueOf(pt.getId()));
        libelle.setText(pt.getLibelle());
        prixUnitaire.setText(String.valueOf(pt.getPrixUnitaire()));
        quantite.setText(String.valueOf(pt.getQuantite()));
        idcategorie.getSelectionModel().select(pt.getIdcategorie());
        bsave.setDisable(true);
    }


    public ObservableList<Produit> getProduit() throws SQLException {
        ObservableList<Produit> list = FXCollections.observableArrayList();

        String select = "SELECT * from Produit";
       Connection con = Connexion.getCon();
        try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produit pt  = new Produit();
                pt.setId(rs.getInt("id"));
                pt.setLibelle(rs.getString("libelle"));
                pt.setQuantite(rs.getInt("quantite"));
                pt.setPrixUnitaire(rs.getInt("prixUnitaire"));
                pt.setIdcategorie(rs.getInt("idcategorie"));
                list.add(pt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public void affiche() throws SQLException {
        ObservableList<Produit> list = getProduit();
        colid.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle"));
        colprixUnitaire.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("prixUnitaire"));
        colquantite.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
        colidcategorie.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idcategorie"));
        table.setItems(list);

    }

    private void insert() {
         Connection con = Connexion.getCon();
        String insert = "INSERT INTO Produit (libelle,quantite,prixUnitaire,idcategorie)VALUES(?,?,?,?)";
        try {
            PreparedStatement  st = con.prepareStatement(insert);
            st.setString(1, libelle.getText());
            st.setInt(2, Integer.parseInt(quantite.getText()));
            st.setInt(3, Integer.parseInt(prixUnitaire.getText()));
            st.setInt(4, (Integer) idcategorie.getSelectionModel().getSelectedItem());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void delete() {
       Connection con = Connexion.getCon();
        String delete = "DELETE FROM Produit where id = ?";
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
                = "UPDATE Produit SET libelle =?,quantite = ?,prixUnitaire =? where id =?";
        try {
            PreparedStatement st = con.prepareStatement(update);
            st.setString(1, libelle.getText());
            st.setInt(2, Integer.parseInt(quantite.getText()));
            st.setInt(3, Integer.parseInt(prixUnitaire.getText()));
            st.setInt(4, Integer.parseInt(id.getText()));
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
        quantite.setText(null);
        prixUnitaire.setText(null);
        idcategorie.getSelectionModel().selectFirst();
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

    public void searchEvent(ActionEvent actionEvent) {
        ObservableList<Produit> list = FXCollections.observableArrayList();

        String select = "SELECT * from Produit where libelle=?";
        Connection con = Connexion.getCon();
        try {
            PreparedStatement st = con.prepareStatement(select);
            st.setString(1, search.getText());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produit pt  = new Produit();
                pt.setId(rs.getInt("id"));
                pt.setLibelle(rs.getString("libelle"));
                pt.setQuantite(rs.getInt("quantite"));
                pt.setPrixUnitaire(rs.getInt("prixUnitaire"));
                pt.setIdcategorie(rs.getInt("idcategorie"));
                list.add(pt);
            }

            colid.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
            collibelle.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle"));
            colprixUnitaire.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("prixUnitaire"));
            colquantite.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("quantite"));
            colidcategorie.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("idcategorie"));
            table.setItems(list);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
