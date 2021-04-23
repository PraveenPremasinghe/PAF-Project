package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Order extends DBConnector{
	//Read Order
	public JsonObject getOrder() {
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
			String query = " select * from order_table";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject order_table_row = new JsonObject();

				order_table_row.addProperty("order_id", rs.getInt("order_id") );
				order_table_row.addProperty("product_id", rs.getInt("product_id") );
				order_table_row.addProperty("items", rs.getString("items") );
				order_table_row.addProperty("item_price", rs.getDouble("item_price") );
				order_table_row.addProperty("discount", rs.getDouble("discount") );
				order_table_row.addProperty("customerid", rs.getString("customerid") );
				resultArray.add(order_table_row);
			}
			result = new JsonObject();
			result.add("order", resultArray);

			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading order");
			System.err.println(e.getMessage());
		}
		return result;
	}



	public JsonObject insertOrder( int product_id , int items, double item_price,double discount,int customerid )
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
			String query = " insert into order_table"+
					"(`product_id`,`items`,`item_price`,`discount`,`customerid`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, product_id);
			preparedStmt.setInt(2, items);
			preparedStmt.setDouble(3, item_price);
			preparedStmt.setDouble(4, discount);
			preparedStmt.setInt(5,customerid);
			
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

	//update order
	public JsonObject updateOrder( int order_id, int product_id , int items, double item_price,double discount,int customerid )
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
			String query = " update order_table set `product_id`= ? ,`items` = ?,`item_price` = ?,`discount` = ?,`customerid` = ? where `order_id` = ?";
PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, product_id);
			preparedStmt.setInt(2, items);
			preparedStmt.setDouble(3, item_price);
			preparedStmt.setDouble(4, discount);
			preparedStmt.setInt(5,customerid);
			preparedStmt.setInt(6,order_id);
			
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

	//delete order
	public JsonObject deleteOrder( int order_id )
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
			String query = "delete from order_table where order_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);


			// binding values
			preparedStmt.setInt(1, order_id);

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
	public JsonObject getSingleOrder(int order_id) {
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
			String query = " select * from order_table where order_id=?";


			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, order_id);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray resultArray = new JsonArray();
			while(rs.next()) {
				JsonObject order_table_row = new JsonObject();
				order_table_row.addProperty("order_id", rs.getInt("order_id") );
				order_table_row.addProperty("product_id", rs.getInt("product_id") );
				order_table_row.addProperty("items", rs.getString("items") );
				order_table_row.addProperty("item_price", rs.getDouble("item_price") );
				order_table_row.addProperty("discount", rs.getDouble("discount") );
				order_table_row.addProperty("customerid", rs.getString("customerid") );
				resultArray.add(order_table_row);
				
			}
			result = new JsonObject();
			result.add("order", resultArray);

			con.close();
		}
		catch (Exception e)
		{
			result = new JsonObject();
			result.addProperty("EXCEPTION", "Error occured while reading Order");
			System.err.println(e.getMessage());
		}
		return result;
	}
}
