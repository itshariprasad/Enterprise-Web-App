package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasequery.HotelsDatabaseQuery;

@WebServlet("/adminPage")
public class AdminScarlet extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		HotelsDatabaseQuery hotelDao = new HotelsDatabaseQuery();
		session.setAttribute("hotels", hotelDao.getdatadetails());
		response.sendRedirect("admin.jsp");
	}
}
