package com.user;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException  {
		
		
		
		System.out.println("============Welcome============");
		User user=new User("joe","1234","y.mohamed.fcai@gmail.com","yousef",0);
		SignUp signUp=new SignUp(user);
		Login login=new Login("joe","1234");
		if (login.getUser()==null){
			System.out.println("UserName or Password wrong!");
		}
		else {
			System.out.println("Hello "+login.getUser().getName());
		}
		
		Logout logout= new Logout(login);
		
	//	RefundRequest r = new RefundRequest();
		//r.makeRefundRequest(5345);
	}

}
