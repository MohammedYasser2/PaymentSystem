import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Credit implements ICredit {
    public void payWithCard(User user, String serviceName, String spName, String creditCardNumber) {
        String query = "INSERT INTO transactions " + " (serviceName,username,serviceProvider,price) " +
                " VALUES(?,?,?,?);";
        double price = Discount.calcTotal(serviceName, spName);
        PreparedStatement statement = null;
        try {
            statement = Database.connection.prepareStatement(query);
            statement.setString(1, serviceName);
            statement.setString(2, user.getUserName());
            statement.setString(3, spName);
            statement.setDouble(4, price);
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Your request has been sent");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
