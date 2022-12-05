import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Wallet extends Pay {

    public void pay(User user,String serviceName,String spName) {
        double price=Discount.calcTotal(serviceName,spName);
        if (user.getWallet()>=price) {
            String query = "INSERT INTO transactions " + " (\"serviceName\",username,\"serviceProvider\",price) " +
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
                    user.setWallet(user.getWallet()-price);
                }
                query ="UPDATE users SET wallet= "+user.getWallet()+",first_time="+false+" WHERE  username='"+user.getUserName()+"'";
                Statement statement1=Database.connection.createStatement();
                statement1.executeUpdate(query);
            }
            catch (SQLException e) {
                System.out.println(e);
                System.out.println("Error");
            }
        }
        else {
            System.out.println("No enough Money on the wallet");
        }
    }


    public void recharge(User user,String cardNumber,double amount){
        String query ="Select * from cards where username='"+user.getUserName()+"' and \"cardNumber\"='"+cardNumber+"'";
        Database.connect();
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if (resultSet.next()){
                if (resultSet.getDouble("amount")>=amount){
                    query="UPDATE public.cards SET amount="+(resultSet.getDouble("amount")-amount)+" WHERE username='"+user.getUserName()+
                            "' and \"cardNumber\"='"+cardNumber+"'";
                    statement.executeUpdate(query);
                    query="UPDATE public.users SET  wallet="+amount+" WHERE username='"+user.getUserName()+"'";
                    statement.executeUpdate(query);
                }
                else {
                    System.out.println("No Money Enough To ReCharge");
                }
            }
            else{
                System.out.println("No Card Found");
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }

    }

}
