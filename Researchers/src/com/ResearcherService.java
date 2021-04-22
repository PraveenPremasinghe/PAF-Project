package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import model.Researcher;

@Path("/Researcher")
public class ResearcherService {
	
Researcher ResearcherObj = new Researcher();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return ResearcherObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Name") String researcherName,
	 @FormParam("NIC") String researcherNIC,
	 @FormParam("Address") String researcherAddress,
	 @FormParam("Email") String researcherEmail,
	 @FormParam("PhoneNo") String researcherPhoneNo)

	
	{
	 String output = ResearcherObj.insertItem(researcherName, researcherNIC, researcherAddress, researcherEmail, researcherPhoneNo);
	return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject ResearcherObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String researcherID = ResearcherObject.get("ID").getAsString();
	 String researcherName = ResearcherObject.get("Name").getAsString();
	 String researcherNIC = ResearcherObject.get("NIC").getAsString();
	 String researcherAddress = ResearcherObject.get("Address").getAsString();
	 String researcherEmail = ResearcherObject.get("Email").getAsString();
	 String researcherPhoneNo = ResearcherObject.get("PhoneNo").getAsString();
	
	 
	 String output = ResearcherObj.updateItem(researcherID,researcherName,researcherNIC,researcherAddress,researcherEmail,researcherPhoneNo);
	 return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, " ", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String researcherID = doc.select("ID").text();
	 String output = ResearcherObj.deleteItem(researcherID);
	 return output;
	}

}
