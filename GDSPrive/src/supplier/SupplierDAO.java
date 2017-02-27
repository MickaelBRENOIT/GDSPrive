/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikael
 */
public class SupplierDAO {

    private Connection connection;
    private PreparedStatement statement;

    public SupplierDAO() {
        connection = null;
        statement = null;
    }

    public List<Supplier> getListOfAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<Supplier>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM fournisseur");
            rs = statement.executeQuery();

            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("id_fournisseur"), rs.getString("societe_fournisseur"), rs.getString("adresse_fournisseur"), rs.getString("domaine_fournisseur"), rs.getString("numero_fournisseur"), rs.getString("email_fournisseur")));
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

        return suppliers;

    }

    public List<Supplier> getListSuppliersByADomain(String domain) {
        List<Supplier> suppliers = new ArrayList<Supplier>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM fournisseur WHERE domaine_fournisseur = ?");
            statement.setString(1, domain);
            rs = statement.executeQuery();

            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("id_fournisseur"), rs.getString("societe_fournisseur"), rs.getString("adresse_fournisseur"), rs.getString("domaine_fournisseur"), rs.getString("numero_fournisseur"), rs.getString("email_fournisseur")));
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

        return suppliers;
    }

    public int addSupplier(Supplier supplier) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO fournisseur (societe_fournisseur,adresse_fournisseur,domaine_fournisseur,numero_fournisseur,email_fournisseur) VALUES ( ?, ?, ?, ?,?)");

            statement.setString(1, supplier.getCompany());
            statement.setString(2, supplier.getAddress());
            statement.setString(3, supplier.getField());
            statement.setString(4, supplier.getPhoneNumber());
            statement.setString(5, supplier.getMail());

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

    public int deleteSupplier(String reference) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("DELETE FROM fournisseur WHERE id_fournisseur = ? ");

            statement.setString(1, reference);

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

    public Supplier getASupplier(String reference) {
        Supplier supplier = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM fournisseur WHERE id_fournisseur = ?");
            statement.setString(1, reference);
            rs = statement.executeQuery();

            if (rs.next()) {
                supplier = new Supplier(rs.getInt("id_fournisseur"), rs.getString("societe_fournisseur"), rs.getString("adresse_fournisseur"), rs.getString("domaine_fournisseur"), rs.getString("numero_fournisseur"), rs.getString("email_fournisseur"));
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

        return supplier;
    }

    public int modifySupplier(Supplier supplier) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE fournisseur SET societe_fournisseur = ?, adresse_fournisseur = ?, domaine_fournisseur = ?, numero_fournisseur = ?, email_fournisseur = ? WHERE id_fournisseur = ?");

            statement.setString(1, supplier.getCompany());
            statement.setString(2, supplier.getAddress());
            statement.setString(3, supplier.getField());
            statement.setString(4, supplier.getPhoneNumber());
            statement.setString(5, supplier.getMail());
            statement.setInt(6, supplier.getReference());

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

    public int idSupplier(String nomSociete) {
        int id = 0;
        ResultSet rs = null;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("SELECT id_fournisseur FROM fournisseur WHERE societe_fournisseur = ? ");

            statement.setString(1, nomSociete);

            //Ex�cution de la requ�te
            rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_fournisseur");
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
        return id;
    }
     public List<String> getListSuppliersOrderByPrice(String nomProduit) {
        List<String> suppliersOrderPrice = new ArrayList<String>();
        ResultSet rs = null;
        
        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT fournisseur.societe_fournisseur,nom_produit,prix_unitaire FROM produit "
                                                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur"
                                                    + " WHERE nom_produit=? "
                                                    + " ORDER BY prix_unitaire ASC"
                                                    
                                                );
              statement.setString(1, nomProduit);
            
            rs = statement.executeQuery();

        
            while (rs.next()) {
                suppliersOrderPrice.add("Fournisseur: "+rs.getString("societe_fournisseur")+" Produit : "+rs.getString("nom_produit")+" Prix: "+rs.getDouble("prix_unitaire"));
              
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

        return suppliersOrderPrice;
    }   
}
