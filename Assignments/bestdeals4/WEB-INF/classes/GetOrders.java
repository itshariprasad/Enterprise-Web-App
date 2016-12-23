
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.util.*;
import java.sql.*;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have
 *  no values or multiple values.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class GetOrders extends HttpServlet {
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	
	response.setContentType("text/html");
	
	String Name = request.getParameter("name");
	
	PrintWriter out = response.getWriter(); 
	ResultSetMetaData resultmetadata = null;
	ResultSetMetaData resultmetadata11 = null;
	ResultSetMetaData resultmetadata1 = null;
	
	
	try{
	Connection con = null;
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
	Statement stmt = con.createStatement();
	Statement stmt2 = con.createStatement();
	Statement stmt5 = con.createStatement();
	String query1 = "SELECT username FROM Registration";
	ResultSet rs = stmt.executeQuery(query1);
	String query2 = "Select custName from CustomerOrders";
	ResultSet rs1 = stmt2.executeQuery(query2);
	String dbname = null;
	ArrayList<String> third = new ArrayList<String>();
	
				String orderid=null;
				 String username = null;
				 String order_name= null;
				 String quantity = null;
				 String address = null;
				 String cardnum = null;
				 String order_price = null;
	int i =1;
	resultmetadata = rs.getMetaData();
	 int numCols = resultmetadata.getColumnCount();
	 while(rs.next()){
					//Retrieve by column name
					for(i=1;i<=numCols;i++) {
					dbname = rs.getString(i);;
					 third.add(dbname);
					
	 }
	 }		 
		//out.println(third);
		resultmetadata11 = rs1.getMetaData();
		
	 int numCols11 = resultmetadata11.getColumnCount();
		String dbname11 = null;
	ArrayList<String> third11 = new ArrayList<String>();
	int j1 = 1;
			while(rs1.next()){
				for(j1=1;j1<=numCols11;j1++) {
				dbname11 = rs1.getString(j1);;
					 third11.add(dbname11);
					
			}
			}	
						//username = rs1.getString("custName");
	//out.println(Name);
						//if(username.equals(third)){
						
						
						String query5 = "Select * from CustomerOrders";
	ResultSet rs5 = stmt5.executeQuery(query5);
	int count = 0;
	while(rs5.next()){
		username = rs5.getString("custName");
	if((username.equals(Name)) && (third11.contains(Name)) && (third.contains(Name))) {
		
		count++;
					// HashMa.getHm().put(dbemail, dbpwd);
						orderid = rs5.getString("OrderId");
						
						order_name = rs5.getString("orderName");
						order_price = rs5.getString("orderPrice");
						quantity = rs5.getString("orderQuantity");
						address = rs5.getString("userAddress");
						cardnum = rs5.getString("dayexpected");
							
							
		out.println("<html><head><tittle><h1>Order Details</h1></title></head>"
					  +"<body align ='center' bgcolor='#D8D8D8'>"
					  +"<h3>Your orders:"
					  +"<br>"
					  +"Order Id: "+ orderid
					  +"<br>"
					  +"Order Name: "+ order_name
					  +"<br>"
					  +"Order Price: "+ order_price
					  +"<br>"
					  +"Order Quantity: "+ quantity
					  +"<br>"
					  +"Order Address: "+ address
					  +"<br>"
					  +"Delivery date: "+ cardnum
					  +"</h3>"
					  +"</body>"
					  +"</html>"
					);
						}
						}
						
						if(count==0){
							 out.println("<html>");
				out.println("<head><title>No orders id</title></head>");
				out.println("<body align='center'>No orders are placed for you!<br>");
				out.println("<br><a href='Home.html'>Home</a>");
				out.println("</html>");
				
				//req.getRequestDispatcher("/Rreg.html").include(req, res);
				return;
							
						
						}
	
	 
	 
	ArrayList<String> third1 = new ArrayList<String>();
	Statement stmt3 = con.createStatement();
	String query3 = "Select custName from CustomerOrders";
	ResultSet rs3 = stmt3.executeQuery(query3);
	resultmetadata1 = rs3.getMetaData();
	 String dbname1 = null;
	 
	 int numCols1 = resultmetadata1.getColumnCount();
	 while(rs3.next()){
					//Retrieve by column name
					for(int j=1;j<=numCols1;j++) {
					dbname1 = rs3.getString(j);;
					 third1.add(dbname1);
					
	 }
	 }
	 
	out.println("<TR><TD colspan='4'><FORM action='/bestdeals4/Deleteorder'> Enter Order Id to Delete : <INPUT TYPE='TEXT' NAME='ordernumber' SIZE=10 VALUE=''>&nbsp&nbsp<INPUT TYPE='SUBMIT' VALUE='Delete Order'></FORM></TD></TR>" );
	
	
	out.println("<html>");
				out.println("<a href='Home.html'>Home</a>");
				out.println("</html>");
				
	}
	catch(Exception e){
			e.printStackTrace();
		}
  }
}
	