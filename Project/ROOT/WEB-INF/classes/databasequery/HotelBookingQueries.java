package databasequery;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import beans.HotelBookingBean;

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

public class HotelBookingQueries 
{
	MongoClient mongo = new MongoClient();
	//create MongoDb Object
	MongoDatabase databases = mongo.getDatabase("CSP595Tutorial");  //MongoDB database
	MongoCollection<Document> databasecollections = databases.getCollection("ScarletHotels");	
	//Database Collections
	ObjectMapper mapper1 = new ObjectMapper();
	ArrayList<HotelBookingBean> bookings = new ArrayList<>();
	
	
	//Insert Customer Booking
	public void hotelDeleteBooking(int bookinId)
	{
		databasecollections.deleteOne(new Document("hotelBookingId", bookinId));
	}
	public void insertCustomerBooking(HotelBookingBean Hotelbooking) throws JsonProcessingException
	{
		ObjectWriter objectwriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectwriter.writeValueAsString(Hotelbooking);
		databasecollections.insertOne(Document.parse(json));
	}
	public ArrayList<HotelBookingBean> getBookingDetails(String email)
	{
		ArrayList<Document> datafilters = new ArrayList<Document>();
		//to create ArrayList Document
		datafilters.add(new Document("$match",new Document("emailId",email)));	
		//Matching User Email-is to get Data
		AggregateIterable<Document> cursor;
		cursor = databasecollections.aggregate(datafilters);
		ObjectMapper mapper1;
		mapper1 = new ObjectMapper();
		// New Array List of Hotelbookings
		ArrayList<HotelBookingBean> hotelbookings;
		hotelbookings = new ArrayList<>();
		
		//for each block
		cursor.forEach(new Block<Document>() 
		{
		    
		    public void apply(final Document document) 
		    {
		        try {
		        		HotelBookingBean booking;
		        		booking = new HotelBookingBean();
		        		booking = mapper1.readValue(document.toJson(), HotelBookingBean.class);
		        		hotelbookings.add(booking);
					}
		        	catch (Exception e) 
		        	{
		        		e.printStackTrace();
		        	}
		    }
		});
		return hotelbookings;
	}
	
	public HotelBookingBean getCustomerBooking(int bookingId) throws JsonParseException, JsonMappingException, IOException
	{
		Document newHotelDoc = databasecollections.find(eq("hotelBookingId", bookingId)).first();
		ObjectMapper objectmapper = new ObjectMapper();
		//Define new objectMapper
		return objectmapper.readValue(newHotelDoc.toJson(), HotelBookingBean.class);
		//Get the hotel data
	}
	
	public ArrayList<HotelBookingBean> getBookingDetails(){
		ArrayList<Document> datafilters;
		datafilters = new ArrayList<Document>();	//create ArrayList Document
		datafilters.add(new Document("$match",new Document("hotelBookingId",new Document("$gt",0))));	//Match with HotelId
		AggregateIterable<Document> cursor = databasecollections.aggregate(datafilters);
		cursor.forEach(new Block<Document>() 
		{
		    public void apply(final Document documents) 
		    {
		        try 
		        {
		      
		        	HotelBookingBean booking;
		        	booking = new HotelBookingBean();booking = mapper1.readValue(documents.toJson(), HotelBookingBean.class);
		        	bookings.add(booking);
				} 
		        catch (Exception e) 
		        {
					e.printStackTrace();
				}
		    }
		});
		return bookings;
	}
	
	
}
