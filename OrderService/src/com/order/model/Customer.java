package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	
	
	///////////////////////////////////////Customer logim&signup curd////////////////////////////////////////////////


	//Read Customer details
	public JsonObject getCustomer() {
		JsonObject result = null;
		try
		{
			Connection con = connect();
			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for reading."); 
			}
			// create a prepared statement
			String query = " select * from customer_table";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject customer_table_row = new JsonObject();

				customer_table_row.addProperty("userid", rs.getInt("userid") );
				customer_table_row.addProperty("username", rs.getString("username") );
				customer_table_row.addProperty("password", rs.getString("password") );
				customer_table_row.addProperty("email", rs.getString("email") );
				customer_table_row.addProperty("phone", rs.getInt("phone") );

				resultArray.add(customer_table_row);
			}
			result = new JsonObject();
			result.add("customer", resultArray);

			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading customer");
			System.err.println(e.getMessage());
		}
		return result;
	}



	public JsonObject insertCustomer( String username, String password,String email,int phone )
	{
		JsonObject result = null;
		try
		{
			Connection con = connect();
			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for inserting.");
			}
			// create a prepared statement
			String query = " insert into customer_table"+
					"(`username`,`password`,`email`,`phone`,)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, email);
			preparedStmt.setInt(4, phone);

			
			// execute the statement
			preparedStmt.execute();
			con.close();

			result = new JsonObject();
			result.addProperty("SUCCESSFUL", "Inserted successfully");
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while inserting the item.");
			System.err.println(e.getMessage());
		}
		return result;
	} 

	//update customer
	public JsonObject updateCustomer( int userid ,String username, String password,String email,int phone)
	{
		JsonObject result = null;
		try
		{
			Connection con = connect();
			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for updating.");
			}
			// create a prepared statement
			String query = " update customer_table set `username`= ? ,`password` = ?,`email` = ?,`phone` = ? where `userid` = ?";
PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, email);
			preparedStmt.setInt(4, phone);
			preparedStmt.setInt(5, userid);
			
			// execute the statement
			preparedStmt.execute();
			

			int status =  preparedStmt.executeUpdate();

			con.close();

			result = new JsonObject();

			if(status > 0 ) {


				result.addProperty("SUCCESSFUL", "Updated Successfully");

			}
			else {
				result.addProperty("UNSUCCESSFUL", "Updating Failed");	
			}

		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while updating the item.");
			System.err.println(e.getMessage());
		}
		return result;
	} 

	//delete customer
	public JsonObject deleteCustomer( int userid )
	{
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for deleting.");
			}


			// create a prepared statement
			String query = "delete from customer_table where userid=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);


			// binding values
			preparedStmt.setInt(1, userid);

			// execute the statement
			int status =  preparedStmt.executeUpdate();

			con.close();

			result = new JsonObject();

			if(status > 0 ) {


				result.addProperty("SUCCESSFUL", "Deleted Successfully");

			}
			else {
				result.addProperty("UNSUCCESSFUL", "Deletion Failed");	
			}


		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION","Error while deleting the item.");
			System.err.println(e.getMessage());
		}
		return result;
	} 


	//get single record
	public JsonObject getSingleCustomer(int userid) {
		JsonObject result = null;
		try
		{
			Connection con = connect();

			if (con == null)
			{
				result = new JsonObject();
				result.addProperty("ERROR", "Error while connecting to the database for reading single item."); 
			}


			// create a prepared statement
			String query = " select * from customer_table where userid=?";


			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, userid);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject customer_table_row = new JsonObject();
				
				customer_table_row.addProperty("userid", rs.getInt("userid") );
				customer_table_row.addProperty("username", rs.getString("username") );
				customer_table_row.addProperty("password", rs.getString("password") );
				customer_table_row.addProperty("email", rs.getString("email") );
				customer_table_row.addProperty("phone", rs.getInt("phone") );
			
				resultArray.add(customer_table_row);
				
			}
			result = new JsonObject();
			result.add("customer", resultArray);

			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading Customer");
			System.err.println(e.getMessage());
		}
		return result;
	}

///////////////////////////////////////Customer logim&signup curd////////////////////////////////////////////////

	
}
