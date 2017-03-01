package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//https://ibytecode.com/blog/jdbc-mysql-create-database-example/
public class Singleton {

    //static reference to itself
    private static Singleton instance = new Singleton();

    /**
     * l'adresse du serveur local
     */
    public static final String URL = "jdbc:mysql://localhost/stocks";

    /**
     * le nom d'utilisateur pour acceder à la base
     */
    public static final String USER = "root";

    /**
     * Le mot de passe pour l'accès à la base
     */
    public static final String PASSWORD = "";

    /**
     * la librairie
     */
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    //private constructor
    private Singleton() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    /**
     *
     * @return l'instance de connexion
     */
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
