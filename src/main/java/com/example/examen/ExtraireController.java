package com.example.examen;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.sql.*;

public class ExtraireController {
    @FXML
    public Button pdf;
    @FXML
    public Button excel;
    @FXML
    public Button retour;


    public void pdfs(ActionEvent actionEvent) throws FileNotFoundException {
        String select = "SELECT * from Produit";
        int n = 0;
        Connection con = Connexion.getCon();
        String filename="C:\\Users\\traor\\Documents\\produits.pdf";
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc,new FileOutputStream(filename));
            doc.open();

           /* PdfPTable table = new PdfPTable(n);
            PdfPCell cl = new PdfPCell(new Phrase("Entete 1"));
            table.addCell(cl);
            cl= new PdfPCell(new Phrase("Entete 2"));
            table.addCell(cl);

            cl= new PdfPCell(new Phrase("Entete 3"));
            table.addCell(cl);
            table.setHeaderRows(1);

            table.addCell("1.0");
            table.addCell("1.1");
            table.addCell("1.2");
            table.addCell("2.1");
            table.addCell("2.2");
            table.addCell("2.3");
            doc.add(table);*/


            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = null;
            rsmd = rs.getMetaData();

            PdfPTable table  = new PdfPTable(4);


            PdfPCell cl = new PdfPCell(new Phrase("ID"));
            table.addCell(cl);
            cl = new PdfPCell(new Phrase("Libellé"));
            table.addCell(cl);
            cl = new PdfPCell(new Phrase("Quantité"));
            table.addCell(cl);
            cl = new PdfPCell(new Phrase("Prix Unitaire"));
            table.addCell(cl);
            table.setHeaderRows(1);


            while (rs.next()) {
                /*Produit pt  = new Produit();
                pt.setId(rs.getInt("id"));
                pt.setLibelle(rs.getString("libelle"));
                pt.setQuantite(rs.getInt("quantite"));
                pt.setPrixUnitaire(rs.getInt("prixUnitaire"));
                pt.setIdcategorie(rs.getInt("idcategorie"));
                 n=n+1;
                Paragraph paragraph = new Paragraph(rs.getInt("id")+"|          |" + rs.getString("libelle")+ "|          |"+
                        rs.getInt("quantite"   )+"|          |"+ rs.getInt("prixUnitaire"));*/
                table.addCell(rs.getString("id"));
                table.addCell(rs.getString("libelle"));
                table.addCell(rs.getString("quantite"));
                table.addCell(rs.getString("prixUnitaire"));



            }
            doc.add(table);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Document pdf enregistrer");
            alert.setHeaderText(null);
            alert.setContentText("Document pdf enregistrer sur votre pc au chemin"+ filename);

            alert.showAndWait();
            doc.close();
            // open file.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }




    }

    public void excels(ActionEvent actionEvent) {
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
