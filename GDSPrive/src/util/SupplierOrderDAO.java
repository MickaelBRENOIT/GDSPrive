package util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SupplierOrderDAO {

    private Connection connection;
    private PreparedStatement statement;

    /**
     * initialise la connexion et les requets preparées à null
     */
    public SupplierOrderDAO() {
        connection = null;
        statement = null;
    }

    /**
     * Permet d'ajouter la commande fournisseur
     *
     * @param supplierOrder - le fournisseur à ajouter
     * @return - retourne le nombre de lignes ajoutées
     */
    public int addSupplierOrder(SupplierOrder supplierOrder) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("INSERT INTO commande_fournisseur (ce_produit, date_commande, quantite) VALUES (?, ?, ?)");

            statement.setInt(1, supplierOrder.getFk_product());
            statement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            statement.setInt(3, supplierOrder.getQuantity() * 10);

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

}
