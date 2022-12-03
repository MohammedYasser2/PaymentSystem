import java.sql.*;
public class Database {
	
	 public static Connection  connection;

	 public static void connect() {
    	  
 		 String jdbcURL = "jdbc:postgresql://localhost:5432/PaymentSystem";
	  	 String username = "postgres";
	  	 String password = "1234";
	  	 
    	try {
  	    connection = DriverManager.getConnection(jdbcURL,username,password);
  		} catch (SQLException e) {
  			 System.out.println("Connection Error");
  		}
    }


	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error occur");
		}
	}

	
}

