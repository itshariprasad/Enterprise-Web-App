package databasequery;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import beans.CustomerBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class UserDao 
{
	MongoClient mongo = new MongoClient();
	//create MongoDb Object
	MongoDatabase databases = mongo.getDatabase("CSP595Tutorial");  //MongoDB database
	MongoCollection<Document> databasecollections = databases.getCollection("ScarletHotels");
	CustomerBean User;
	ArrayList<Document> dataFilters = new ArrayList<Document>();
	
	public boolean findScarletUser(String emailId)
	{
		dataFilters.add(new Document("$match",new Document("emailId",emailId)));
		AggregateIterable<Document> cursorparser = databasecollections.aggregate(dataFilters);
		cursorparser.forEach(new Block<Document>() {
		public void apply(final Document document) {
		        try {
		        	if(document.size() > 0)
		        		status =  false;
		        	else
		        		status = true;
				} 
		        catch (Exception e) 
		        {
					e.printStackTrace();
				}
		    }
		});
		return status;
	}
	
	public void insertScarletUser(CustomerBean user) throws JsonProcessingException, IOException
	{
		ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectWriter.writeValueAsString(user);
		databasecollections.insertOne(Document.parse(json));
	}
	
	public Boolean status = true;
	
	
	
	public boolean checkScarletUser(String emailId,String password,String type)
	{
		Document myDoc = databasecollections.find(eq("emailId", emailId)).first();
		if(myDoc != null)
		if(password.equals(myDoc.get("password")) && type.equals(myDoc.get("type")))
				status = true;
			else
				status = false;
		else
			status = false;
		return status;
	}
	
	public CustomerBean getUser(String emailId) throws JsonParseException, JsonMappingException, IOException
	{
		Document myDoc = databasecollections.find(eq("emailId", emailId)).first();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(myDoc.toJson(), CustomerBean.class);
	}
	
}
