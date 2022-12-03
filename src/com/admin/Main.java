package com.admin;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		try (Scanner sc = new Scanner(System.in)) {
			RefundList r = new RefundList();
			RefundResponse refund = new RefundResponse();
			r.getRefundList();
			
			String input = sc.next();
			
			refund.setRefundID(input);
			
			refund.acceptRefund();
		}
		

	}

}
