import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundList {


	public RefundList() {
		Database.connect();
	}
	
	public void getRefundList() throws SQLException {
		
		String query = "SELECT refund_id,payment.id, payment.amount, payment.service_name, payment.service_provider FROM payment INNER JOIN refund ON payment.id = refund.payment_id;";
		
	    Statement statement = Database.connection.createStatement();
		ResultSet result  = statement.executeQuery(query);	
		
		System.out.println("Refund Number    "+"Amount    "+"Service Name  "+"Service Provider  ");
		
		while(result.next()) {
			int refund_id = result.getInt("refund_id");
			int amount = result.getInt("amount");
			String serviceName = result.getString("service_name");
			String serviceProvider = result.getString("service_provider");				
			System.out.println(Integer.toString(refund_id)+"              "+Integer.toString(amount)+"        "+serviceName  +"          " +serviceProvider);
				
			}
		Database.disconnect();
	}
	
}
