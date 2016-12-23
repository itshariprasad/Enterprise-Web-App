
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.util.*;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have
 *  no values or multiple values.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class Checkout extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

	  
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	  Random r = new Random( System.currentTimeMillis() );
	int rand= 10000 + r.nextInt(20000);
	String randString=Integer.toString(rand);
    String title = "Your Order Placed Successfully";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
				"<H3 ALIGN=CENTER>Your Order Number :" + randString + "</H3>\n" +
                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
                "<TR BGCOLOR=\"#75E9F2\">\n" +
                "<TH>Parameter Name<TH>Parameter Value(s)");
				 
    Enumeration paramNames = request.getParameterNames();
	ServletContext sc = request.getSession().getServletContext();
	
	File fname= new File(sc.getRealPath("OrderDetails.txt"));
	FileWriter fileWriter = new FileWriter(fname,true);
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	String storeorder=randString+"-";

	
	request.getParameterValues("email");
	request.getParameterValues("firstName");
	request.getParameterValues("lastName");
	request.getParameterValues("initial");
	request.getParameterValues("address");
	
	
	storeorder+=request.getParameter("email")+"-";
	storeorder+=request.getParameter("firstName")+"-";
	storeorder+=request.getParameter("lastName")+"-";
	
	out.print("<TR><TD> First Name  \n</TD>");
	out.print("<TD>" + request.getParameter("firstName") + "\n</TD></TR>");
	
	out.print("<TR><TD> Last Name  \n</TD>");
	out.print("<TD>" + request.getParameter("lastName") + "\n</TD></TR>");
	
	out.print("<TR><TD> Initial \n</TD>");
	out.print("<TD>" + request.getParameter("initial") + "\n</TD></TR>");
	
	out.print("<TR><TD> Address  \n</TD>");
	out.print("<TD>" + request.getParameter("address") + "\n</TD></TR>");
	
    Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, 14);
	Date newDate = cal.getTime();

	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	out.print("<TR><TD> Delivery Date  \n</TD>");
	out.print("<TD>" + dateFormat.format(newDate) + "\n</TD></TR>");
	
	storeorder+=dateFormat.format(newDate)+"\n";
	 String backURL =
          response.encodeURL("/bestdeals/HomePage1");

	out.println("</TABLE>\n" +
           "<FORM ACTION=\"" + backURL + "\">\n" +
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Back to Best Deal Store\">\n" +
           "</CENTER></BIG></FORM>");
    
	out.println("</BODY></HTML>");
	bufferedWriter.write(storeorder);
	
	bufferedWriter.close();
	request.getSession().invalidate();
	
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
