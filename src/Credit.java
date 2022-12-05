import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Credit extends Pay {
    private String card="";
    private double amount=0;
    public Credit(){

    }
    public Credit(String card,User user, String serviceName, String spName) {
        this.card = card;
        Database.connect();
        String query="Select * from cards where username='"+user.getUserName()+"' and \"cardNumber\"='"+card+"'";
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            double price = Discount.calcTotal(serviceName, spName);
            if (resultSet.next()){
                amount=resultSet.getDouble("amount");
                if (amount>=price){
                    amount=amount-price;
                    pay(user,serviceName,spName);
                    query="UPDATE public.cards SET amount="+amount+" WHERE username='"+user.getUserName()+
                            "' and \"cardNumber\"='"+card+"'";
                    statement.executeUpdate(query);
                }
            }
            else {
                System.out.println("Not Found Card!");
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }

    }

    public void pay(User user, String serviceName, String spName) {

        String query = "INSERT INTO transactions " + " ( \"serviceName\",username,\"serviceProvider\",price) " +
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

    public void addCredit(User user,String card,double amount){
        String query="INSERT INTO public.cards(username, \"cardNumber\", amount)VALUES ('"+user.getUserName()+"','"+card
                + "', "+amount+")";
        Database.connect();
        try {
            Statement statement=Database.connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }
}
