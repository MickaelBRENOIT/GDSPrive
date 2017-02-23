/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order.orderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Mikael
 */
public class OrderItemDAO {
    private Connection connection;
    private PreparedStatement statement;

    public OrderItemDAO() {
        connection = null;
        statement = null;
    }

    public int addOrderItems(OrderItem orderItem) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO commande_article (ce_commande, ce_produit, quantite) VALUES (?, ?, ?)");

            statement.setInt(1, orderItem.getFk_order());
            statement.setInt(2, orderItem.getFk_product());
            statement.setInt(3, orderItem.getQuantity());
            

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
