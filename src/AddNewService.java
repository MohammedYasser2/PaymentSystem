import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewService {
    public AddNewService(String serviceName,String serviceProvider,double price,double discount){
        Database.connect();
        String query="Insert into services (\"serviceName\", \"serviceProvider\", price, discount)VALUES ('"+serviceName
                +"', '"+serviceProvider+"',"+price+","+discount+")";
        try {
            if(!isExist(serviceName,serviceProvider)) {
                Statement statement = Database.connection.createStatement();
                statement.executeUpdate(query);
                System.out.println("Done");
            }
            else {
                System.out.println("This Service Already Exists");
            }
        } catch (SQLException e) {
            System.out.println("Error!");
        }
        Database.disconnect();
    }

    public boolean isExist(String serviceName,String serviceProvider){
        String query="select * from services where \"serviceName\"='"+serviceName+"'and \"serviceProvider\"='"+serviceProvider+"'";
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if(resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error!");
        }
        return true;
    }
}
