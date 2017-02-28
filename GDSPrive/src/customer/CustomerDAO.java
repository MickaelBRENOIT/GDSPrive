package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private Connection connection;
    private PreparedStatement statement;

    /**
     * Initialise la connection et la requête préparée à null
     */
    public CustomerDAO() {
        connection = null;
        statement = null;
    }

    /**
     *
     * @return - retourne la liste de tous les clients
     */
    public List<Customer> getListOfAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM client");
            rs = statement.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id_client"), rs.getString("societe_client"), rs.getString("adresse_client"), rs.getString("domaine_client"), rs.getString("numero_client"), rs.getString("email_client")));
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

        return customers;

    }

    /**
     *
     * @param domain - le domaine de fonction
     * @return - retourne la liste des clients qui appartient à un certain
     * domaine de fonction
     */
    public List<Customer> getListCustomersByADomain(String domain) {
        List<Customer> customers = new ArrayList<Customer>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM client WHERE domaine_client = ?");
            statement.setString(1, domain);
            rs = statement.executeQuery();

            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id_client"), rs.getString("societe_client"), rs.getString("adresse_client"), rs.getString("domaine_client"), rs.getString("numero_client"), rs.getString("email_client")));
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

        return customers;
    }

    /**
     *
     * @param customer - le client à ajouter
     * @return - retourne le nombre d'ajout de client
     */
    public int addCustomer(Customer customer) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("INSERT INTO client (societe_client,adresse_client,domaine_client,numero_client,email_client) VALUES ( ?, ?, ?, ?,?)");

            statement.setString(1, customer.getCompany());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getField());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getMail());

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
     * @param reference - l'identifiant unique du client (son code)
     * @return - retourne le nombre de suppression de client
     */
    public int deleteCustomer(String reference) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("DELETE FROM client WHERE id_client = ? ");

            statement.setString(1, reference);

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
     * @param reference - l'identifiant d'un client
     * @return - retourne le client qui correspond à la référence passée en
     * paramètre
     */
    public Customer getACustomer(String reference) {
        Customer customer = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM client WHERE id_client = ?");
            statement.setString(1, reference);
            rs = statement.executeQuery();

            if (rs.next()) {
                customer = new Customer(rs.getInt("id_client"), rs.getString("societe_client"), rs.getString("adresse_client"), rs.getString("domaine_client"), rs.getString("numero_client"), rs.getString("email_client"));
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

        return customer;
    }

    /**
     *
     * @param customer - modifie le client passé en paramètre
     * @return - retourne le nombre de modifications de client
     */
    public int modifyCustomer(Customer customer) {
        int returnCode = 0;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("UPDATE client SET societe_client = ?, adresse_client = ?, domaine_client = ?, numero_client = ?, email_client = ? WHERE id_client = ?");

            statement.setString(1, customer.getCompany());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getField());
            statement.setString(4, customer.getPhoneNumber());
            statement.setString(5, customer.getMail());
            statement.setInt(6, customer.getReference());

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
     * @return - retourne la liste de toutes les sociétés client de
     * l'application
     */
    public List<String> getListOfAllCompanies() {
        List<String> companies = new ArrayList<String>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT societe_client FROM client ");
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

    /**
     *
     * @param compagny - la société d'un client
     * @return - retourne l'identifiant du client qui appartient à la société
     * passée en paramètre
     */
    public int getReferenceCompanyByName(String compagny) {
        int id = 0;
        ResultSet rs = null;
        try {

            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_client FROM client "
                    + "WHERE societe_client = ?");

            statement.setString(1, compagny);

            rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_client");
            }

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
        return id;

    }

}
