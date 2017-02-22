/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.orderItem;

/**
 *
 * @author Mikael
 */
public class OrderItem {
    private int reference;
    private int fk_order;
    private int fk_product;
    private int quantity;

    public OrderItem(int reference, int fk_order, int fk_product, int quantity) {
        this.reference = reference;
        this.fk_order = fk_order;
        this.fk_product = fk_product;
        this.quantity = quantity;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getFk_order() {
        return fk_order;
    }

    public void setFk_order(int fk_order) {
        this.fk_order = fk_order;
    }

    public int getFk_product() {
        return fk_product;
    }

    public void setFk_product(int fk_product) {
        this.fk_product = fk_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "reference=" + reference + ", fk_order=" + fk_order + ", fk_product=" + fk_product + ", quantity=" + quantity + '}';
    }
    
}
