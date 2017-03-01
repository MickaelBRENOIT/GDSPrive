package util;

import java.sql.Date;

public class SupplierOrder {

    private int reference;
    private int fk_product;
    private Date orderDate;
    private int quantity;

    /**
     * Créer le contructeur de la commande fournisseur avec les paramètres
     * suivants
     *
     * @param reference - référence de la commande fournisseur
     * @param fk_product - identifiant du produit
     * @param orderDate - date de commande
     * @param quantity - quantité du produit commandé
     */
    public SupplierOrder(int reference, int fk_product, Date orderDate, int quantity) {
        this.reference = reference;
        this.fk_product = fk_product;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    /**
     * Créer le contructeur de la commande fournisseur avec les paramètres
     * suivants
     *
     * @param fk_product - identifiant du produit
     * @param quantity - quantité du produit
     */
    public SupplierOrder(int fk_product, int quantity) {
        this.fk_product = fk_product;
        this.quantity = quantity;
    }

    /**
     *
     * @return - retourne la référence de la commande du fournisseur
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @param reference - modifie la référence de la commande du fournisseur
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return - retourne l'id du produit
     */
    public int getFk_product() {
        return fk_product;
    }

    /**
     *
     * @param fk_product - modifie l'id du produit
     */
    public void setFk_product(int fk_product) {
        this.fk_product = fk_product;
    }

    /**
     *
     * @return - retourne la date de la commande fournisseur
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param orderDate - modifie la date de la commande fournisseur
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *
     * @return - retourne la quantité de produit
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity - modifie la quantité de produit
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SupplierOrder{" + "reference=" + reference + ", fk_product=" + fk_product + ", orderDate=" + orderDate + ", quantity=" + quantity + '}';
    }

}
