package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HotelsDao;

public class AdminPage extends HttpServlet{
	HotelsDao hotelDao = new HotelsDao();
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		session.setAttribute("hotels", hotelDao.getHotelDetails());
		response.sendRedirect("scarletAdmin.jsp");
	}
}
