package authentication;

public class Authentication {

    private String login;
    private String password;
    private int role;

    /**
     * Constructeur qui permet de retourner un utilisateur par son login, mdp et
     * rôle
     *
     * @param login - login de l'utilisateur
     * @param password - le mot de passe de l'utilisateur
     * @param role - le rôle de l'utilisateur
     */
    public Authentication(String login, String password, int role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructeur qui permet de retourner un utilisateur par son login, mdp
     *
     * @param login - le login de l'utilisateur
     * @param password - le mot de passe de l'utilisateur
     */
    public Authentication(String login, String password) {
        this(login, password, 0);
    }

    /**
     *
     * @return - retourne le login de l'utilisateur
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login - modifie le login de l'utilisateur avec la chaîne passée en
     * paramètre
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return - retourne le mdp de l'utilisateur
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password - modifie le mdp de l'utilisateur avec la chaîne passée
     * en paramètre
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return - retourne le rôle de l'utilisateur
     */
    public int getRole() {
        return role;
    }

    /**
     *
     * @param role - modifie le rôle de l'utilisateur avec la chaîne passée en
     * paramètre
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     *
     * @return - une chaîne avec les éléments de l'authentification comme le
     * login et le mot de passe
     */
    @Override
    public String toString() {
        return "Authentication{" + "login=" + login + ", password=" + password + '}';
    }
}
