package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Product {

	public Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product-paf", "root", "");
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String InsertProduct( String name, String price, String description)
	{
		
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into products(Product_id,Product_name,Price,Description) values (?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, description);
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String ReadProduct()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
		
			output = "<table border='1' class='table table-striped table-hover'>"
					+ "<tr><th>Product Name</th>" + "<th>Price</th><th>Description</th>"
					+"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String productID = Integer.toString(rs.getInt("Product_id"));
				String productName = rs.getString("Product_name");
				String price = Double.toString(rs.getDouble("Price"));
				String description = rs.getString("Description");
		
				
				// Add a row into the html table
				output += "<tr><td>" + productID + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + description + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger' >"
						+ "<input name='itemID' type='hidden' value='" + productID + "'>"
						+ "</form></td></tr>";
			}
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String DeleteProduct(String product_id)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
				
			// create a prepared statement
			String query = "delete from products where Product_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(product_id));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Deleted successfully";
		}
		
		catch (Exception e)
		{
			output = "Error while deleting the funder.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String UpdateProduct(String productid, String name, String price, String description)
	{
		
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " update products set Product_name=?,Price=? ,Description=? where Product_id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
						
			// binding values			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, price);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(productid));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Update successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
