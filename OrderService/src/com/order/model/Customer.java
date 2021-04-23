package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customer extends DBConnector {

	public boolean validateCustomer(String username, String password) {
		try
		{
			Connection con = connect();
			if (con == null)
			{
				System.out.println("connection error");
				return false;
			}
			// create a prepared statement
			String query = " select * from `customer_table` where `username` = ? AND `password`= ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			
			// execute the statement
			ResultSet rs = preparedStmt.executeQuery();

			String user = null;
			
			while (rs.next()) {
				user = rs.getNString("username");
			}

			con.close();
			System.out.println("un: "+username + " pw:" + password + " user: " +user);
			
			if(user!=null) {
				return true;
			} else {
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("exception: " +e);
			return false;
		}
		
	} 
}
