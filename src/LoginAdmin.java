import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginAdmin {
    private Admin admin;

    public LoginAdmin(String userName,String password) {
        admin=checkData(userName,password);
    }
    public Admin checkData(String userName,String password){
        Admin newAdmin=null;
        String query="Select * from \"serviceProviders\" where username ='"+userName+"'and password ='"+password+"';";
        Database.connect();
        try {
            Statement statement=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if (resultSet.next()){
                String name;
                name=resultSet.getString("name");
                newAdmin=new Admin(name,userName,password);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error");
        }
        Database.disconnect();
        return newAdmin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
