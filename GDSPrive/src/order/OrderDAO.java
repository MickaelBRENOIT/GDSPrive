/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael
 */
public class OrderDAO {

    private Connection connection;
    private PreparedStatement statement;

    public OrderDAO() {
        connection = null;
        statement = null;
    }

    public List<Order> getListOfAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT *, societe_client FROM commande"
                    + " INNER JOIN client ON commande.ce_client = client.id_client");
            rs = statement.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("id_commande"), rs.getString("societe_client"), rs.getDate("date_commande"), rs.getDate("date_limite_livraison"), rs.getDate("date_livraison"), rs.getDouble("prix_total")));
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

        return orders;

    }

    public List<String> getListOfAllCompanies() {
        List<String> companies = new ArrayList<String>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT societe_client FROM commande"
                    + " INNER JOIN client ON commande.ce_client = client.id_client");
            rs = statement.executeQuery();

            while (rs.next()) {
                companies.add(rs.getString("societe_client"));
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

        return companies;

    }

    public List<Order> getListOrdersByACompagny(String compagny) {
        List<Order> orders = new ArrayList<Order>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT *, societe_client FROM commande"
                    + " INNER JOIN client ON commande.ce_client = client.id_client"
                    + " WHERE client.societe_client = ? ");
            statement.setString(1, compagny);
            rs = statement.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("id_commande"), rs.getString("societe_client"), rs.getDate("date_commande"), rs.getDate("date_limite_livraison"), rs.getDate("date_livraison"), rs.getDouble("prix_total")));
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

        return orders;
    }

    int deleteOrder(String id) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("DELETE FROM commande WHERE id_commande = ? ");

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

    int addOrder(Order order) {
        int returnCode = 0;
        ResultSet rs = null;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO commande (ce_client,date_commande,date_limite_livraison,date_livraison,prix_total) VALUES ( ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, order.getFk_customer());
            statement.setDate(2, order.getOrder_date());
            statement.setDate(3, order.getDelivery_deadline());
            statement.setDate(4, order.getDelivery_date());
            statement.setDouble(5, order.getTotalPriceOrder());

            //Ex�cution de la requ�te
            returnCode = statement.executeUpdate();
            rs = statement.getGeneratedKeys();

            if (rs.next()) {
                returnCode = rs.getInt(1);
            }

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

    public Order getAOrderDate(String id) {
        Order order = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_commande,date_livraison FROM commande"
                    + " INNER JOIN client ON commande.ce_client = client.id_client "
                    + "WHERE id_commande=?");
            statement.setString(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                order = new Order(rs.getInt("id_commande"), rs.getDate("date_livraison"));

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

        return order;
    }

    public Order getAOrder(String id) {
        Order order = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_commande,societe_client FROM commande"
                    + " INNER JOIN client ON commande.ce_client = client.id_client "
                    + "WHERE id_commande=?");
            statement.setString(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                order = new Order(rs.getInt("id_commande"), rs.getString("societe_client"));

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

        return order;
    }

    public int modifyOrder(Order order) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE commande "
                    + "INNER JOIN client ON commande.ce_client = client.id_client "
                    + "SET societe_client=?,date_livraison=? WHERE id_commande=?");

            statement.setString(1, order.getCustomer_name());
            statement.setDate(2, order.getDelivery_date());
            statement.setInt(3, order.getReference());
            // System.out.println("LA REFERENCE EST:"+order.getReference());

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
