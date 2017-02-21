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
    private int ce_cient;
    private Date order_date;
    private Date delivery_deadline;
    private Date delivery_date;

    public Order(int reference, int ce_cient, Date order_date, Date delivery_deadline, Date delivery_date) {
        this.reference = reference;
        this.ce_cient = ce_cient;
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

    public int getCe_cient() {
        return ce_cient;
    }

    public void setCe_cient(int ce_cient) {
        this.ce_cient = ce_cient;
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
        return "Order{" + "reference=" + reference + ", ce_cient=" + ce_cient + ", order_date=" + order_date + ", delivery_deadline=" + delivery_deadline + ", delivery_date=" + delivery_date + '}';
    }
    
    
}


