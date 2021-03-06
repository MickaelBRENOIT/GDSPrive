package product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;
    private PreparedStatement statement;

    /**
     * initialisation de la connexion et de la requete préparée à null
     */
    public ProductDAO() {
        connection = null;
        statement = null;
    }

    /**
     *
     * @return la liste de tous les produits
     */
    public List<Product> getListOfAllProducts() {
        List<Product> products = new ArrayList<Product>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM produit");

            rs = statement.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getInt("id_produit"), rs.getString("nom_produit"), rs.getDouble("prix_unitaire"), rs.getString("quantite"), rs.getDate("date_expiration"), rs.getString("societe_fournisseur"), rs.getInt("ce_fournisseur"), rs.getInt("stock_minimal")));
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

        return products;

    }

    /**
     *
     * @param nom le nom du produit selectionné
     * @return la liste des produits en fonction du nom de produit
     */
    public List<Product> getListProductsByNom(String nom) {
        List<Product> products = new ArrayList<Product>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM produit WHERE nom_produit = ?");
            statement.setString(1, nom);
            rs = statement.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getString("nom_produit"), rs.getDouble("prix_unitaire"), rs.getString("quantite"), rs.getDate("date_expiration"), rs.getString("societe_fournisseur"), rs.getInt("ce_fournisseur"), rs.getInt("stock_minimal")));
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

        return products;
    }

    /**
     *
     * @param product le nom du produit à ajouter
     * @return le nombre de produit ajouté
     */
    public int addProduct(Product product) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("INSERT INTO produit (nom_produit,prix_unitaire,quantite,date_expiration,societe_fournisseur,ce_fournisseur,stock_minimal) VALUES ( ?, ?, ?, ?,?,?,?)");

            statement.setString(1, product.getNomProduit());
            statement.setDouble(2, product.getPrixUnitaire());
            statement.setString(3, product.getQuantite());
            statement.setDate(4, (Date) product.getDateExpiration());
            statement.setString(5, product.getNomFournisseur());
            statement.setInt(6, product.getCeFournisseur());
            statement.setInt(7, product.getStockMin());

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
     * @param reference la reference du produit
     * @return le nombre de produit supprimé
     */
    public int deleteProducts(String reference) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("DELETE FROM produit WHERE id_produit = ? ");

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

    Product getAProduct(String id) {
        Product product = null;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT * FROM produit WHERE id_produit = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                product = new Product(rs.getInt("id_produit"), rs.getString("nom_produit"), rs.getDouble("prix_unitaire"), rs.getString("quantite"), rs.getDate("date_expiration"), rs.getString("societe_fournisseur"), rs.getInt("ce_fournisseur"), rs.getInt("stock_minimal"));
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

        return product;
    }

    int modifyProduct(Product product) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE produit SET nom_produit = ?,prix_unitaire = ?,quantite = ?,date_expiration = ?,societe_fournisseur = ?,ce_fournisseur = ?,stock_minimal=? WHERE id_produit = ?");

            statement.setString(1, product.getNomProduit());
            statement.setDouble(2, product.getPrixUnitaire());
            statement.setString(3, product.getQuantite());
            statement.setDate(4, product.getDateExpiration());
            statement.setString(5, product.getNomFournisseur());
            statement.setInt(6, product.getCeFournisseur());
            statement.setInt(7, product.getStockMin());
            statement.setInt(8, product.getReference());

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
     * @return la liste de tous les forunisseurs
     */
    public List<Product> getListOfAllFournisseurs() {
        List<Product> products = new ArrayList<Product>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT societe_fournisseur FROM fournisseur");

            rs = statement.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getString("societe_fournisseur")));
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

        return products;

    }

    /**
     *
     * @return la liste de tous les produits et des fournisseurs
     */
    public List<String> getListOfAlluppliersAndProducts() {
        List<String> suppliersAndProducts = new ArrayList<String>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT fournisseur.societe_fournisseur, nom_produit FROM produit "
                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur");

            rs = statement.executeQuery();

            while (rs.next()) {
                suppliersAndProducts.add(rs.getString("societe_fournisseur") + " - " + rs.getString("nom_produit"));
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

        return suppliersAndProducts;
    }

    /**
     *
     * @param company le nom de la societe
     * @param product le nom du produit
     * @return le prix en fonction du fournisseur et du produit
     */
    public double getUnitPriceByCompanyAndProduct(String company, String product) {
        double unitPrice = 0;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT prix_unitaire FROM produit "
                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur "
                    + "WHERE produit.nom_produit = ? AND fournisseur.societe_fournisseur = ?");

            statement.setString(1, product);
            statement.setString(2, company);

            rs = statement.executeQuery();

            if (rs.next()) {
                unitPrice = rs.getDouble("prix_unitaire");
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

        return unitPrice;
    }

    /**
     *
     * @param company le nom de la societe
     * @param product le nom du produit
     * @return la quantité des produits
     */
    public int getQuantityOfAProduct(String company, String product) {
        int quantity = 0;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT quantite FROM produit "
                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur "
                    + "WHERE produit.nom_produit = ? AND fournisseur.societe_fournisseur = ?");

            statement.setString(1, product);
            statement.setString(2, company);

            rs = statement.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("quantite");
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

        return quantity;
    }

    /**
     *
     * @param company le nom de la compagnie
     * @param product le nom du produit
     * @return les identifiants
     */
    public int getProductIdByCompanyAndProductNames(String company, String product) {
        int id = 0;
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_produit FROM produit "
                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur "
                    + "WHERE produit.nom_produit = ? AND fournisseur.societe_fournisseur = ?");

            statement.setString(1, product);
            statement.setString(2, company);

            rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_produit");
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

        return id;
    }

    /**
     *
     * @param last_insert_id le dernier identifiant inséré
     * @return le nombre de mise à jours éffectués
     */
    public int decreaseProductQuantity(int last_insert_id) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE commande_article "
                    + "INNER JOIN produit ON commande_article.ce_produit = produit.id_produit "
                    + "INNER JOIN commande ON commande_article.ce_commande = commande.id_commande "
                    + "SET produit.quantite = produit.quantite - commande_article.quantite "
                    + "WHERE commande.id_commande = ?");

            statement.setInt(1, last_insert_id);

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
     * @param id l'identifiant du produit
     * @return une mise à jour du produit
     */
    public int increaseProductQuantity(String id) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE commande_article "
                    + "INNER JOIN produit ON commande_article.ce_produit = produit.id_produit "
                    + "INNER JOIN commande ON commande_article.ce_commande = commande.id_commande "
                    + "SET produit.quantite = produit.quantite + commande_article.quantite "
                    + "WHERE commande.id_commande = ?");

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
     * @param last_insert_id l'identifiant du produit
     * @return la liste de tous les produits dont la quantité est inferieur au
     * stock minimal
     */
    public List<Product> getListOfAllProductsWhichHaveQuantityInferiorStock(int last_insert_id) {
        List<Product> products = new ArrayList<Product>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT commande_article.ce_produit, produit.stock_minimal FROM commande_article "
                    + "INNER JOIN produit ON commande_article.ce_produit = produit.id_produit "
                    + "INNER JOIN commande ON commande_article.ce_commande = commande.id_commande "
                    + "WHERE commande.id_commande = ? AND produit.quantite < produit.stock_minimal");

            statement.setInt(1, last_insert_id);

            rs = statement.executeQuery();

            while (rs.next()) {
                products.add(new Product(rs.getInt("commande_article.ce_produit"), rs.getInt("produit.stock_minimal")));
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

        return products;
    }

    /**
     *
     * @param reference la reference du produit
     * @param replenishment
     * @return
     */
    public int increaseProductQuantityByReplenishment(int reference, int replenishment) {
        int returnCode = 0;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("UPDATE produit SET quantite = quantite + ? WHERE id_produit = ?");

            statement.setInt(1, replenishment);
            statement.setInt(2, reference);

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
     * @param nomProduit le nom du produit
     * @return l'identifiant du produit en fonction du nom
     */
    public int idProduct(String nomProduit) {
        int id = 0;
        ResultSet rs = null;
        try {

            //tentative de connexion
            connection = singleton.Singleton.getConnection();
            //pr�paration de l'instruction SQL, chaque ? repr�sente une valeur � communiquer dans l'insertion
            //les getters permettent de r�cup�rer les valeurs des attributs souhait�s de nouvArticle
            statement = connection.prepareStatement("SELECT id_produit FROM produit WHERE nom_produit = ? ");

            statement.setString(1, nomProduit);

            //Ex�cution de la requ�te
            rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_produit");
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

    /**
     *
     * @return la liste de tous les fournisseurs en fonction du produit
     */
    public List<String> getListOfFournisseursByProducts() {
        List<String> products = new ArrayList<String>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT fournisseur.societe_fournisseur FROM produit "
                    + "INNER JOIN fournisseur ON produit.ce_fournisseur = fournisseur.id_fournisseur ");

            rs = statement.executeQuery();

            while (rs.next()) {
                products.add(rs.getString("societe_fournisseur"));
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

        return products;

    }

    /**
     *
     * @return la liste des produits sans doublons
     */
    public List<String> getListOfAllProductsDistinct() {
        List<String> products = new ArrayList<String>();
        ResultSet rs = null;

        try {
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT DISTINCT nom_produit FROM produit");

            rs = statement.executeQuery();

            while (rs.next()) {
                products.add(rs.getString("nom_produit"));
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

        return products;

    }

}
