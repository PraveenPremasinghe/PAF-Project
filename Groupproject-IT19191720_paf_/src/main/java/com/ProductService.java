package com;

	import model.Product;

	//For REST Service
	import javax.ws.rs.*; 
	import javax.ws.rs.core.MediaType; 
	//For JSON
	import com.google.gson.*; 
	//For XML
	import org.jsoup.*; 
	import org.jsoup.parser.*; 
	import org.jsoup.nodes.Document; 


	@Path("/products")
	public class ProductService {
		
		Product productObj = new Product();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String ReadProduct()
		{
			return productObj.ReadProduct();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String InsertProduct(@FormParam("name") String Productname,
				@FormParam("price") String price,
				@FormParam("description") String description)
			
		{
			String output = productObj.InsertProduct(Productname, price, description);
			return output;
		}
		
		
		
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String UpdateProduct(String productid) {
			//Convert the input string to a JSON object 
			
			JsonObject itemObject = new JsonParser().parse(productid).getAsJsonObject();
			
			//Read the values from the JSON objects
			String id = itemObject.get("productid").getAsString();
			String name = itemObject.get("name").getAsString();
			String price = itemObject.get("price").getAsString();
			String description = itemObject.get("description").getAsString();
			
			String output = productObj.UpdateProduct(id, name, price, description);
			return output;
		}
		
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String DeleteProduct(String productdata) {
			
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(productdata, "", Parser.xmlParser());
			
			//Read the value from the element <itemID>
			String id = doc.select("id").text();
			
			String output = productObj.DeleteProduct(id);
			
			return output;
		}
		
		
	}
