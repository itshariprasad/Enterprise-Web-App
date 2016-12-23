package beans;

import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotelBeans 
{
	ArrayList<String> roomTypes;									
	HashMap<String, Integer> numberOfRooms;
	ArrayList<RoomHotelBean> hotelrooms;
	ArrayList<String> hotelfeatures;
	ArrayList<String> hotelimages;
	
	String hotelName1;
	String cityName;
	String zipCode;
	String hotelAddress;
	String hotel_id;
	String hotel_url1;
	String hotelcancellationPolicy1;
	String hotelDescription;
	
	int hotelId;
	int hoteltotalRooms;
	
	float rating,hotelMinPrice;
	
	Boolean truee;
	
	public String getHotelName() 
	{
		return hotelName1;
	}
	public void setHotelName(String hotelName) 
	{
		this.hotelName1 = hotelName;
	}
	public String getHotelCity() 
	{
		return cityName;
	}
	public void setHotelCity(String city) 
	{
		this.cityName = city;
	}
	public String getZipCode() 
	{
		return zipCode;
	}
	public void setZipCode(String zip) 
	{
		this.zipCode = zip;
	}
	public String getHotelAddress() 
	{
		return hotelAddress;
	}
	public void setHotelAddress(String address) {
		this.hotelAddress = address;
	}
	public String get_id() 
	{
		return hotel_id;
	}
	
	public void set_id(String _id) 
	{
		this.hotel_id = _id;
	}
	public String getUrl() 
	{
		return hotel_url1;
	}
	public void setUrl(String url) 
	{
		this.hotel_url1 = url;
	}
	public String getCancellationPolicy() 
	{
		return hotelcancellationPolicy1;
	}
	public void setCancellationPolicy(String cancellationPolicy) 
	{
		this.hotelcancellationPolicy1 = cancellationPolicy;
	}
	public ArrayList<String> getRoomTypes() 
	{
		return roomTypes;
	}
	public void setHotelRoomTypes(ArrayList<String> roomTypes) 
	{
		this.roomTypes = roomTypes;
	}
	public HashMap<String, Integer> getNumberOfRooms() 
	{
		return numberOfRooms;
	}
	public void setHotelNumberOfRooms(HashMap<String, Integer> numberOfRooms) 
	{
		this.numberOfRooms = numberOfRooms;
	}
	public ArrayList<String> getFeatures() 
	{
		return hotelfeatures;
	}
	public void setFeatures(ArrayList<String> features) 
	{
		this.hotelfeatures = features;
	}
	public int getTotalRooms() 
	{
		return hoteltotalRooms;
	}
	public void setHotelTotalRooms(int totalRooms) 
	{
		this.hoteltotalRooms = totalRooms;
	}
	public ArrayList<RoomHotelBean> getHotelRooms() 
	{
		return hotelrooms;
	}
	public void setHotelRooms(ArrayList<RoomHotelBean> rooms) 
	{
		this.hotelrooms = rooms;
	}
	public int getHotelId()
	{
		return hotelId;
	}
	public void setHotelId(int hotelId) 
	{
		this.hotelId = hotelId;
	}
	public float getRating() 
	{
		return rating;
	}
	public void setHotelRating(float rating) 
	{
		this.rating = rating;
	}
	public String getDescription() 
	{
		return hotelDescription;
	}
	public void setHotelDetails(String description) 
	{
		this.hotelDescription = description;
	}
	public float getMinPrice() 
	{
		return hotelMinPrice;
	}
	public void setHotelMinPrice(float minPrice) 
	{
		this.hotelMinPrice = minPrice;
	}
	public ArrayList<String> getImages() 
	{
		return hotelimages;
	}
	public void setHotelImages(ArrayList<String> images) 
	{
		this.hotelimages = images;
	}
	public Boolean getValid() 
	{
		return truee;
	}
	public void setTrue(Boolean valid) 
	{
		this.truee = valid;
	}
}
