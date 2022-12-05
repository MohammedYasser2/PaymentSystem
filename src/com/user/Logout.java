package com.user;
public class Logout {
    public Logout(Login login) {
        login.setUser(null);
        System.out.println("Logout Success");
    }
}
