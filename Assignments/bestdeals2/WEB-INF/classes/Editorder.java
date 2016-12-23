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

public class Editorder extends HttpServlet {
	
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	PrintWriter out = response.getWriter();  
	  
	  String ordernumber=request.getParameter("ordernumber");
	  
	  String ordername=request.getParameter("ordername");
	  String orderprice=request.getParameter("orderprice");
	  String orderquan=request.getParameter("orderquan");
	  String orderadd=request.getParameter("orderadd");
	  String orderday=request.getParameter("orderday");
	  
	  
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
				out.println("<br><a href='Home1.html'>Home</a>");
				out.println("</html>");
				
				return;
				 }
				
				 String sql10 = "UPDATE CustomerOrders SET orderName =?, orderPrice =?, orderQuantity =?, userAddress =?, dayexpected =? where OrderId =?";
				  preparedStmt = con.prepareStatement(sql10);
				 preparedStmt.setString(1, ordername);
				  preparedStmt.setString(2, orderprice);
				   preparedStmt.setString(3, orderquan);
				    preparedStmt.setString(4, orderadd);
					 preparedStmt.setString(5, orderday);
				 preparedStmt.setString(6, ordernumber);
				
				preparedStmt.execute();
				 
				
				
				out.println("<html>");
				out.println(ordernumber+ ": Order id has been updated");
				out.println("<a href='Home1.html'>Home</a>");
				out.println("</html>");
				
					con.close();
					
				 }
			
			catch (Exception e)
			{
      out.println("Some fields are missing! Please try again");
      
				}
	  }
	  
				else{
					out.println("<html>");
				out.println("<head><title>Incorrect Order ID</title></head>");
				out.println("<body align='center'>Wrong order ID<br>");
				out.println("<a href='Home1.html'>Home</a>");
				out.println("</html>");
							
		}

		}
}

			
			
