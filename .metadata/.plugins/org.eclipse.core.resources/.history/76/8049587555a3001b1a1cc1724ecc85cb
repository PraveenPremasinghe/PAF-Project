package com.paymentservice;


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
import com.paymentservice.model.Payment;

@Path("/payments")
public class PaymentResouce {

	 Payment payment = new Payment();
	    
	    @GET
	    @Path("/")
	    @Produces(MediaType.APPLICATION_JSON)
	    public String getPayment(@QueryParam("payment_id") String payment_id) {
	    	if(payment_id == null) {
	    	
	    	
	        return payment.getPayment().toString();
	    	}
	    	else
	    	{
	    		 return payment.getSinglePayment(Integer.parseInt(payment_id)).toString();
	    	}
	    }
	    
	    
	    @POST
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String insertPayment(String paymentJsonString) {
	        JsonObject paymentJson =new  JsonParser().parse(paymentJsonString).getAsJsonObject();
	        
	        return (payment.insertPayment(paymentJson.get("customer_id").getAsInt(), paymentJson.get("payment_date").getAsString(), paymentJson.get("payment_amount").getAsDouble(), paymentJson.get("tax_rate").getAsDouble(), paymentJson.get("description").getAsString(), paymentJson.get("card_type").getAsString(), paymentJson.get("card_number").getAsString(), paymentJson.get("card_cvn").getAsString() )).toString();
	    }
	    
	    @PUT
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String updatePayment(String paymentJsonString) {
	        JsonObject paymentJson =new  JsonParser().parse(paymentJsonString).getAsJsonObject();
	        
	        return (payment.updatePayment(paymentJson.get("payment_id").getAsInt(),paymentJson.get("customer_id").getAsInt(), paymentJson.get("payment_date").getAsString(), paymentJson.get("payment_amount").getAsDouble(), paymentJson.get("tax_rate").getAsDouble(), paymentJson.get("description").getAsString(), paymentJson.get("card_type").getAsString(), paymentJson.get("card_number").getAsString(), paymentJson.get("card_cvn").getAsString() )).toString();
	    }
	    
	    
	    @DELETE
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public String deletePayment(String paymentJsonString) {
	        JsonObject paymentJson =new  JsonParser().parse(paymentJsonString).getAsJsonObject();
	        
	        return (payment.deletePayment(paymentJson.get("payment_id").getAsInt())).toString();
	    }
	
}



