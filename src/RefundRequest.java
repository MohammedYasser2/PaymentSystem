import java.sql.*;


public class RefundRequest {

	private int paymentID;

	public RefundRequest(int paymentID) {
		this.paymentID = paymentID;
		Database.connect();
	}

	private boolean isRefunded() {

		boolean refunded = false;
		String query = "SELECT  * FROM transactions WHERE payment_id = " + paymentID;

		Statement statement = null;
		try {
			statement = Database.connection.createStatement();

		ResultSet result  = statement.executeQuery(query);
		if(result.next()) {
			refunded = result.getBoolean("refund");
		}
		} catch (SQLException e) {
			System.out.println("Error");
		}
		return refunded;
	}

	public boolean makeRefundRequest(int payment_id)  {

		System.out.println(isRefunded());

		if(isRefunded()) {return false;}

		String query = "INSERT INTO refund (payment_id)" + "VALUES(?)";

		PreparedStatement statement = null;
		try {
			statement = Database.connection.prepareStatement(query);

			statement.setInt(1, payment_id);
			statement.executeUpdate();
			Database.disconnect();
		} catch (SQLException e) {
			System.out.println("Error");
		}
		return true;

	}
	
	

}
