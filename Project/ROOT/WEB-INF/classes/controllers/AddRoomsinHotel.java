package controllers;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.HotelBeans;
import beans.RoomHotelBean;
import databasequery.HotelsDatabaseQuery;

@WebServlet("/addRooms")
public class AddRoomsinHotel extends HttpServlet
{
	ArrayList<String> hotelfeatures = new ArrayList<String>();
	int total_rooms = 0;
		public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
		{
			int hotelFeature;
			int roomtypesNumber;
			HttpSession session = request.getSession();
			Float hotelminPrice = Float.MAX_VALUE;
			List<FileItem> items = null;
			ArrayList<RoomHotelBean> hotel_rooms_list = new ArrayList<RoomHotelBean>();
			ArrayList<String> hotelroomTypes = new ArrayList<String>();
			HashMap<String, Integer> hotelroomCount = new HashMap<String, Integer>();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ArrayList<FileItem> hotelimages = new ArrayList<FileItem>();
			ArrayList<String> hotelimageLocation = new ArrayList<>();
			HashMap<String, String> hoteall = new HashMap<String, String>();
			HotelBeans scarlet = new HotelBeans();
			
			HotelsDatabaseQuery hotelDatabasequery = new HotelsDatabaseQuery();
			
			hotelFeature = Integer.parseInt(session.getAttribute("featureCount").toString());
			roomtypesNumber =  Integer.parseInt(session.getAttribute("typeCount").toString());
			String filePath = request.getServletContext().getRealPath("")+ File.separator + "Hotels"+File.separator+
					session.getAttribute("hotelName").toString().replaceAll("\\s","").replaceAll("\\W", "");
			try 
			{
				items = upload.parseRequest(request);
			}
			catch (FileUploadException e) 
			{
				e.printStackTrace();	
			}
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) 
			{
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
				hoteall.put(item.getFieldName(),item.getString());
			} 
			else 
			{
					hotelimages.add(item);
			}
		}
		
		for(int i=1;i<=hotelFeature;i++)
		{
			hotelfeatures.add(session.getAttribute("f"+i).toString());
		}
			
		scarlet.setHotelName(session.getAttribute("hotelName").toString());
		scarlet.setHotelCity(session.getAttribute("cityName").toString());
		scarlet.setZipCode(session.getAttribute("zipCode").toString());
		scarlet.setHotelAddress(session.getAttribute("hotelAddress").toString());
		scarlet.setUrl(session.getAttribute("hotel_url").toString());
		scarlet.setCancellationPolicy(session.getAttribute("cancellation").toString());
		scarlet.setHotelId(hotelDatabasequery.getCount()+1);
		scarlet.setHotelRating(0);
		scarlet.setHotelDetails(session.getAttribute("hotelDescription").toString());
		
		
		scarlet.setFeatures(hotelfeatures);
		
		for(int i=1;i<=roomtypesNumber;i++)
		{
			for(int j=0;j<((Integer.parseInt(hoteall.get("roomsNum"+i).toString())));j++)
			{
				total_rooms++;
				int count = 1;
				RoomHotelBean scarlettemp = new RoomHotelBean();
				scarlettemp.setHotelName(scarlet.getHotelName());
				scarlettemp.setHotelAddress(scarlet.getHotelAddress());
				scarlettemp.setHotelCity(scarlet.getHotelCity());
				scarlettemp.setZipcode(scarlet.getZipCode());
				scarlettemp.setRoomTypeName(hoteall.get("roomType"+i));
				HashMap<String, String> hotel_room_feature = new HashMap<String, String>();
				for(int hotelfeature=1;hotelfeature<=hotelFeature;hotelfeature++)
				{
					hotel_room_feature.put(hoteall.get(i+"f"+hotelfeature+"name"), hoteall.get(i+"f"+hotelfeature+"status"));
				}
				scarlettemp.setFeatures(hotel_room_feature);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				HashMap<String, Long> datesHash = new HashMap<String, Long>();
				ArrayList<HashMap<String, Long>> dates = new ArrayList<HashMap<String,Long>>();
				try 
				{
					Date date_tem = formatter.parse(hoteall.get("startDate"+i));
					datesHash.put("startDate", date_tem.getTime()/1000);
					date_tem = formatter.parse(hoteall.get("endDate"+i));
					datesHash.put("endDate", date_tem.getTime()/1000);
					datesHash.put("status", (long) 1);
					dates.add(datesHash);
					scarlettemp.setDates(dates);
				} 
				catch (ParseException e) 
				{
					e.printStackTrace();
				}
				scarlettemp.setHotelPrice(Integer.parseInt(hoteall.get("roomPrice"+i).toString()));
				scarlettemp.setHotelRoomId(total_rooms);
				ArrayList<String> scarletLocation = new ArrayList<>();
				for(FileItem item : hotelimages)
				{
					
					String type = hoteall.get("roomType"+item.getFieldName().substring(6)).replaceAll("\\s","").replaceAll("\\W", "");
					if(type.equals(scarlettemp.getRoomTypeName()))
					{
						try 
						{
							scarletLocation.add(type+File.separator+"image"+(count+1)+".jpg");
							count ++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
				if(scarlettemp.getPrice() < hotelminPrice)
				hotelminPrice = scarlettemp.getPrice();
				scarlettemp.setImages(scarletLocation);
				hotel_rooms_list.add(scarlettemp);
			}
			hotelroomTypes.add(hoteall.get("roomType"+i));
			hotelroomCount.put(hoteall.get("roomType"+i), Integer.parseInt(hoteall.get("roomsNum"+i).toString()));
		}
		
		scarlet.setHotelRooms(hotel_rooms_list);
		scarlet.setHotelRoomTypes(hotelroomTypes);
		scarlet.setHotelNumberOfRooms(hotelroomCount);
		scarlet.setHotelTotalRooms(total_rooms);
		scarlet.setHotelImages((ArrayList<String>) session.getAttribute("hotelImages"));
		scarlet.setHotelMinPrice(hotelminPrice);
		scarlet.setTrue(true);
		
		for(int i=0;i<roomtypesNumber;i++)
		{
			new File(filePath+File.separator+scarlet.getRoomTypes().get(i).replaceAll("\\s","").replaceAll("\\W", "")).mkdir();
		}
		
		for(FileItem item : hotelimages)
		{
			String type = hoteall.get("roomType"+item.getFieldName().substring(6)).replaceAll("\\s","").replaceAll("\\W", "");
			{
				int count = new File(filePath+File.separator+type).list().length;
				try {
					item.write(new File(filePath+File.separator+type+File.separator+"image"+(count+1)+".jpg"));
					hotelimageLocation.add(type+File.separator+"image"+(count+1)+".jpg");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for(int i=0;i<scarlet.getHotelRooms().size();i++)
		{
			RoomHotelBean room = scarlet.getHotelRooms().get(i);
			ArrayList<String> scarletimagesLoc = new ArrayList<>();
 			for(String image : hotelimageLocation)
			{
				if(room.getRoomTypeName().replaceAll("\\s","").replaceAll("\\W", "").equals(image.split("\\\\")[0]))
				{
					scarletimagesLoc.add("Hotels"+File.separator+scarlet.getHotelName().replaceAll("\\s","").replaceAll("\\W", "")
							+File.separator+image);
				}
			}
 			scarlet.getHotelRooms().get(i).setImages(scarletimagesLoc);
		}
		hotelDatabasequery.insertCustomerOrder(scarlet);
		response.sendRedirect("addHotel.jsp");
	}
}
