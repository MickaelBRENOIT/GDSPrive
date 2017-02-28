package authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationDAO {

    private Connection connection;
    private PreparedStatement statement;
    private Authentication authentication;
    private ResultSet rs;

    /**
     * Initialise la connection et la requête préparée à null
     */
    public AuthenticationDAO() {
        connection = null;
        statement = null;
    }

    /**
     * Méthode qui permet de retourner un utilisateur si les identifiants sont
     * corrects.
     *
     * @param auth - un utilisateur est envoyé, c'est-à-dire son login et mot de
     * passe
     * @return - retourne l'utilisateur s'il est présent dans la base de données
     */
    public Authentication signIn(Authentication auth) {

        authentication = null;
        rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_utilisateur, email_utilisateur, password_utilisateur, ce_role FROM utilisateur WHERE email_utilisateur = ? AND password_utilisateur = ?");
            statement.setString(1, auth.getLogin());
            statement.setString(2, auth.getPassword());
            rs = statement.executeQuery();

            if (rs.next()) {
                authentication = new Authentication(rs.getString("email_utilisateur"), rs.getString("password_utilisateur"), rs.getInt("ce_role"));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception t) {
            }
        }

        return authentication;
    }
}
