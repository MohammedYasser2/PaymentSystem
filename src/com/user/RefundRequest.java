package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.DatabaseConnection;

public class RefundRequest {
    
	private int payment_id;
	private Connection connection;
	
	public RefundRequest() {	
		connection = DatabaseConnection.getConnection();
	}
	
	
	public int getPaymentId() {
		return payment_id;
	}
	
	public void makeRefundRequest(int payment_id) throws SQLException {
		
        String query = "INSERT INTO refund (payment_id)" + "VALUES(?)";
		
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setInt(1, payment_id);
		int rows = statement.executeUpdate();
		
		if(rows > 0) {
			System.out.println("Your request has been sent");
		}

	}
	
	
	

}
