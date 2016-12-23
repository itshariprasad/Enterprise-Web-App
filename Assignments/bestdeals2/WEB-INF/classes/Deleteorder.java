import java.sql.*;
import java.util.Date;
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

public class Deleteorder extends HttpServlet {
	
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	PrintWriter out = response.getWriter();  
	  
	  String ordernumber=request.getParameter("ordernumber");
	  
	  PreparedStatement preparedStmt = null;
			Connection con = null;
			
		Statement stmt = null;	
			ResultSetMetaData resultmetadata = null;
			String dbname = null;
	ArrayList<String> third = new ArrayList<String>();
	
	  if(ordernumber!=null)
	  {
		try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				stmt = con.createStatement();
				 String sql1=null;
				 String orderno=null;
				 sql1 = "SELECT OrderId FROM CustomerOrders";
				 ResultSet rs = stmt.executeQuery(sql1);
				
				 // Extract data from result set
				 resultmetadata = rs.getMetaData();
	 int numCols = resultmetadata.getColumnCount();
	 while(rs.next()){
					//Retrieve by column name
					for(int i=1;i<=numCols;i++) {
					dbname = rs.getString(i);;
					 third.add(dbname);
					
	 }
	 }		 
					if(!third.contains(ordernumber)){
					 out.println("<html>");
				out.println("<head><title>Incorrect order id</title></head>");
				out.println("<body align='center'>Wrong order id<br>");
				out.println("<br><a href='Home.html'>Home</a>");
				out.println("</html>");
				
				//req.getRequestDispatcher("/Rreg.html").include(req, res);
				return;
				 }
				 			
				
				
				 String sql=null;
				// String dbname=null;
				 sql = "delete FROM CustomerOrders where OrderId =?";
				 preparedStmt = con.prepareStatement(sql);
				 preparedStmt.setString(1, ordernumber);
				
				preparedStmt.execute();
				
				
				
				out.println("<html>");
				//out.println("<head><title>Incorrect UserName</title></head>");
				//out.println("<body align='center'>No orders placed for you!!!<br>");
				out.println(ordernumber+ ": Order id has been deleted");
				out.println("<a href='Home.html'>Home</a>");
				out.println("</html>");
				
					con.close();
					
				 }
			
			catch (Exception e)
			{
      out.println("Got an exception! ");
      out.println(e.getMessage());
				}
	  }
	  
				else{
					out.println("<html>");
				out.println("<head><title>Incorrect Order ID</title></head>");
				out.println("<body align='center'>Wrong order ID<br>");
				out.println("<a href='Home.html'>Home</a>");
				out.println("</html>");
							
		}

		}
}
