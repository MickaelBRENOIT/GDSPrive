package order.orderItem;

public class OrderItem {

    private int reference;
    private int fk_order;
    private int fk_product;
    private int quantity;

    /**
     * Cnstructeur qui construit la commande des articles avec les paramètres
     * suivant:
     *
     * @param reference reference du client
     * @param fk_order identifiant de la commande
     * @param fk_product identifiant du produit commandé
     * @param quantity quantité de produit
     */
    public OrderItem(int reference, int fk_order, int fk_product, int quantity) {
        this.reference = reference;
        this.fk_order = fk_order;
        this.fk_product = fk_product;
        this.quantity = quantity;
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
     * @return l'identifiant de la commande effectué
     */
    public int getFk_order() {
        return fk_order;
    }

    /**
     *
     * @param fk_order modifie l'identifiant de la commande effectué
     */
    public void setFk_order(int fk_order) {
        this.fk_order = fk_order;
    }

    /**
     *
     * @return l'identifiant du produit commandé
     */
    public int getFk_product() {
        return fk_product;
    }

    /**
     *
     * @param fk_product modifie l'identifiant du produit commandé
     */
    public void setFk_product(int fk_product) {
        this.fk_product = fk_product;
    }

    /**
     *
     * @return la quantité du produit commandé
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity modifie la quantité du produit commané
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return une chaine de caractère en fonctions des differents paramètres
     */
    @Override
    public String toString() {
        return "OrderItem{" + "reference=" + reference + ", fk_order=" + fk_order + ", fk_product=" + fk_product + ", quantity=" + quantity + '}';
    }

}
