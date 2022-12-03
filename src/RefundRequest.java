import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RefundRequest {
    
	private int payment_id;

	public RefundRequest() {
		Database.connect();
	}

	
	public int getPaymentId() {
		return payment_id;
	}
	
	public void makeRefundRequest(int payment_id) throws SQLException {
		
        String query = "INSERT INTO refund (payment_id)" + "VALUES(?)";
		
		PreparedStatement statement = Database.connection.prepareStatement(query);
		
		statement.setInt(1, payment_id);
		int rows = statement.executeUpdate();
		
		if(rows > 0) {
			System.out.println("Your request has been sent");
		}

		Database.disconnect();
	}
	
	
	

}
