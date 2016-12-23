package controllers;

import helper.EditDates;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.HotelBookingBean;
import beans.HotelBeans;
import beans.RoomHotelBean;
import databasequery.HotelBookingQueries;
import databasequery.HotelsDatabaseQuery;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/cancelBooking")
public class ScarletCancelBooking extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException
	{
		HotelBookingQueries bookingDatabasequry  = new HotelBookingQueries();
		int bookingId = Integer.parseInt(request.getParameter("hotelBookingId"));
		HotelBookingBean booking = bookingDatabasequry.getCustomerBooking(bookingId);
		ArrayList<RoomHotelBean> originalRooms = new ArrayList<>();
		ObjectMapper mapper =new ObjectMapper();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Long start=(long)0;
		Long end=(long)0;
		try{
			Date date = formatter.parse(booking.getStartDate());
			start = date.getTime()/1000;
			date = formatter.parse(booking.getEndDate());
			end = date.getTime()/1000;
		}catch (ParseException e) {
			e.printStackTrace();
		}
		EditDates editDates = new EditDates();
		HotelsDatabaseQuery hoteldatabasequery = new HotelsDatabaseQuery();
		
		for(int i : booking.getRoomId())
		{
			RoomHotelBean room = hoteldatabasequery.getRoom(booking.getHotelId(), i);
			editDates.removeDates(start, end, room.getDates());
			hoteldatabasequery.deleteFromList(booking.getHotelId(),i);
			hoteldatabasequery.updateRoom(booking.getHotelId(), mapper.writeValueAsString(room));
		}
		bookingDatabasequry.hotelDeleteBooking(bookingId);
		response.sendRedirect("/getBookings");
	}

}
