package com.order;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.order.model.Order;

@Path("/orders")
public class OrderResource {
	 Order order = new Order();
	    
	    @GET
	    @Path("/")
	    @Produces(MediaType.APPLICATION_JSON)
	    public String getOrder(@QueryParam("order_id") String order_id) {
	    	if(order_id == null) {
	    	
	    	
	        return order.getOrder().toString();
	    	}
	    	else
	    	{
	    		 return order.getSingleOrder(Integer.parseInt(order_id)).toString();
	    	}
	    }
	    
	    
	    @POST
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String insertOrder(String orderJsonString) {
	        JsonObject orderJson =new  JsonParser().parse(orderJsonString).getAsJsonObject();
	        
	        return (order.insertOrder(orderJson.get("product_id").getAsInt(), orderJson.get("items").getAsInt(), orderJson.get("item_price").getAsDouble(), orderJson.get("discount").getAsDouble(), orderJson.get("customerid").getAsInt() )).toString();
	    }
	    
	    @PUT
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String updateOrder(String orderJsonString) {
	        JsonObject orderJson =new  JsonParser().parse(orderJsonString).getAsJsonObject();
	        
	        return (order.updateOrder(orderJson.get("order_id").getAsInt(),orderJson.get("product_id").getAsInt(), orderJson.get("items").getAsInt(), orderJson.get("item_price").getAsDouble(), orderJson.get("discount").getAsDouble(), orderJson.get("customerid").getAsInt() )).toString();
	    }
	    
	    
	    @DELETE
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String deleteOrder(@QueryParam("order_id") String order_id) {
	        
	        
	        return (order.deleteOrder(Integer.parseInt(order_id))).toString();
	    }
}
