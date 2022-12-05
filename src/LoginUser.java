import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUser {
    private User user;

    public LoginUser(String userName, String password) {
         user=checkData(userName,password);
    }
    public User checkData(String userName,String password){
        User newUser=null;
        String query="Select * from users where username ='"+userName+"'and password ='"+password+"';";
        Database.connect();
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if (resultSet.next()){
                String email,name;
                double wallet;
                boolean firstTime;
                email=resultSet.getString("email");
                name=resultSet.getString("name");
                wallet=resultSet.getDouble("wallet");
                firstTime=resultSet.getBoolean("first_time");
                newUser=new User(userName,password,email,name,wallet,firstTime);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        Database.disconnect();
        return newUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
