package databasequery;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import beans.HotelBeans;
import beans.ResultHotelBean;
import beans.RoomHotelBean;
import beans.RoomResultBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class HotelsDatabaseQuery 
{
	MongoClient mongo = new MongoClient();
	//create MongoDb Object
	MongoDatabase databases = mongo.getDatabase("CSP595Tutorial");  //MongoDB database
	MongoCollection<Document> databasecollections = databases.getCollection("ScarletHotels");	
	ArrayList<RoomHotelBean> rooms = new ArrayList<>();
	ArrayList<HashMap<String, Long>> dates = new ArrayList<HashMap<String,Long>>();
	ArrayList<HotelBeans> hotels = new ArrayList<>();
	RoomHotelBean room = new RoomHotelBean();
	ObjectMapper mapper = new ObjectMapper();
	HashMap<String, Integer> roomTypes = new HashMap<>();
	int count =0;
	String roomIds = "";
	int totalRooms = 0;
	//Get collection of ScarletHotels
	
	public int getCount()
	{
		return (int) databasecollections.count();		//Get count of hotels
	}
	
	public void insertCustomerOrder(HotelBeans hotels) throws JsonProcessingException	//insertCustomerOrder
	{
		ObjectWriter objectwriter = new ObjectMapper().writer().withDefaultPrettyPrinter();			
		String json = objectwriter.writeValueAsString(hotels);		
		databasecollections.insertOne(Document.parse(json));
	}
	
	public void deleteFromList(long hotelId,int roomId)
	{
		databasecollections.updateOne(new Document("hotelId",hotelId), new Document("$pull", new Document("rooms",new Document("roomId",roomId))));
	}
	//to remove data of hotel from hotelId
	
	public ArrayList<HotelBeans> getdatadetails()				//function to get details of data
	{
		ArrayList<HotelBeans> hotels = new ArrayList<>();
		//ArrayList of Hotel
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Document> datafilters = new ArrayList<Document>();
		//create a new ArrayList Document
		datafilters.add(new Document("$match",new Document("hotelId",new Document("$gt",0))));
		//to match ID of hotel to cover data
		
		AggregateIterable<Document> cursor = databasecollections.aggregate(datafilters);
		cursor.forEach(new Block<Document>() 
		{
		    //function apply
			public void apply(final Document document) 
		    {
		        try 
		        {
		        	HotelBeans hotelbeans = new HotelBeans();
		        	hotelbeans = mapper.readValue(document.toJson(), HotelBeans.class);
		        	hotels.add(hotelbeans);
		        	//Add hotels
				} 
		        catch (Exception e) 
		        {
					e.printStackTrace();
					//catch an error
				}
		    }
		});
		return hotels;
		//return values
	}
	
	public void updateRoom(long hotelId,String room)
	{
		databasecollections.updateOne(new Document("hotelId",hotelId),
				new Document("$push",new Document("rooms",Document.parse(room))));
	}
	
	public void deleteHotel(int hotelId)
	{
		databasecollections.deleteOne(new Document("hotelId",hotelId));
	}

	
	public ArrayList<HotelBeans> findHotelData (Long startDate,Long endDate,String cityName)
	{
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Document> datafilters = new ArrayList<Document>();							//Document of data filters
		datafilters.add(new Document("$unwind","$rooms"));
		datafilters.add(new Document("$unwind","$rooms.dates"));
		datafilters.add(new Document("$match",new Document("cityName",cityName)));
		datafilters.add(new Document("$match",new Document("rooms.dates.startDate",new Document("$lte",startDate)).append("rooms.dates.endDate", new Document("$gte",endDate)).append("rooms.dates.status", 1)));
		datafilters.add(new Document("$group",new Document("hotel_id","$hotelId")
		.append("hotelId", new Document("$push","$hotelId"))
		.append("hotelName",new Document("$push","$hotelName"))
		.append("cityName",new Document("$push","$cityName"))
		.append("zipCode",new Document("$push","$zipCode"))
		.append("hotelAddress",new Document("$push","$hotelAddress"))
		.append("hotel_url",new Document("$push","$hotel_url"))
		.append("hotelcancellationPolicy",new Document("$push","$hotelcancellationPolicy"))
		.append("roomTypes",new Document("$push","$roomTypes"))
		.append("numberOfRooms",new Document("$push","$numberOfRooms"))
		.append("rooms",new Document("$push","$rooms"))
		.append("features",new Document("$push","$features"))
		.append("totalRooms",new Document("$push","$totalRooms"))
		.append("rating",new Document("$push","$rating"))
		.append("hotelDescription", new Document("$push","$hotelDescription"))
		.append("images", new Document("$push","$images"))
		.append("minPrice", new Document("$push","$minPrice"))));
		ArrayList<ResultHotelBean> finalhotelsResult = new ArrayList<ResultHotelBean>();
	
		AggregateIterable<Document> cursordata = databasecollections.aggregate(datafilters);
		cursordata.forEach(new Block<Document>() 
		{
		    public void apply(final Document document) 
		    {
		        try 
		        {
		        	ResultHotelBean hotel = new ResultHotelBean();
		        	hotel = mapper.readValue(document.toJson(), ResultHotelBean.class);
		        	finalhotelsResult.add(hotel);
				} 
		        catch (Exception e) 
		        {
					e.printStackTrace();}
		    	}
			});
			
			
			for(ResultHotelBean hotelResult : finalhotelsResult)
			{
				
				if(finalhotelsResult.size() > 0)			//if any Room size available
				{
					HotelBeans hotel = new HotelBeans();
					hotel.setHotelAddress(hotelResult.getAddress().get(0));
					hotel.setHotelCity(hotelResult.getCity().get(0));
					hotel.setFeatures(hotelResult.getFeatures().get(0));
					hotel.setHotelDetails(hotelResult.getDescription().get(0));
					hotel.setHotelId(hotelResult.getHotelId().get(0));
					hotel.setHotelMinPrice(hotelResult.getMinPrice().get(0));
					hotel.setHotelRating(hotelResult.getRating().get(0));
					hotel.setHotelName(hotelResult.getHotelName().get(0));
					hotel.setHotelImages(hotelResult.getImages().get(0));
					
				for(RoomResultBean resultRoom : hotelResult.getRooms())
				{
					roomIds = roomIds+resultRoom.getRoomId()+"-";
					totalRooms++;
					
					if(roomTypes.get(resultRoom.getTypeName()) == null)				//if room is not available
					{
						roomTypes.put(resultRoom.getTypeName(), 1);
					}
					else
					{
						roomTypes.put(resultRoom.getTypeName(), roomTypes.get(resultRoom.getTypeName())+1);
					}
					
					for(RoomHotelBean local : rooms)
					{
						if(local.getRoomTypeName().equals(resultRoom.getTypeName()))
						{
							count++;					//Make a count 
						}
					}
					if(count ==0)
					{
						RoomHotelBean room = new RoomHotelBean();
						room.setHotelCity(resultRoom.getCity());
						room.setHotelAddress(resultRoom.getAddress());
						
						//Make room available by the available between start date and old date
						dates.add(resultRoom.getDates());
						
						room.setFeatures(resultRoom.getFeatures());
						room.setDates(dates); room.setImages(resultRoom.getImages());
						room.setHotelName(resultRoom.getHotelName());
						room.setRoomTypeName(resultRoom.getTypeName());
						room.setHotelRoomId(resultRoom.getRoomId());
						room.setHotelPrice(resultRoom.getPrice());
						room.setZipcode(resultRoom.getZip());
						rooms.add(room);
					}
				}
				hotel.setCancellationPolicy(roomIds);
				hotel.setHotelNumberOfRooms(roomTypes);
				hotel.setHotelRoomTypes(hotelResult.getRoomTypes().get(0));
				hotel.setHotelRooms(rooms);
				hotel.setUrl(hotelResult.getUrl().get(0));
				hotel.setHotelTotalRooms(totalRooms);
				hotel.setZipCode(hotelResult.getZip().get(0));
				hotels.add(hotel);
			}
			
		}
		return hotels;
	}
	
	public RoomHotelBean getRoom(long hotelId,int roomId)
	{
		ArrayList<Document> datafilters = new ArrayList<Document>();
		datafilters.add(new Document("$match",new Document("hotelId",hotelId)));
		datafilters.add(new Document("$unwind","$rooms"));
		datafilters.add(new Document("$match",new Document("rooms.roomId",roomId)));
		datafilters.add(new Document("$project",new Document("rooms",1).append("hotel_id", 0)));
		ObjectMapper mapper1 = new ObjectMapper();
		AggregateIterable<Document> cursor = databasecollections.aggregate(datafilters);
		cursor.forEach(new Block<Document>() 
		{
		    public void apply(final Document apply) 
		    {
		        try 
		        {
		        	room = mapper1.readValue(apply.toJson().substring(11,apply.toJson().length()-1), RoomHotelBean.class);
				} 
		        catch (Exception e) 
		        {
					e.printStackTrace();
				}
		    }
		});
		return room;
	}
	
	}
