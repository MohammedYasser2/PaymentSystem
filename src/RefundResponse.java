
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RefundResponse {
	
	private int refundID;

	public RefundResponse() {
		Database.connect();
	}

	
	private void deleteRequest(int refundID) {
		String query = "DELETE FROM refund WHERE refund_id =? ";

		PreparedStatement statement = null;
		try {
			statement = Database.connection.prepareStatement(query);
			statement.setInt(1, refundID);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

	private int getPaymentID() throws SQLException {
		
		String query = "SELECT transaction_id FROM refund "
				+ "WHERE refund_id = " + refundID + ";";
		
		 Statement statement = Database.connection.createStatement();
		 ResultSet result  = statement.executeQuery(query);	
		 
		 int paymentID=0;
		 
		 if(result.next()) {
		        paymentID = result.getInt("transaction_id");
		 }
		 
		 deleteRequest(refundID);
		 return paymentID;
	}
	
	private User getUser() {
		User user = null ;
		int paymentID = 0;
		try {
			paymentID = getPaymentID();
		String query = "SELECT * FROM transactions"
				+ " WHERE id = " +  paymentID + ";";
		
		 Statement statement = Database.connection.createStatement();
		 ResultSet result  = statement.executeQuery(query);	
		 

		 if(result.next()) {
			 user=new User();
			  user.setUserName ( result.getString("username"));
			  user.setWallet(result.getDouble("price"));
		 }
		 Statement statement1 =Database.connection.createStatement();
		 query="UPDATE public.transactions SET refund=true WHERE id="+paymentID;
		 statement1.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error");;
		}


		return user;
	}
	
	
	public void acceptRefund() {

		User  user = null;
		try {
			user = getUser();
			String query ="Select * from users where username='"+user.getUserName()+"'";
			Statement statement1=Database.connection.createStatement();
			ResultSet resultSet=statement1.executeQuery(query);
			if (resultSet.next()) {
				user.setWallet(user.getWallet()+resultSet.getDouble("wallet"));
				query = "UPDATE users SET wallet =? "
						+ "WHERE username =? ;";
				PreparedStatement statement = Database.connection.prepareStatement(query);

				statement.setInt(1, (int) user.getWallet());
				statement.setString(2, user.getUserName());
				int row = statement.executeUpdate();
				if (row > 0) {
					System.out.println("Successful Operation.");
				} else {
					System.out.println("Please Try again Later");
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Error");
		}
		 
	}

	public void setRefundID(int refundID) {
		this.refundID = refundID;
	}
	
	

}
