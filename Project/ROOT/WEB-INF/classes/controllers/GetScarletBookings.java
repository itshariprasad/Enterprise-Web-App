package controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasequery.HotelBookingQueries;

@WebServlet("/getBookings")
public class GetScarletBookings extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		HotelBookingQueries hotelbookingqueries = new HotelBookingQueries();
		session.setAttribute("bookings",hotelbookingqueries.getBookingDetails((String) session.getAttribute("user")));
		response.sendRedirect("reservations.jsp");
	}

}
