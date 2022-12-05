import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Discount {
    double percentage;
    public Discount(String serviceName,String spName,double percentage){
        this.percentage=percentage;
        Database.connect();
        String query ="UPDATE public.services SET discount="+percentage +" WHERE \"serviceName\"='"+ serviceName +"'and \"serviceProvider\"= '"+spName+"'";
        try {
            Statement statement=Database.connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("Error");
        }
        Database.disconnect();
    }
    public static double calcTotal(String serviceName,String spName){
        Database.connect();
        String query="SELECT * FROM public.services where \"serviceName\" = '"+serviceName+"' and  \"serviceProvider\" = '"+spName+"'";
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if (resultSet.next()){
                double total=resultSet.getInt("price")-(resultSet.getInt("price")*resultSet.getInt("discount")/100);
                return total;
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return 0;
    }
}
