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
    private Authentication authentication;
    private ResultSet rs;

    public AuthenticationDAO() {
        connection = null;
        statement = null;
    }
    
    public Authentication signIn(Authentication auth){
        
        authentication = null;
        rs = null;
        
        try{
            connection = singleton.Singleton.getConnection();
            statement = connection.prepareStatement("SELECT id_utilisateur, email_utilisateur, password_utilisateur, ce_role FROM utilisateur WHERE email_utilisateur = ? AND password_utilisateur = ?");
            statement.setString(1, auth.getLogin());
            statement.setString(2, auth.getPassword());
            rs = statement.executeQuery();
            
            System.out.println(">>> login : "+ auth.getLogin()+ " >>> mdp : "+auth.getPassword());
            
            if(rs.next()){
                authentication = new Authentication(rs.getString("email_utilisateur"), rs.getString("password_utilisateur"), rs.getInt("ce_role"));
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
