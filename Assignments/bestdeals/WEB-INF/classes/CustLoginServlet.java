
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
	
	ServletContext sc = request.getSession().getServletContext();
	
				//File fname= new File(sc.getRealPath("registration.txt"));
		
		//request.getRequestDispatcher("link.html").include(request, response);
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
				
	
				if(name.equals("Hari") && password.equals("hari") && email.equals("admin@bestdeals.com")){
				
				
				
		
		out.print("<p align='center'>You are successfully logged in as Salesman!</p>");
			out.print("<p align='center'>Welcome, "+name);
			out.print("</p>");
			
			 String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";	
			out.println(docType +"<HTML>\n" +
				"<HEAD><TITLE>Logged in!</TITLE><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><h3><p align='right'>Hello,"+name+"&nbsp&nbsp&nbsp<a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp <a href='/bestdeals/OrderPage'>Shopping cart items </a></p></h3></TR>");
			
			
			Cookie ck=new Cookie("name",name);
			response.addCookie(ck);
			request.getRequestDispatcher("/examples.html").include(request, response);
			
			out.println("</TD></TR></TABLE><HR>\n</BODY></HTML>");
		}
		else {
		
			//out.print("sorry, username or password error!");
			//request.getRequestDispatcher("login.html").include(request, response);
		
		String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	  
	out.println(docType +"<HTML>\n" +
				"<HEAD><TITLE>Login error!</TITLE><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><p align='center'><b>Login credentials failed!</b></p><h3><p align='right'><a href='validate.html'>Login/Sign up</a> &nbsp&nbsp&nbsp <a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp <a href='/bestdeals/OrderPage'>Shopping cart items </a></p></h3></TR>");
    
				request.getRequestDispatcher("/examples.html").include(request,response);
			out.println("</TD></TR></TABLE><HR>\n</BODY></HTML>");
		}
		
		out.close();
}
}