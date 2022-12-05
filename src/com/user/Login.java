package com.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.Database;

public class Login {
    private User user;
    public Login(String userName,String password) {
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
                email=resultSet.getString("email");
                name=resultSet.getString("name");
                wallet=resultSet.getDouble("wallet");
                newUser=new User(userName,password,email,name,wallet);
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return newUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
