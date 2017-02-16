/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

/**
 *
 * @author Mikael
 */
public class Supplier {
    private int reference;
    private String company;
    private String address;
    private String field;
    private String phoneNumber;

    public Supplier(int reference, String company, String address, String field, String phoneNumber) {
        this.reference = reference;
        this.company = company;
        this.address = address;
        this.field = field;
        this.phoneNumber = phoneNumber;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() { //TODO if company == ""
        return reference + " - " + company + " - " + address + " - " + field + " - " + phoneNumber;
    }
    
    
    
    
}
