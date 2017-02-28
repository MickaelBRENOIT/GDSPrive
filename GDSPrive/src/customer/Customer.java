package customer;

public class Customer {

    private int reference;
    private String company;
    private String address;
    private String field;
    private String phoneNumber;
    private String mail;

    /**
     * Constructeur client qui permet d'en créer un avec les informations
     * suivantes
     *
     * @param reference - référence du client
     * @param company - société à laquelle appartient le client
     * @param address - l'adresse du client
     * @param field - le domaine de fonction du client
     * @param phoneNumber - le numéro de téléphone du client
     * @param mail - le mail du client
     */
    public Customer(int reference, String company, String address, String field, String phoneNumber, String mail) {
        this.reference = reference;
        this.company = company;
        this.address = address;
        this.field = field;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    /**
     * Constructeur client qui permet d'en créer un avec les informations
     * suivantes
     *
     * @param company - société à laquelle appartient le client
     * @param address - l'adresse du client
     * @param field - le domaine de fonction du client
     * @param phoneNumber - le numéro de téléphone du client
     * @param mail - le mail du client
     */
    public Customer(String company, String address, String field, String phoneNumber, String mail) {
        this(0, company, address, field, phoneNumber, mail);
    }

    /**
     *
     * @return - retourne la référence du client
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @param reference - modifie la référence du client avec celle passée en
     * paramètre
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return - retourne la société du client
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @param company - modifie la société du client avec celle passée en
     * paramètre
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     *
     * @return - retourne l'adresse du client
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address - modifie l'adresse du client avec celle passée en
     * paramètre
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return - retourne le domaine de fonction du client
     */
    public String getField() {
        return field;
    }

    /**
     *
     * @param field - modifie le domaine de fonction du client avec celui qui
     * est passé en paramètre
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     *
     * @return - retourne le numéro de téléphone du client
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber - modifie le numéro de téléphone du client avec celui
     * passé en paramètre
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return - retourne le mail du client
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail - modifie le mail du client avec celui qui est passé en
     * paramètre
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return - retourne une chaîne de caractères avec les informations d'un
     * client
     */
    @Override
    public String toString() {
        return reference + " - " + company + " - " + address + " - " + field + " - " + phoneNumber + " - " + mail;
    }

}
