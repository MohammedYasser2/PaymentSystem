import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUp {
    public SignUp(User user) {
        Database.connect();
        boolean check=isExist(user.getUserName(),user.getEmail());
        if (check){
            String query="INSERT INTO public.users(" +
                    "username, name, email, password, wallet, first_time)" +
                    "VALUES ('"+user.getUserName()+"', '"+user.getName()+"', '"+user.getEmail()+"', '"+user.getPassword()
                    +"', 0.0, true);";
            try {
                Statement statement=Database.connection.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("Error");
            }

        }
        Database.disconnect();
    }
    public boolean isExist(String username,String email){
        String query="Select * From users where username='"+username+"';";
        String query2="Select * From users where email='"+email+"';";
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if (resultSet.next()){
                resultSet=statement.executeQuery(query2);
                System.out.println("This UserName is Already Exists");
                if (resultSet.next()){
                    System.out.println("This Email is Already Exists");
                    return false;
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println("IsExist Error");
        }
        return true;
    }
}
