package beans.HotelDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class HotelDetails
{
	
	public HotelDetails(){
		
	}
	
	
	String hotel_place_id;
	
	String hotelDetails;
	String hotelId;
	String hotel_reference;
	String hotel_room_types;
	
	HotelConditions terms;
	CompareString compare;
	
	public String getHotelDescription() 
	{
		return hotelDetails;
	}
	public void setHotelRoomTypes(String types) {
		this.hotel_room_types = types;
	}
	public CompareString getHotelCompare_substrings() {
		return compare;
	}
	public void setCompare_substrings(CompareString matched_substrings) {
		this.compare = matched_substrings;
	}
	public HotelConditions getHotelsTerms() {
		return terms;
	}
	public void setHotelDescription(String description) 
	{
		this.hotelDetails = description;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String id) {
		this.hotelId = id;
	}
	public String getHotel_id() {
		return hotel_place_id;
	}
	public void setHotel_id(String place_id) {
		this.hotel_place_id = place_id;
	}
	public String getHotelReference() {
		return hotel_reference;
	}
	public void setHotelReference(String reference) {
		this.hotel_reference = reference;
	}
	public String getHotelRoomsTypes() {
		return hotel_room_types;
	}
	public void setHotelsTerms(HotelConditions terms) {
		this.terms = terms;
	}
	
}
