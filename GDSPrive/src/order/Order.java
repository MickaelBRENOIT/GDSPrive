/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Date;

/**
 *
 * @author Mikael
 */
public class Order {
    private int reference;
    private int fk_customer;
    private String customer_name;
    private Date order_date;
    private Date delivery_deadline;
    private Date delivery_date;

    public Order(int fk_customer, Date order_date, Date delivery_deadline, Date delivery_date) {
        this.fk_customer = fk_customer;
        this.order_date = order_date;
        this.delivery_deadline = delivery_deadline;
        this.delivery_date = delivery_date;
    }

    public Order(int reference, String customer_name, Date order_date, Date delivery_deadline, Date delivery_date) {
        this.reference = reference;
        this.customer_name = customer_name;
        this.order_date = order_date;
        this.delivery_deadline = delivery_deadline;
        this.delivery_date = delivery_date;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getFk_customer() {
        return fk_customer;
    }

    public void setFk_customer(int fk_customer) {
        this.fk_customer = fk_customer;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getDelivery_deadline() {
        return delivery_deadline;
    }

    public void setDelivery_deadline(Date delivery_deadline) {
        this.delivery_deadline = delivery_deadline;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    @Override
    public String toString() {
        return reference + " - " + customer_name + " - date de commande (" + order_date + ") - date limite de livraison (" + delivery_deadline + ") - date de livraison (" + delivery_date + ')';
    }    
    
}


