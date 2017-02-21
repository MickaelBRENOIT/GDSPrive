/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    List<Order> getListOfAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT *, societe_client FROM commande"
                                                    + " INNER JOIN client ON commande.ce_client = client.id_client");
            rs = statement.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("id_commande"), rs.getString("societe_client"), rs.getDate("date_commande"), rs.getDate("date_limite_livraison"), rs.getDate("date_livraison")));
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
    
    public List<String> getListOfAllCompagny() {
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

    List<Order> getListOrdersByACompagny(String compagny) {
        List<Order> orders = new ArrayList<Order>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT *, societe_client FROM commande"
                                                    + " INNER JOIN client ON commande.ce_client = client.id_client"
                                                    + " WHERE client.societe_client = "+"\""+compagny+"\"");
            rs = statement.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("id_commande"), rs.getString("societe_client"), rs.getDate("date_commande"), rs.getDate("date_limite_livraison"), rs.getDate("date_livraison")));
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

}
