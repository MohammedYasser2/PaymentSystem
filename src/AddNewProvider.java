import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewProvider {
    public AddNewProvider(ServiceProvider serviceProvider){
        Database.connect();
        String query="Insert into \"serviceProviders\" (name, username, password)VALUES ('"+serviceProvider.getName()
                +"', '"+serviceProvider.getUserName()+"', '"+serviceProvider.getPassword()+"')";
        try {
            if(!isExist(serviceProvider.getName())) {
                Statement statement = Database.connection.createStatement();
                statement.executeUpdate(query);
                System.out.println("Done");
            }
            else {
                System.out.println("This Service Provider Already Exists");
            }
        } catch (SQLException e) {
            System.out.println("Error!");
        }
        Database.disconnect();
    }
    public boolean isExist(String name){
        String query="select * from \"serviceProviders\" where name='"+name+"'";
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
