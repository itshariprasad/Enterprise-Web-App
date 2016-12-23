package controllers;

import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

@WebServlet("/addHotel")
@MultipartConfig(fileSizeThreshold=2048*2048*2, 
				maxFileSize=2048*2048*10,
				maxRequestSize=2048*2048*50)

public class ScarletHotelAdd extends HttpServlet{

	ArrayList<String> image = new ArrayList<>();
	ArrayList<String> imagesLocation = new ArrayList<>();
	String hotelName = "";
	FileItemFactory factory = new DiskFileItemFactory();
	int images =1;
	

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		
		String filePath = request.getServletContext().getRealPath("")+ File.separator + "Hotels";
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = null;
		try 
		{
			list = upload.parseRequest(request);
		} 
		catch (FileUploadException e) 
		{
			e.printStackTrace();
		}
		Iterator<FileItem> itr = list.iterator();
		while (itr.hasNext()) 
		{
			FileItem item = (FileItem) itr.next();
			if (item.isFormField()) 
			{
				if(item.getFieldName().equals("totalCount"))
				session.setAttribute(item.getFieldName(),Integer.parseInt(item.getString()));
				session.setAttribute(item.getFieldName(),item.getString());
			} 
			else 
			{
				try 
				{
						hotelName = session.getAttribute("hotelName").toString().replaceAll("\\s","").replaceAll("\\W", "");
						File f = new File(filePath);
						new File(filePath+File.separator+hotelName).mkdir();
						item.write(new File(filePath+File.separator+hotelName+File.separator+"image"+images+".jpg"));
						imagesLocation.add("Hotels"+File.separator+session.getAttribute("hotelName").toString().
									replaceAll("\\s","").replaceAll("\\W", "")+File.separator+"image"+images+".jpg");
						images++;
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		session.setAttribute("hotelImages", imagesLocation);
		response.sendRedirect("/addRooms.jsp");
	}
}
