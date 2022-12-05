import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceList {
    private ArrayList<Service> services=new ArrayList<Service>();
    public ServiceList(){
        Database.connect();
        try {
            String query="Select * from services";
            Statement statement=Database.connection.createStatement();
            Statement statement1=Database.connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            while (resultSet.next()){
                String serviceName= resultSet.getString("serviceName");
                boolean check=false;
                for (int i=0;i<services.size();i++){
                    if (services.get(i).getServiceName().equals(serviceName)){
                        check=true;
                    }
                }
                if (!check){
                    String query2="Select * from services where \"serviceName\"='"+serviceName+"'";
                    ResultSet resultSet1=statement1.executeQuery(query2);
                    ArrayList<String>npName=new ArrayList<String>();
                    while (resultSet1.next()){
                        String name= resultSet1.getString("serviceProvider");
                        npName.add(name);
                    }
                    Service service=new Service(serviceName,npName);
                    services.add(service);
                }
            }
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("ServiceList Error");
        }

    }

    public ServiceList(String searchKey) {
        Database.connect();
        try {
            Statement statement =Database.connection.createStatement();
            String query2 = "Select * from services where \"serviceName\"='" + searchKey + "'";
            ResultSet resultSet1 = statement.executeQuery(query2);
            ArrayList<String> npName = new ArrayList<String>();
            while (resultSet1.next()) {
                String name = resultSet1.getString("serviceProvider");
                npName.add(name);
            }
            Service service = new Service(searchKey, npName);
            services.add(service);
            Database.disconnect();
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("ServiceList Error");
        }
    }

    @Override
    public String toString() {
        String output="";
        try {
            for (int i = 0; i < services.size(); i++) {
                output += "\n"+(i + 1)+"-" + services.get(i).getServiceName();
                for (int j = 0; j < services.get(i).getSpName().size(); j++) {
                    output += "\n\t" + (j + 1)+"-" + services.get(i).getSpName().get(j);
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("No Services Found");
        }
        return output;
    }
}
