package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TemporaryOrderItemDAO {

    private Connection connection;
    private PreparedStatement statement;

    /**
     * initialise la connexion et les requets preparées à null
     */
    public TemporaryOrderItemDAO() {
        connection = null;
        statement = null;
    }

    /**
     * Permet d'ajouter une commande temporaire
     *
     * @param temporaryOrderItem - la commande temporaire à ajouter
     * @return - retourne le nombre de lignes qui a été ajoutées
     */
    public int addTemporaryOrderItem(TemporaryOrderItem temporaryOrderItem) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("INSERT INTO commande_temporaire (nom_societe, nom_produit, prix_unitaire, quantite, total) VALUES ( ?, ?, ?, ?, ?)");

            statement.setString(1, temporaryOrderItem.getCompany_name());
            statement.setString(2, temporaryOrderItem.getProduct_name());
            statement.setDouble(3, temporaryOrderItem.getUnit_price());
            statement.setInt(4, temporaryOrderItem.getQuantity());
            statement.setDouble(5, temporaryOrderItem.getTotal_price());

            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
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
     * @return - retourne la liste de toutes les commandes temporaires
     */
    public List<TemporaryOrderItem> getListOfAllTemporaryOrderItems() {

        List<TemporaryOrderItem> temporaryOrderItems = new ArrayList<TemporaryOrderItem>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM commande_temporaire");
            rs = statement.executeQuery();

            while (rs.next()) {
                temporaryOrderItems.add(new TemporaryOrderItem(rs.getInt("id_commande_temporaire"), rs.getString("nom_societe"), rs.getString("nom_produit"), rs.getDouble("prix_unitaire"), rs.getInt("quantite"), rs.getDouble("total")));
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

        return temporaryOrderItems;
    }

    /**
     * Permet de supprimer une commande temporaire désignée par son id
     *
     * @param id - id d'une commande temporaire
     * @return - retourne le nombre de lignes qui a été supprimés
     */
    public int deleteTemporaryOrderItem(String id) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("DELETE FROM commande_temporaire WHERE id_commande_temporaire = ? ");

            statement.setString(1, id);

            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
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
     * Permet de récupérer une commande temporaire par son id
     *
     * @param id - id de la commande temporaire
     * @return - retourne une commande temporaire
     */
    public TemporaryOrderItem selectTemporaryOrderItemById(String id) {
        TemporaryOrderItem temporaryOrderItem = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM commande_temporaire WHERE id_commande_temporaire = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                temporaryOrderItem = new TemporaryOrderItem(rs.getInt("id_commande_temporaire"), rs.getString("nom_societe"), rs.getString("nom_produit"), rs.getDouble("prix_unitaire"), rs.getInt("quantite"), rs.getDouble("total"));
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

        return temporaryOrderItem;
    }

    /**
     * Permet de modifier une commande temporaire
     *
     * @param toi - commande temporaire à modifier
     * @return - retourne le nombre de ligne qui a été modifiées
     */
    public int modifyTemporaryOrderItem(TemporaryOrderItem toi) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("UPDATE commande_temporaire SET nom_societe = ?, nom_produit = ?, prix_unitaire = ?, quantite = ?, total = ? WHERE id_commande_temporaire = ?");

            statement.setString(1, toi.getCompany_name());
            statement.setString(2, toi.getProduct_name());
            statement.setDouble(3, toi.getUnit_price());
            statement.setInt(4, toi.getQuantity());
            statement.setDouble(5, toi.getTotal_price());
            statement.setInt(6, toi.getReference());

            returnCode = statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
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
     * Permet de supprimer toutes les lignes de la table commande temporaire et
     * de mettre à zéro les champs à auto-incrémentation
     */
    public void clearTemporaryDatabase() {
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("TRUNCATE commande_temporaire");

            statement.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
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
    }

}
