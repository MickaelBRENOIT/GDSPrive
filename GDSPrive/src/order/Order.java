package order;

import java.sql.Date;

public class Order {

    private int reference;
    private int fk_customer;
    private String customer_name;
    private Date order_date;
    private Date delivery_deadline;
    private Date delivery_date;
    private double totalPriceOrder;

    /**
     * Constucteur qui construit une commande avec les informations suivantes.
     *
     * @param fk_customer - identifiant du client qui a passé la commande
     * @param order_date - date de commande
     * @param delivery_deadline - date limite de livraison
     * @param delivery_date - date de livraison
     * @param totalPriceOrder - prix de la commande
     */
    public Order(int fk_customer, Date order_date, Date delivery_deadline, Date delivery_date, double totalPriceOrder) {
        this.fk_customer = fk_customer;
        this.order_date = order_date;
        this.delivery_deadline = delivery_deadline;
        this.delivery_date = delivery_date;
        this.totalPriceOrder = totalPriceOrder;
    }

    /**
     * Constucteur qui construit une commande avec les informations suivantes.
     *
     * @param reference - identifiant de la commande
     * @param customer_name - nom du client qui a passé la commande
     * @param order_date - date de commande
     * @param delivery_deadline - date limite de livraison
     * @param delivery_date - date de livraison
     * @param totalPriceOrder - prix de la commande
     */
    public Order(int reference, String customer_name, Date order_date, Date delivery_deadline, Date delivery_date, double totalPriceOrder) {
        this.reference = reference;
        this.customer_name = customer_name;
        this.order_date = order_date;
        this.delivery_deadline = delivery_deadline;
        this.delivery_date = delivery_date;
        this.totalPriceOrder = totalPriceOrder;
    }

    /**
     * Constucteur qui construit une commande avec les informations suivantes.
     *
     * @param reference - identifiant de la commande
     * @param customer_name - nom du client qui a passé la commande
     * @param delivery_date - date de livraison
     */
    public Order(int reference, String customer_name, Date delivery_date) {
        this.reference = reference;
        this.customer_name = customer_name;
        this.delivery_date = delivery_date;
    }

    /**
     * Constucteur qui construit une commande avec les informations suivantes.
     *
     * @param reference - identifiant de la commande
     * @param customer_name - nom du client qui a passé la commande
     */
    public Order(int reference, String customer_name) {
        this.customer_name = customer_name;
        this.reference = reference;
    }

    /**
     * Constucteur qui construit une commande avec les informations suivantes.
     *
     * @param reference - identifiant de la commande
     * @param delivery_date - date de livraison
     */
    public Order(int reference, Date delivery_date) {
        this.reference = reference;
        this.delivery_date = delivery_date;
    }

    /**
     *
     * @return - retoune la référence d'une commande
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @param reference - modifie la référence d'une commande
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return - retourne l'identifiant qui a passé la commande
     */
    public int getFk_customer() {
        return fk_customer;
    }

    /**
     *
     * @param fk_customer - modifie l'identifiant du client qui a passé la
     * commande
     */
    public void setFk_customer(int fk_customer) {
        this.fk_customer = fk_customer;
    }

    /**
     *
     * @return - retourne le nom du client qui a passé la commande
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     *
     * @param customer_name - modifie le nom du client qui a passé la commande
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     *
     * @return - retourne la date de la commande
     */
    public Date getOrder_date() {
        return order_date;
    }

    /**
     *
     * @param order_date - modifie la date de la commande
     */
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    /**
     *
     * @return - retourne la date limite de livraison de la commande
     */
    public Date getDelivery_deadline() {
        return delivery_deadline;
    }

    /**
     *
     * @param delivery_deadline - modifie la date limite de livraison de la
     * commande
     */
    public void setDelivery_deadline(Date delivery_deadline) {
        this.delivery_deadline = delivery_deadline;
    }

    /**
     *
     * @return - retourne la date de livraison de la commande
     */
    public Date getDelivery_date() {
        return delivery_date;
    }

    /**
     *
     * @param delivery_date - modifie la date de livraison de la commande
     */
    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    /**
     *
     * @return - retourne le prix de la commande
     */
    public double getTotalPriceOrder() {
        return totalPriceOrder;
    }

    /**
     *
     * @param totalPriceOrder - modifie le prix de la commande
     */
    public void setTotalPriceOrder(double totalPriceOrder) {
        this.totalPriceOrder = totalPriceOrder;
    }

    /**
     *
     * @return - retourne une chaîne de caractères suivant les informations
     * renseignées sur la commande
     */
    @Override
    public String toString() {
        if (order_date == null && delivery_deadline == null && delivery_date == null && totalPriceOrder == 0) {
            return customer_name;
        } else if (customer_name == null && delivery_deadline == null && order_date == null && totalPriceOrder == 0) {
            return "" + delivery_date;
        } else {
            return reference + " - " + customer_name + " - date de commande (" + order_date + ") - date limite de livraison (" + delivery_deadline + ") - date de livraison (" + delivery_date + ')' + " - Prix TTC : " + totalPriceOrder;
        }

    }
}
