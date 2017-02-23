/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Mikael
 */
public class TemporaryOrderItem {
    private int reference;
    private String company_name;
    private String product_name;
    private double unit_price;
    private int quantity;
    private double total_price;

    public TemporaryOrderItem(int reference, String company_name, String product_name, double unit_price, int quantity, double total_price) {
        this.reference = reference;
        this.company_name = company_name;
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Id : " + reference + " - Nom société : " + company_name + " - Nom du produit : " + product_name + " - Prix unitaire : " + unit_price + "€ - Quantité : " + quantity + " - Total TTC  : " + total_price + "€";
    }
    
    
}
