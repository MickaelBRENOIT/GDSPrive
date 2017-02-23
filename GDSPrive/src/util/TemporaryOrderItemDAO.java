/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael
 */
public class TemporaryOrderItemDAO {
    
    private Connection connection;
    private PreparedStatement statement;

    public TemporaryOrderItemDAO() {
        connection = null;
        statement = null;
    }

    public int addTemporaryOrderItem(TemporaryOrderItem temporaryOrderItem) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO commande_temporaire (nom_societe, nom_produit, prix_unitaire, quantite, total) VALUES ( ?, ?, ?, ?, ?)");

            statement.setString(1, temporaryOrderItem.getCompany_name());
            statement.setString(2, temporaryOrderItem.getProduct_name());
            statement.setDouble(3, temporaryOrderItem.getUnit_price());
            statement.setInt(4, temporaryOrderItem.getQuantity());
            statement.setDouble(5, temporaryOrderItem.getTotal_price());
            

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

    public int deleteTemporaryOrderItem(String id) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("DELETE FROM commande_temporaire WHERE id_commande_temporaire = ? ");

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

    public int modifyTemporaryOrderItem(TemporaryOrderItem toi) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE commande_temporaire SET nom_societe = ?, nom_produit = ?, prix_unitaire = ?, quantite = ?, total = ? WHERE id_commande_temporaire = ?");

            statement.setString(1, toi.getCompany_name());
            statement.setString(2, toi.getProduct_name());
            statement.setDouble(3, toi.getUnit_price());
            statement.setInt(4, toi.getQuantity());
            statement.setDouble(5, toi.getTotal_price());
            statement.setInt(6, toi.getReference());

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
