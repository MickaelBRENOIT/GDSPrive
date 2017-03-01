package supplier;

public class Supplier {

    private int reference;
    private String company;
    private String address;
    private String field;
    private String phoneNumber;
    private String mail;

    public Supplier() {

    }

    /**
     * Constructeur qui construit le fournisseur avec les parametres suivants
     *
     * @param reference la referenc du fournisseur
     * @param company le nom de la societe
     * @param address l'adresse de la societe
     * @param field le domaine de la societe
     * @param phoneNumber le numero de la societe
     * @param mail l'adresse mail de la societe
     */
    public Supplier(int reference, String company, String address, String field, String phoneNumber, String mail) {
        this.reference = reference;
        this.company = company;
        this.address = address;
        this.field = field;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    /**
     * Constructeur qui construit un fournisseur et qui prend en parametre
     *
     * @param company le nom de la societé
     * @param address l'adresse de la societe
     * @param field le domainde de la societe
     * @param phoneNumber le numero de telephone
     * @param mail l'adresse mail
     */
    public Supplier(String company, String address, String field, String phoneNumber, String mail) {
        this(0, company, address, field, phoneNumber, mail);
    }

    /**
     *
     * @return la reference
     */
    public int getReference() {
        return reference;
    }

    /**
     *
     * @param reference modifie la reference
     */
    public void setReference(int reference) {
        this.reference = reference;
    }

    /**
     *
     * @return le nom de la societe
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @param company modifie le nom de la societe
     */
    public void setCompany(String company) {
        this.company = company;
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
     * @return le domaine
     */
    public String getField() {
        return field;
    }

    /**
     *
     * @param field modifie le domaine
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     *
     * @return le numero de telephone
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber modifie le numero de telephone
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return l'adresse mail
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail modifie l'adresse mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return reference + " - " + company + " - " + address + " - " + field + " - " + phoneNumber + " - " + mail;
    }

}
