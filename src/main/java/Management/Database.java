package Management;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Database {
 	static String url="jdbc:mysql://127.0.0.1:3306/libMang";
	static String uname="root";
	static String pass="Pavan@123";
 
	
 // Change to your MySQL password
    public static Connection getConnection() throws SQLException,ClassNotFoundException{
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,uname,pass);
    }
}