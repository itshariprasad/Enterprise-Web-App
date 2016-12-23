

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LogoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		//equest.getRequestDispatcher("link.html").include(request, response);
		
		Cookie ck=new Cookie("name","");
		ck.setMaxAge(0);
		response.addCookie(ck);
		String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	  
	out.println(docType +"<HTML>\n" +
				"<HEAD><TITLE>Logout</TITLE><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><p align='center'><b>Logged out!</b></p><h3><p align='right'><a href='validate.html'>Login/Sign up</a> &nbsp&nbsp&nbsp <a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp <a href='/bestdeals/OrderPage'>Shopping cart items </a></p></h3></TR>");
    
				request.getRequestDispatcher("/examples.html").include(request,response);
			out.println("</TD></TR></TABLE><HR>\n</BODY></HTML>");
	}
}
