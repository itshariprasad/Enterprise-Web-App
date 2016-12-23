package databasequery;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;

import org.bson.Document;

import beans.HotelBookingBean;
import beans.HotelBookingBean;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HotelBookingQuery {

	MongoClient mongo = new MongoClient();
	//create MongoDb Object
	MongoDatabase databases = mongo.getDatabase("CSP595Tutorial");  //MongoDB database
	MongoCollection<Document> databasecollections = databases.getCollection("ScarletHotels");	
	//Database Collections
	
	
	public ArrayList<HotelBookingBean> getBookingDetails(String email)
	{
		ArrayList<Document> datafilters = new ArrayList<Document>();
		datafilters.add(new Document("$match",new Document("emailId",email)));			//Match Email ID
		ArrayList<HotelBookingBean> hotelBookings = new ArrayList<>();
		AggregateIterable<Document> cursor1 = databasecollections.aggregate(datafilters);
		cursor1.forEach(new Block<Document>() 
		{
		    public void apply(final Document document) 
		    {
		        try 
		        {
		        	HotelBookingBean booking = new HotelBookingBean();
		        	ObjectMapper mapper1 = new ObjectMapper();
		        	booking = mapper1.readValue(document.toJson(), HotelBookingBean.class);
		        	hotelBookings.add(booking);
				} 
		        catch (Exception e) 
		        {
					e.printStackTrace();
				}
		    }
		});
		return hotelBookings;
	}
	public void insertHotelBooking(HotelBookingBean booking) throws JsonProcessingException
	{
		ObjectWriter objectwriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = objectwriter.writeValueAsString(booking);
		databasecollections.insertOne(Document.parse(json));
	}
	public HotelBookingBean getBooking(int bookingId) throws JsonParseException, JsonMappingException, IOException
	{
		Document myDoc = databasecollections.find(eq("bookingId", bookingId)).first();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(myDoc.toJson(), HotelBookingBean.class);
	}
	
	public ArrayList<HotelBookingBean> getHotelBookingDetails()
	{
		ArrayList<Document> datafilters = new ArrayList<Document>();
		datafilters.add(new Document("$match",new Document("bookingId",new Document("$gt",0))));
		ArrayList<HotelBookingBean> bookings = new ArrayList<>();
		AggregateIterable<Document> datacursor = databasecollections.aggregate(datafilters);
		datacursor.forEach(new Block<Document>() 
		{
		    public void apply(final Document document) 
		    {
		        try 
		        {
		        	HotelBookingBean booking = new HotelBookingBean();
		        	ObjectMapper mapper = new ObjectMapper();
		        	booking = mapper.readValue(document.toJson(), HotelBookingBean.class);
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
	
	public void deleteBooking(int bookinId)
	{
		databasecollections.deleteOne(new Document("bookingId", bookinId));
	}
	public static void main(String[] args) {

	}
}
