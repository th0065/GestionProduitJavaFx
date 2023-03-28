package com.example.examen;

public class Produit {

    int id  ;
    String libelle ;
    int quantite ;

    int prixUnitaire ;
    int idcategorie;
    public Produit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }



    /*public Produit(int id, String libelle, int quantite, int prixUnitaire, int idCategorie) {
        this.id = id;
        this.libelle = libelle;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.idCategorie = idCategorie;
    }*/
}
