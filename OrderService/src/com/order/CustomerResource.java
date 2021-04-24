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
import com.order.model.Customer;

@Path("/customers")

public class CustomerResource {
	

	Customer customer = new Customer();
	    
	    @GET
	    @Path("/")
	    @Produces(MediaType.APPLICATION_JSON)
	    public String getCustomer(@QueryParam("userid") String userid) 
	    { 	
	    	System.out.println("hello");
	    	if(userid == null) {
	    	
	    	
	        return customer.getCustomer().toString();
	    	}
	    	else
	    	{
	    		 return customer.getSingleCustomer(Integer.parseInt(userid)).toString();
	    	}
	    }
	    
	    
	    @POST
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String insertCustomer(String customerJsonString) {
	        JsonObject customerJson =new  JsonParser().parse(customerJsonString).getAsJsonObject();
	        
	        return (customer.insertCustomer(customerJson.get("username").getAsString(), customerJson.get("password").getAsString(), customerJson.get("email").getAsString(), customerJson.get("phone").getAsInt())).toString();
	    }
	    
	    @PUT
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String updateCustomer(String customerJsonString) {
	        JsonObject customerJson =new  JsonParser().parse(customerJsonString).getAsJsonObject();
	        
	        return (customer.updateCustomer( customerJson.get("userid").getAsInt() ,customerJson.get("username").getAsString(), customerJson.get("password").getAsString(), customerJson.get("email").getAsString(), customerJson.get("phone").getAsInt()  )).toString();
	       
	    }
	    
	    
	    @DELETE
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String deleteCustomer(@QueryParam("userid") String userid) {
	        
	        
	        return (customer.deleteCustomer(Integer.parseInt(userid))).toString();
	        
	    }

}
