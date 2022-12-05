import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Wallet implements IWallet{

    public void payWithWallet(User user,double price,String serviceName,String spName) {
        if (user.getWallet()>=price) {
            String query = "INSERT INTO transactions " + " (serviceName,username,serviceProvider,price) " +
                    " VALUES(?,?,?,?);";
            Database.connect();
            PreparedStatement statement = null;
            try {
                statement = Database.connection.prepareStatement(query);
                statement.setString(1, serviceName);
                statement.setString(2, user.getUserName());
                statement.setString(3, spName);
                statement.setDouble(4, price);
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    System.out.println("===============(: Thanks For Using "+spName+" :)===============");
                }
            }
            catch (SQLException e) {
                System.out.println("Error");
            }
        }
        else {
            System.out.println("No enough Money on the wallet");
        }
    }
}
