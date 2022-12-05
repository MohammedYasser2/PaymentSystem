package com.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;


public class RefundRequest {
    
	private int paymentID; 

	public RefundRequest(int paymentID) {
		this.paymentID = paymentID;
		Database.connect();
	}

	
	private boolean isRefunded() throws SQLException {
		
		  boolean refunded = false;
		  String query = "SELECT  * FROM Payment WHERE payment_id = " + paymentID;
		  
		  Statement statement = Database.connection.createStatement();
		  ResultSet result  = statement.executeQuery(query);	
		  if(result.next()) {
				  refunded = result.getBoolean("refund");
			 }
		  
		  return refunded;
	}
	
	public boolean makeRefundRequest(int payment_id) throws SQLException {
		
		System.out.println(isRefunded());

		if(isRefunded()) {return false;}	
		
        String query = "INSERT INTO refund (payment_id)" + "VALUES(?)";
		
		PreparedStatement statement = Database.connection.prepareStatement(query);
		
		statement.setInt(1, payment_id);
		statement.executeUpdate();
		Database.disconnect();
		
		return true;

	}
	
	
	

}