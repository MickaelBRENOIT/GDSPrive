package user;

import java.sql.Date;

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

    /**
     * Constructeur qui construit l'utilisateur avec les informations suivantes
     *
     * @param reference_user la reference de l'utilisateur
     * @param name le nom de l'utilisateur
     * @param surname le prenom de l'utilisateur
     * @param password le mot de passe
     * @param mail l'email de l'utilisateur
     * @param birth la date de naissande
     * @param hiring la date d'embauche
     * @param address l'adresse
     * @param role le role
     * @param number le numreo
     */
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

    /**
     *
     * Constructeur qui construit l'utilisateur avec les informations suivantes
     *
     * @param name le nom de l'utilisateur
     * @param surname le prenom de l'utilisateur
     * @param password le mot de passe
     * @param mail l'email de l'utilisateur
     * @param birth la date de naissande
     * @param hiring la date d'embauche
     * @param address l'adresse
     * @param role le role
     * @param number le numreo
     */
    public User(String name, String surname, String password, String mail, Date birth, Date hiring, String address, int role, String number) {
        this(0, name, surname, password, mail, birth, hiring, address, role, number);
    }

    /**
     * Constructeur qui construit l'utilisateur et qui prend en parametre
     *
     * @param reference_role la reference
     * @param designation la designation
     */
    public User(int reference_role, String designation) {
        this.reference_role = reference_role;
        this.designation = designation;
    }

    /**
     *
     * @return la reference
     */
    public int getReference_user() {
        return reference_user;
    }

    /**
     *
     * @param reference_user modifie la reference
     */
    public void setReference_user(int reference_user) {
        this.reference_user = reference_user;
    }

    /**
     *
     * @return le nom
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name modifie le nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return le prenom
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname modifie le prenom
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password modifie le mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return le mail
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail le mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return la date de naissance
     */
    public Date getBirth() {
        return birth;
    }

    /**
     *
     * @param birth modifie la date de naissance
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     *
     * @return la date d'embauche
     */
    public Date getHiring() {
        return hiring;
    }

    /**
     *
     * @param hiring modifie la date d'embauche
     */
    public void setHiring(Date hiring) {
        this.hiring = hiring;
    }

    /**
     *
     * @return l'adresse
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address modifie l'adresse
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return le role
     */
    public int getRole() {
        return role;
    }

    /**
     *
     * @param role modifie le role
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     *
     * @return le numero
     */
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number modifie le numero
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     *
     * @return la reference en fonction du role
     */
    public int getReference_role() {
        return reference_role;
    }

    /**
     *
     * @param reference_role modifie la refence
     */
    public void setReference_role(int reference_role) {
        this.reference_role = reference_role;
    }

    /**
     *
     * @return la designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation modifie la designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        if (getRole() == 1) {
            return reference_user + " - " + name + " - " + surname + " - " + password + " - " + mail + " - " + birth + " - " + hiring + " - " + address + " - " + "administrateur" + " - " + number;
        } else {
            return reference_user + " - " + name + " - " + surname + " - " + password + " - " + mail + " - " + birth + " - " + hiring + " - " + address + " - " + "utilisateur" + " - " + number;
        }
    }

}
