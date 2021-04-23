package com.order.login;

import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.order.model.Customer;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;


public class LoginFilter implements ContainerRequestFilter{
	
	
	private static final String Header = "Authorization";
	private static final String Prefix = "Basic";
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		
		List<String> AuthHeader = request.getRequestHeader(Header);
		if (AuthHeader != null && AuthHeader.size() > 0) {
			String token = AuthHeader.get(0);
			token = token.replaceFirst(Prefix, "");
			
			String decToken = Base64.base64Decode(token);
			System.out.println("dec token " + decToken);
			
			if(!decToken.contains(":")) {
				String error = "Token invalid";
				ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED).entity(error);
				throw new WebApplicationException(builder.build());
			}
			
			String[] credentials = decToken.split(":");
			
			if(credentials.length < 1) {
				String error = "Token invalid";
				ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED).entity(error);
				throw new WebApplicationException(builder.build());
			}
			
			
			String username = credentials[0];
			String password = credentials[1];
			
			System.out.println("un:"+username+" pw:"+password);
			
			Customer customer = new Customer();
			if (customer.validateCustomer(username,password)) {
				
				return request;
			}
			
			String error = "username passsword is invalid";
			ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED).entity(error);
			throw new WebApplicationException(builder.build());
			
			
		}
		
		
		String error = "Header invalid";
		ResponseBuilder builder = Response.status(Response.Status.UNAUTHORIZED).entity(error);
		throw new WebApplicationException(builder.build());
	}

}
