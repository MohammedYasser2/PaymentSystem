package database;

import java.sql.*;


public class DatabaseConnection {
	
	 private static Connection  connection;
	 
	   DatabaseConnection(){
	 }
	  
      public static Connection getConnection() {
    	  
 		 String jdbcURL = "jdbc:postgresql://localhost:5432/PayementSystem";
	  	 String username = "postgres";
	  	 String password = "123456";
	  	 
    	try {
  	    connection = DriverManager.getConnection(jdbcURL,username,password);
  		//System.out.println("connected");
  		//connection.close();
  		
  		} catch (SQLException e) {
  			 System.out.println("Connection Error");
  			 e.printStackTrace();
  		}
    	return connection;
    
    }

	
}

