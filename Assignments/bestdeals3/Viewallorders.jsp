<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import=" com.mongodb.*"%>
<%@page import="java.sql.*"%>

<%
	
	try{
	Connection con = null;
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
	
	Statement stmt2 = con.createStatement();
	
	
	String query2 = "Select * from CustomerOrders";
	ResultSet rs1 = stmt2.executeQuery(query2);
	
	
				String orderid=null;
				 String username = null;
				 String order_name= null;
				 String quantity = null;
				 String cost = null;
				 String order_price = null;
				 String cardnum = null;
	
		
			while(rs1.next()){
				
						username = rs1.getString("custName");
						orderid = rs1.getString("OrderId");
						
						order_name = rs1.getString("orderName");
						order_price = rs1.getString("orderPrice");
						quantity = rs1.getString("orderQuantity");
						cost = rs1.getString("finalCost");
						cardnum = rs1.getString("dayexpected");
							
							
		out.println("<html><head><tittle><h1>Order Details</h1></title></head>"
					  +"<body align ='center' bgcolor='#D8D8D8'>"
					  +"<h3>Order placed by: "+ username
					  +"<br>"
					  +"Order Id: "+ orderid
					  +"<br>"
					  +"Order Name: "+ order_name
					  +"<br>"
					  +"Order Price: "+ order_price
					  +"<br>"
					  +"Order Quantity: "+ quantity
					  +"<br>"
					  +"Order Cost: "+ cost
					  +"<br>"
					  +"Delivery Date: "+ cardnum
					  +"</h3>"
					  +"</body>"
					  +"</html>"
					);
						}
						
						String query = "Select * from CustomerOrders";
						ResultSet rs2 = stmt2.executeQuery(query);
						 boolean val = rs2.next(); 
						if(val==false){
			
			out.println("<html>");
		
		out.println("<body align='center'>No orders placed!<br>");
		out.println("</html>");
			
		}
						
							
		out.println("<html>");
		
		out.println("<br><form action='Salesmanhome.jsp'><input type = 'submit' value='Back' name='Back'></form>");
		out.println("</html>");
				
	}
	catch(Exception e){
			e.printStackTrace();
		}
 %>
	