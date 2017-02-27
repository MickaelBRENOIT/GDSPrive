/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Mikael
 */
public class SupplierOrderDAO {
    private Connection connection;
    private PreparedStatement statement;

    public SupplierOrderDAO() {
        connection = null;
        statement = null;
    }

    public int addSupplierOrder(SupplierOrder supplierOrder) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO commande_fournisseur (ce_produit, date_commande, quantite) VALUES (?, ?, ?)");

            statement.setInt(1, supplierOrder.getFk_product());
            statement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            statement.setInt(3, supplierOrder.getQuantity() * 10);
            

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
