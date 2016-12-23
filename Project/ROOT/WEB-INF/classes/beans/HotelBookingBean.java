package beans;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HotelBookingBean {

	ArrayList<Integer> hotelRoomId;
	
	String startDate;
	String endDate;
	String hotelEmailId;
	
	String hotelBookingName;
	String hotelBookingDate;
	
	long hotelId;
	int hotelBookingId;

	
	HotelBeans hotel;
	String _id;
	
	public String getEmailId() {
		return hotelEmailId;
	}
	public void setEmailId(String hotelEmailId) {
		this.hotelEmailId = hotelEmailId;
	}
	public long getHotelId() {
		return hotelId;
	}
	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}
	public ArrayList<Integer> getRoomId() {
		return hotelRoomId;
	}
	public void setRoomId(ArrayList<Integer> hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}
	public int getBookingId() {
		return hotelBookingId;
	}
	public void setBookingId(int bookingId) {
		this.hotelBookingId = bookingId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBookingName() {
		return hotelBookingName;
	}
	public void setBookingName(String bookingName) {
		this.hotelBookingName = bookingName;
	}
	public String getBookingDate() {
		return hotelBookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.hotelBookingDate = bookingDate;
	}
	public HotelBeans getHotel() {
		return hotel;
	}
	public void setHotel(HotelBeans hotel) {
		this.hotel = hotel;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
}
