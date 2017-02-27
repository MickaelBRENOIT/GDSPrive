/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.sql.Date;

public class Product {

    private int reference;
    private String nomProduit;
    private double prixUnitaire;
    private String quantite;
    private Date dateExpiration;
    private int ceFournisseur;
    private String nomFournisseur;
    private int referenceProduit;
    private int stockMin;

    public Product(int reference, String nomProduit, double prixUnitaire, String quantite, Date dateExpiration, String nomFournisseur, int ceFournisseur, int stockMin) {
        this.reference = reference;
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.ceFournisseur = ceFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.stockMin=stockMin;
        
    }

    public Product(String nomProduit, double prixUnitaire, String quantite, Date dateExpiration, String nomFournisseur, int ceFournisseur, int stockMin) {
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.nomFournisseur = nomFournisseur;
        this.ceFournisseur = ceFournisseur;
        this.stockMin=stockMin;
    }
    
    public Product(int reference, int stockMin){
        this.reference = reference;
        this.stockMin = stockMin;
    }

    public Product(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getCeFournisseur() {
        return ceFournisseur;
    }

    public void setCeFournisseur(int ceFournisseur) {
        this.ceFournisseur = ceFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public int getReferenceProduit() {
        return referenceProduit;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

  

    @Override
    public String toString() {

        return reference + " Produit : " + nomProduit + " Prix : " + prixUnitaire + " quantite : " + quantite + " date expiration :  " + dateExpiration + " nom Fournisseur : " + nomFournisseur;

    }

}
