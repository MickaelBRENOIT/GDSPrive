package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;
    private PreparedStatement statement;

    /**
     * initialise la connexion et la requete préparé à null
     */
    public UserDAO() {
        connection = null;
        statement = null;
    }

    /**
     *
     * @return la liste de tous les roles
     */
    public List<User> getListOfAllRoles() {
        List<User> users = new ArrayList<User>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM role");
            rs = statement.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("id_role"), rs.getString("nom_role")));
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

        return users;

    }

    /**
     *
     * @param user l'utilisateur
     * @return le nombre d'utilisateur ajouté
     */
    public int addUser(User user) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO utilisateur (nom_utilisateur,prenom_utilisateur,email_utilisateur,password_utilisateur,date_naissance,date_embauche,adresse_utilisateur,ce_role,numero_utilisateur) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getPassword());
            statement.setDate(5, user.getBirth());
            statement.setDate(6, user.getHiring());
            statement.setString(7, user.getAddress());
            statement.setInt(8, user.getRole());
            statement.setString(9, user.getNumber());

            //Ex�cution de la requ�te
            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du preparedStatement et de la connexion
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
        }

        return returnCode;
    }

    /**
     *
     *
     * @return la liste de tous les utilisateurs
     */
    List<User> getListOfAllUsers() {
        List<User> users = new ArrayList<User>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM utilisateur");
            rs = statement.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("id_utilisateur"), rs.getString("nom_utilisateur"), rs.getString("prenom_utilisateur"), rs.getString("email_utilisateur"), rs.getString("password_utilisateur"), rs.getDate("date_naissance"), rs.getDate("date_embauche"), rs.getString("adresse_utilisateur"), rs.getInt("ce_role"), rs.getString("numero_utilisateur")));
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

        return users;

    }

    /**
     *
     *
     * @return la liste des utilisateurs par role
     */
    List<User> getListUsersByARole(int role) {
        List<User> users = new ArrayList<User>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM utilisateur WHERE ce_role = ?");
            statement.setInt(1, role);
            rs = statement.executeQuery();

            while (rs.next()) {
                users.add(new User(rs.getInt("id_utilisateur"), rs.getString("nom_utilisateur"), rs.getString("prenom_utilisateur"), rs.getString("email_utilisateur"), rs.getString("password_utilisateur"), rs.getDate("date_naissance"), rs.getDate("date_embauche"), rs.getString("adresse_utilisateur"), rs.getInt("ce_role"), rs.getString("numero_utilisateur")));
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

        return users;
    }

    /**
     *
     *
     * @return le nombre de suppression
     */
    int deleteUser(String id) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("DELETE FROM utilisateur WHERE id_utilisateur = ? ");

            statement.setString(1, id);

            //Ex�cution de la requ�te
            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du preparedStatement et de la connexion
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
        }
        return returnCode;
    }

    /**
     *
     *
     * @return l'utilisateur
     */
    User getAUser(String id) {
        User user = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM utilisateur WHERE id_utilisateur = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id_utilisateur"), rs.getString("nom_utilisateur"), rs.getString("prenom_utilisateur"), rs.getString("email_utilisateur"), rs.getString("password_utilisateur"), rs.getDate("date_naissance"), rs.getDate("date_embauche"), rs.getString("adresse_utilisateur"), rs.getInt("ce_role"), rs.getString("numero_utilisateur"));
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

        return user;
    }

    /**
     *
     *
     * @return le nombre de modification
     */
    int modifyUser(User user) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE utilisateur SET nom_utilisateur = ?,prenom_utilisateur = ?,email_utilisateur = ?,password_utilisateur = ?,date_naissance = ?,date_embauche = ?,adresse_utilisateur = ?,ce_role = ?,numero_utilisateur = ? WHERE id_utilisateur = ?");

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getPassword());
            statement.setDate(5, user.getBirth());
            statement.setDate(6, user.getHiring());
            statement.setString(7, user.getAddress());
            statement.setInt(8, user.getRole());
            statement.setString(9, user.getNumber());
            statement.setInt(10, user.getReference_user());

            //Ex�cution de la requ�te
            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            //fermeture du preparedStatement et de la connexion
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception t) {
            }
        }

        return returnCode;
    }

}
