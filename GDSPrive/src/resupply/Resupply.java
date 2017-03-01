package resupply;

import java.sql.Date;

public class Resupply {

    private int ce_produit;
    private String quantite;
    private Date dateCommande;

    /**
     * Constructeur qui construit le reaprovisionement avec les parameteres
     * suivantes
     *
     * @param ce_produit l'identifiant du produit
     * @param quantite la quantité de produit
     * @param dateCommande la date de commande
     */
    public Resupply(int ce_produit, String quantite, Date dateCommande) {
        this.ce_produit = ce_produit;
        this.quantite = quantite;
        this.dateCommande = dateCommande;

    }

    /**
     *
     * @return la quantité de produit
     */
    public String getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite modifié la quantité de produits
     */
    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    /**
     *
     * @return la date de commande
     */
    public Date getDateCommande() {
        return dateCommande;
    }

    /**
     *
     * @param dateCommande modifie la date de commande
     */
    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    /**
     *
     * @return l'identifiant du produit
     */
    public int getCe_produit() {
        return ce_produit;
    }

    /**
     *
     * @param ce_produit modifie l'identifiant du produit
     */
    public void setCe_produit(int ce_produit) {
        this.ce_produit = ce_produit;
    }

    public String toString() {

        return +ce_produit + " quantite : " + quantite + " date expiration :  " + dateCommande;

    }

}
