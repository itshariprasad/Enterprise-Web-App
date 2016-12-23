

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HomePage1 extends HttpServlet {
  
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
  HttpSession session = request.getSession();
	
	
	String cartItems="";
	
	Cookie[] cookies = request.getCookies();
	String value = "";
	try{
	cartItems = session.getAttribute("cartItems").toString();
	//name = session.getAttribute("LoginServlet").toString();
	
	
	 if (cartItems == null) {
	 session.setAttribute("cartItems","0");
        cartItems = "0";
      }
	  
	//Cookie[] cookies = request.getCookies();
	if (cookies != null) {
	for (Cookie cookie : cookies) {
   if (cookie.getName().equals("name")) {
		value = cookie.getValue();
}
	}
	}
   if(value == null){
	    value = "Guest";
   }
   
	  
   }
	catch(Exception e){cartItems = "0";}
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
	
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    /***out.println(docType +
                "<HTML>\n" +
				"<HEAD><TITLE>Best Deals</TITLE><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><TD BGCOLOR='#75E9F2' ALIGN='CENTER' COLSPAN='2'><H1>Welcome BestDeal Store</H1><P>The best Online Retail Store </P><h3><p align='right'><a href='/bestdeals/ViewOrdersPage'>View Orders</a> &nbsp&nbsp&nbsp Shopping Cart(<a href='/bestdeals/OrderPage'>"+cartItems+" items </a>)</p></h3></TD></TR>"+
				"<TR><TD ALIGN='LEFT' WIDTH='20%' BGCOLOR='#C1F3CA'><FONT COLOR='#FFFFFF'><H2><FONT COLOR='#FF0000'>&sect;</FONT><a href='/bestdeals/PhonePage'>Smart Phones</a><BR><br><br>"+
				"<FONT COLOR='#FF0000'>&sect;</FONT><a href='/bestdeals/TabletPage'>Tablets</a><BR><br><br><FONT COLOR='#FF0000'>&sect;</FONT><a href='/bestdeals/LaptopPage'>Laptops</a><BR><br><br><FONT COLOR='#FF0000'>&sect;</FONT>"+
				"<a href='/bestdeals/TVPage'>TVs</a><BR><br><br></H2></TD><TD>");
   ***/
   
    out.println(docType +"<HTML>\n" +
				"<HEAD><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><h3><p align='right'><a href='/bestdeals/LogoutServlet'>Logout</a>&nbsp&nbsp&nbsp <a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp Shopping Cart(<a href='/bestdeals/OrderPage'>"+cartItems+" items </a>)</p></h3></TR>");
    
	request.getRequestDispatcher("/examples.html").include(request,response);
    out.println("</TD></TR></TABLE><HR>\n</BODY></HTML>");
  }
}
