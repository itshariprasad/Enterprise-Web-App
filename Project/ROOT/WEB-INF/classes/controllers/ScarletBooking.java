package controllers;

import helper.EditDates;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.HotelBookingBean;
import beans.HotelBeans;
import beans.RoomHotelBean;
import databasequery.HotelBookingQueries;
import databasequery.HotelsDatabaseQuery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/bookRoom")
public class ScarletBooking extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		int hotelId = (int)session.getAttribute("hotelId");
		int roomId = (int)session.getAttribute("roomId");
		int scarletnumOfRooms = (int)session.getAttribute("numOfRooms");
		String checkIn = (String)session.getAttribute("checkInDate");
		String checkOut = (String)session.getAttribute("checkOutDate");
		Long checkInLong = (long)0;
		Long checkOutLong = (long)0;
		
		String hotelRoomName = "";
		HotelsDatabaseQuery hoteldatabasequery = new HotelsDatabaseQuery();
		ArrayList<HotelBeans> hotels = (ArrayList<HotelBeans>) session.getAttribute("hotels");
		HotelBeans customerbookingHotel = new HotelBeans();
		RoomHotelBean requiredRoom = new RoomHotelBean();
		ArrayList<RoomHotelBean> hotelRooms = new ArrayList<>();
		HashMap<Integer, RoomHotelBean> hotelRooms1 = new HashMap<>();
		ArrayList<Integer> hotelroomIDs = new ArrayList<>();
		
		EditDates editDates = new EditDates();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			Date date_tem = formatter.parse(checkIn);
			checkInLong = date_tem.getTime()/1000;
			date_tem = formatter.parse(checkOut);
			checkOutLong = date_tem.getTime()/1000;
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		HotelBeans hotelroom = new HotelBeans();
		for(HotelBeans temp : hotels)
		{
			if(temp.getHotelId() == hotelId)
			{
				String[] hoteltemp = temp.getCancellationPolicy().split("-");
				ArrayList<Integer>  hotelroomId = new ArrayList<>();
				for(String i : hoteltemp)
				{	
					hotelroomId.add(Integer.parseInt(i));
					hotelroom = temp;
				}
				ArrayList<RoomHotelBean> scarletrooms = new ArrayList<>();
				for(int i : hotelroomId)
				{
					RoomHotelBean room = hoteldatabasequery.getRoom(hotelId,i);
					scarletrooms.add(room);
				}
				hotelroom.setHotelRooms(scarletrooms);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<RoomHotelBean> rooms = hotelroom.getHotelRooms();
		for(RoomHotelBean hotelbookingroom : rooms)
		{
			if(hotelbookingroom.getRoomId() == roomId)
			{
				requiredRoom = hotelbookingroom;
				break;
			}
		}
		customerbookingHotel.setHotelTotalRooms(scarletnumOfRooms);
		hotelRoomName = requiredRoom.getRoomTypeName();
		for(RoomHotelBean room : rooms)
		{
			if(room.getRoomTypeName().equals(requiredRoom.getRoomTypeName()) && (scarletnumOfRooms > 0) )
			{
				RoomHotelBean hotelTempRoom = new RoomHotelBean();
				hotelTempRoom.setHotelAddress(room.getAddress());
				hotelTempRoom.setHotelCity(room.getCity());
				hotelTempRoom.setDates(editDates.addDates(checkInLong, checkOutLong,
						room.getDates()));
				hotelTempRoom.setFeatures(room.getFeatures());
				hotelTempRoom.setHotelName(room.getHotelName());
				hotelTempRoom.setImages(room.getImages());
				hotelTempRoom.setHotelPrice(room.getPrice());
				hotelTempRoom.setHotelRoomId(room.getRoomId());
				hotelTempRoom.setRoomTypeName(room.getRoomTypeName());
				hotelTempRoom.setZipcode(room.getZip());
				scarletnumOfRooms--;
				hotelRooms1.put(hotelTempRoom.getRoomId(),hotelTempRoom);
				HashMap<String, Long> datesHash = new HashMap<String, Long>();
				ArrayList<HashMap<String, Long>> dates = new ArrayList<HashMap<String,Long>>();
				try {
					Date date_tem = formatter.parse(checkIn);
					datesHash.put("startDate", date_tem.getTime()/1000);
					date_tem = formatter.parse(checkOut);
					datesHash.put("endDate", date_tem.getTime()/1000);
					datesHash.put("status", (long) 0);
					dates.add(datesHash);
					room.setDates(dates);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				hotelroomIDs.add(room.getRoomId());
				hotelRooms.add(room);
			}
		}
		customerbookingHotel = hotelroom;
		customerbookingHotel.setHotelRooms(hotelRooms);

		
		for (Map.Entry<Integer, RoomHotelBean> entry : hotelRooms1.entrySet()) 
		{
			hoteldatabasequery.deleteFromList(hotelId, entry.getKey());
			hoteldatabasequery.updateRoom(hotelId, mapper.writeValueAsString(entry.getValue()));
		}
		HotelBookingBean booking = new HotelBookingBean();
		booking.setBookingDate(new Date().toString());
		booking.setBookingId(10000 + new Random( System.currentTimeMillis()).nextInt(20000));
		booking.setBookingName(request.getParameter("bookingName"));
		booking.setStartDate(checkIn);
		booking.setEndDate(checkOut);
		booking.setEmailId((String)session.getAttribute("user"));
		booking.setHotel(customerbookingHotel);
		booking.setHotelId(customerbookingHotel.getHotelId());
		booking.setRoomId(hotelroomIDs);
		
		HotelBookingQueries bookingDao = new HotelBookingQueries();
		bookingDao.insertCustomerBooking(booking);
		
		response.sendRedirect("index.jsp");
		
	}
}
