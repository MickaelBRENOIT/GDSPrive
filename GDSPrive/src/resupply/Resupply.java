/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resupply;

import java.sql.Date;

/**
 *
 * @author sandra
 */
public class Resupply {

    private int ce_produit;
    private String quantite;
    private Date dateCommande;
   

    public Resupply(int ce_produit, String quantite, Date dateCommande) {
        this.ce_produit = ce_produit;
        this.quantite = quantite;
        this.dateCommande = dateCommande;
    
    }

   

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getCe_produit() {
        return ce_produit;
    }

    public void setCe_produit(int ce_produit) {
        this.ce_produit = ce_produit;
    }
public String toString() {

        return  +ce_produit+ " quantite : " + quantite + " date expiration :  " + dateCommande ;

    }
   
    
    
}
