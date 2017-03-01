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

    /**
     * Constructeur qui construit un produit avec les informations
     *
     * @param reference la reference du produit
     * @param nomProduit le nom du produit
     * @param prixUnitaire le prix unitaire du produit
     * @param quantite la quantité de produit ajouté
     * @param dateExpiration la date d'expiration du produit
     * @param nomFournisseur le nom du fournisseur qui a fournit le produit
     * @param ceFournisseur l'identifiant du fournisseur
     * @param stockMin le stock minimal
     */
    public Product(int reference, String nomProduit, double prixUnitaire, String quantite, Date dateExpiration, String nomFournisseur, int ceFournisseur, int stockMin) {
        this.reference = reference;
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.ceFournisseur = ceFournisseur;
        this.nomFournisseur = nomFournisseur;
        this.stockMin = stockMin;

    }

    /**
     * Constructeur qui construit le produit avec les parametres suivants
     *
     * @param nomProduit le nom du produit
     * @param prixUnitaire le prix unitaire du produit
     * @param quantite la quantite de produit
     * @param dateExpiration la date d'expiration du produit
     * @param nomFournisseur le nom du fournisseur
     * @param ceFournisseur l'identifiant du fournisseur
     * @param stockMin le stock minimal
     */
    public Product(String nomProduit, double prixUnitaire, String quantite, Date dateExpiration, String nomFournisseur, int ceFournisseur, int stockMin) {
        this.nomProduit = nomProduit;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
        this.dateExpiration = dateExpiration;
        this.nomFournisseur = nomFournisseur;
        this.ceFournisseur = ceFournisseur;
        this.stockMin = stockMin;
    }

    /**
     * Le constructeur qui prent en parametre les informations suivantes:
     *
     * @param reference la reference du produit
     * @param stockMin le stock minimal
     */
    public Product(int reference, int stockMin) {
        this.reference = reference;
        this.stockMin = stockMin;
    }

    /**
     * constructeur qui prend en parametre:
     *
     * @param nomFournisseur le nom du forunisseur
     */
    public Product(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    /**
     *
     * @return la reference
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @param reference modifie la reference
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return le nom du produit
     */
    public String getNomProduit() {
        return nomProduit;
    }

    /**
     *
     * @param nomProduit modifie le nom du produit
     */
    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    /**
     *
     * @return le prix unitaire
     */
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    /**
     *
     * @param prixUnitaire modifie le prix unitaire
     */
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
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
     * @param quantite modifie la quantité de produit ajouté
     */
    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    /**
     *
     * @return la date d'expiration
     */
    public Date getDateExpiration() {
        return dateExpiration;
    }

    /**
     *
     * @param dateExpiration modifie la date d'expiration
     */
    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    /**
     *
     * @return l'identifiant du fournisseur
     */
    public int getCeFournisseur() {
        return ceFournisseur;
    }

    /**
     *
     * @param ceFournisseur modifie l'identifiant du fournisseur
     */
    public void setCeFournisseur(int ceFournisseur) {
        this.ceFournisseur = ceFournisseur;
    }

    /**
     *
     * @return le nom du fournisseur
     */
    public String getNomFournisseur() {
        return nomFournisseur;
    }

    /**
     *
     * @param nomFournisseur modifie le nom du fournisseur
     */
    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    /**
     *
     * @return la reference du produit
     */
    public int getReferenceProduit() {
        return referenceProduit;
    }

    /**
     *
     * @return le stock minimal
     */
    public int getStockMin() {
        return stockMin;
    }

    /**
     *
     * @param stockMin modifie le stock minimal
     */
    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    @Override
    public String toString() {

        return reference + " Produit : " + nomProduit + " Prix : " + prixUnitaire + " quantite : " + quantite + " date expiration :  " + dateExpiration + " nom Fournisseur : " + nomFournisseur;

    }

}
