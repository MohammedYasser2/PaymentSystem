import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundList {

	
	public static void getRefundList() {
		Database.connect();
		String query = "SELECT refund_id,transactions.id, transactions.price, transactions.\"serviceName\", transactions.\"serviceProvider\" FROM transactions,refund where transactions.id = refund.transaction_id;";

		Statement statement = null;
		try {
			statement = Database.connection.createStatement();
		ResultSet result  = statement.executeQuery(query);	
		
		System.out.println("Refund Number    "+"Price    "+"Service Name  "+"Service Provider  ");
		
		while(result.next()) {
			int refund_id = result.getInt("refund_id");
			int amount = result.getInt("price");
			String serviceName = result.getString("serviceName");
			String serviceProvider = result.getString("serviceProvider");
			System.out.println(Integer.toString(refund_id)+"              "+Integer.toString(amount)+"        "+serviceName  +"          " +serviceProvider);
				
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Error!");
		}
		Database.disconnect();
	}
	
}
