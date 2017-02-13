/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Mikael
 */
public class AuthenticationDAO {
    private Connection connection;
    private PreparedStatement statement;

    public AuthenticationDAO() {
    }
    
    public Authentication signIn(Authentication auth){
        
        connection = null;
        statement = null;
        Authentication authentication = null;
        ResultSet rs = null;
        
        try{
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_utilisateur, prenom_utilisateur, nom_utilisateur, ce_role FROM utilisateur WHERE prenom_utilisateur = ? AND nom_utilisateur = ?");
            statement.setString(1, auth.getLogin());
            statement.setString(2, auth.getPassword());
            rs = statement.executeQuery();
            
            System.out.println("RS >>> "+rs.toString());
            
            if(rs.next()){
                authentication = new Authentication(rs.getString("prenom_utilisateur"), rs.getString("nom_utilisateur"), rs.getInt("ce_role"));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {if (rs != null)rs.close();} catch (Exception t) {}
            try {if (statement != null)statement.close();} catch (Exception t) {}
            try {if (connection != null)connection.close();} catch (Exception t) {}
        }
        
        return authentication;
    }
}
