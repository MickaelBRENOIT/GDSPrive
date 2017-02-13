package singleton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//https://ibytecode.com/blog/jdbc-mysql-create-database-example/

public class Singleton {
    //static reference to itself
    private static Singleton instance = new Singleton();
    public static final String URL = "jdbc:mysql://localhost/stocks";
    public static final String USER = "root";
    public static final String PASSWORD = "";
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
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
