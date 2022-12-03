package com.admin;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;

public class RefundResponse {
	
	private String refundID;
	Connection connection; 
	
	RefundResponse(){
		
		connection = DatabaseConnection.getConnection();
	}
	
	public void setRefundID(String refundID) {
		this.refundID = refundID;
		}
	
	
	
	private void deleteRquest(int refundID) throws SQLException {
		
		String query = "DELETE FROM refund WHERE refund_id =? ";
		
		PreparedStatement statement = connection.prepareStatement(query);	
		
		statement.setInt(1, refundID);
		statement.executeUpdate();
	}
	
	
	
	private String getPaymentID() throws SQLException {
		
		String query = "SELECT payment_id FROM refund "
				+ "WHERE refund_id = " + refundID + ";";
		
		 Statement statement = connection.createStatement();
		 ResultSet result  = statement.executeQuery(query);	
		 
		 String paymentID = null;
		 
		 if(result.next()) {
		        paymentID = Integer.toString(result.getInt("payment_id"));
		 }
		 
		 deleteRquest(Integer.parseInt(refundID));
		 return paymentID;
	}
	
	private String getUser() throws SQLException {
		
		String paymentID = getPaymentID();
		
		String query = "SELECT * FROM payment "
				+ "WHERE id = " +  paymentID + ";";
		
		 Statement statement = connection.createStatement();
		 ResultSet result  = statement.executeQuery(query);	
		 
		 String username = null ;
		 if(result.next()) {
			  username = result.getString("username");
		 }
		 System.out.println(username);
		 
		 return username;
	}
	
	
	public void acceptRefund() throws SQLException {
		
		 String  username = getUser();
		 System.out.println(username);
		 
			Integer wallet = 30;
			String query = "UPDATE users SET wallet =? "
					+ "WHERE username =? ;";
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, wallet);
			statement.setString(2, username);
		    int row = statement.executeUpdate();
		    if(row > 0) {
		    	System.out.println("Succsesful Operation.");
		    }
		    else {
		    	System.out.println("Please Try again Later");
		    }
			
		 
	}
	
	
	
	

}
