/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Date;


/**
 *
 * @author Mikael
 */
public class User {
    private int reference_user;
    private String name;
    private String surname;
    private String password;
    private String mail;
    private Date birth;
    private Date hiring;
    private String address;
    private int role;
    private String number;
    
    private int reference_role;
    private String designation;

    public User(int reference_user, String name, String surname, String password, String mail, Date birth, Date hiring, String address, int role, String number) {
        this.reference_user = reference_user;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.mail = mail;
        this.birth = birth;
        this.hiring = hiring;
        this.address = address;
        this.role = role;
        this.number = number;
    }

    public User(String name, String surname, String password, String mail, Date birth, Date hiring, String address, int role, String number) {
        this(0, name, surname, password, mail, birth, hiring, address, role, number);
    }

    public User(int reference_role, String designation) {
        this.reference_role = reference_role;
        this.designation = designation;
    }

    public int getReference_user() {
        return reference_user;
    }

    public void setReference_user(int reference_user) {
        this.reference_user = reference_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getHiring() {
        return hiring;
    }

    public void setHiring(Date hiring) {
        this.hiring = hiring;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getReference_role() {
        return reference_role;
    }

    public void setReference_role(int reference_role) {
        this.reference_role = reference_role;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        if(getRole() == 1){
            return reference_user + " - " + name + " - " + surname + " - " + password + " - " + mail + " - " + birth + " - " + hiring + " - " + address + " - " + "administrateur" + " - " + number;
        }else{
            return reference_user + " - " + name + " - " + surname + " - " + password + " - " + mail + " - " + birth + " - " + hiring + " - " + address + " - " + "utilisateur" + " - " + number;
        }
    }

    
    
    
}
