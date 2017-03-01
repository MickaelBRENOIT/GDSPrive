package util;

public class TemporaryOrderItem {

    private int reference;
    private String company_name;
    private String product_name;
    private double unit_price;
    private int quantity;
    private double total_price;

    /**
     * Créer le constructeur de la commande temporaire avec les informations
     * suivantes
     *
     * @param reference - id de la commande temporaire
     * @param company_name - nom de la société cliente
     * @param product_name - nom du produit
     * @param unit_price - prix unitaire du produit
     * @param quantity - quantité du produit commander
     * @param total_price - prix total de la commande de ce produit
     */
    public TemporaryOrderItem(int reference, String company_name, String product_name, double unit_price, int quantity, double total_price) {
        this.reference = reference;
        this.company_name = company_name;
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    /**
     *
     * @return - retourne la référence de la commande temporaire
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @param reference - modifie la référence de la commande temporaire
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return - retourne le nom de la société cliente
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     *
     * @param company_name - modifie le nom de la société cliente
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     *
     * @return - retourne le nom du produit
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     *
     * @param product_name - modifie le nom du produit
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     *
     * @return - retourne le prix unitaire du produit
     */
    public double getUnit_price() {
        return unit_price;
    }

    /**
     *
     * @param unit_price - modifie le prix unitaire du produit
     */
    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    /**
     *
     * @return - retourne la quantité du produit à commander
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity - modifie la quantité du produit à commander
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return - retourne le prix total de la commande temporaire d'un produit
     */
    public double getTotal_price() {
        return total_price;
    }

    /**
     *
     * @param total_price - modifie le prix total de la commande temporaire d'un
     * produit
     */
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Id : " + reference + " - Nom société : " + company_name + " - Nom du produit : " + product_name + " - Prix unitaire : " + unit_price + "€ - Quantité : " + quantity + " - Total TTC  : " + total_price + "€";
    }

}
