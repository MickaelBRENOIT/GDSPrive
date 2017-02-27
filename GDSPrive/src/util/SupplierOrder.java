/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;

/**
 *
 * @author Mikael
 */
public class SupplierOrder {
    private int reference;
    private int fk_product;
    private Date orderDate;
    private int quantity;

    public SupplierOrder(int reference, int fk_product, Date orderDate, int quantity) {
        this.reference = reference;
        this.fk_product = fk_product;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public SupplierOrder(int fk_product, int quantity) {
        this.fk_product = fk_product;
        this.quantity = quantity;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getFk_product() {
        return fk_product;
    }

    public void setFk_product(int fk_product) {
        this.fk_product = fk_product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SupplierOrder{" + "reference=" + reference + ", fk_product=" + fk_product + ", orderDate=" + orderDate + ", quantity=" + quantity + '}';
    }
    
        
}
