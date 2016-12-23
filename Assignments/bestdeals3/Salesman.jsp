<%@page import="java.util.Random"%>
<%@page import="java.util.Calendar"%>
<%@page import=" com.mongodb.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>
<%
	MongoClient mg = new MongoClient("localhost", 27017);
	DB mdb = mg.getDB("Hw3Bestdeals");

	if (request.getParameter("createaccount") != null) {

		String uname = request.getParameter("email");
		String upass = request.getParameter("password");
		String cpass = request.getParameter("cpassword");
		String username = request.getParameter("name");
		
		Statement stmt = null;
			Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbname=null;
		

		DBCollection cdetails = mdb.getCollection("Cdetails");
		if (upass.equals(cpass)) {

			BasicDBObject bdo = new BasicDBObject("name", "pass")
					.append("username", uname)
					.append("password", upass);

			cdetails.insert(bdo);
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO registration"+"(username,email,password,repassword,registration_type)" + "VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,username);  
				ps.setString(2,uname);  
				ps.setString(3,upass);  
				ps.setString(4,cpass);  
				ps.setString(5,"customer");
						  
				ps.executeUpdate();
			
			
			out.println("<html>");
			out.println("<head><title>Account created</title></head>");
			out.println("<body align='center'>Account created Successfully<br>");
			out.println("<br><form action='Salesmanhome.jsp'><input type = 'submit' value='Back' name='Back'></form>");
			out.println("</html>");
		} else {
			out.println("<html>");
			out.println("<head><title>Wrong Password</title></head>");
			out.println("<body align='center'>Passwords do not match<br>");
			out.println("<br><form action='Salesmanhome.jsp'><input type = 'submit' value='Back' name='Back'></form>");
			out.println("</html>");
		}
	}

	else if (request.getParameter("addorder") != null) {
		String userid = request.getParameter("email");
		String productnamedropdown = request
				.getParameter("productnamedropdown");
		String qty = request.getParameter("quantity");
		double quantity = Double.parseDouble(qty);
		DBCollection orders = mdb.getCollection("Orders");
		DBCollection products = mdb.getCollection("Products");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 14);
		Random r = new Random(System.currentTimeMillis());
		Long orderid = (long) (10000 + r.nextInt(20000));
		BasicDBObject bdbo = new BasicDBObject();
		bdbo.put("field0", productnamedropdown);
		DBCursor dbc = products.find(bdbo);

		
				Statement stmt = null;
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

			String day = format1.format(c.getTime());
				
		
		while (dbc.hasNext()) {

			BasicDBObject bdc = (BasicDBObject) dbc.next();
			String imageName = bdc.getString("field8");
			String pprice = bdc.getString("field1");
			double price = Double.parseDouble(pprice);
			double totalCost = (quantity * price);

			BasicDBObject bdo1 = new BasicDBObject("name", "value")
					.append("userName", userid)
					.append("orderId", orderid)
					.append("imageName", imageName)
					.append("productName", productnamedropdown)
					.append("productPrice", price)
					.append("productQuantity", quantity)
					.append("totalCost", totalCost)
					.append("deliveryDate", c.getTime());
			orders.insert(bdo1);
			
			
			String insertIntoCustomerRegisterQuery  = "INSERT INTO CustomerOrders"+"(OrderId,custName,orderName,orderPrice,orderQuantity,finalCost,dayexpected)" + "VALUES (?,?,?,?,?,?,?);";
				 PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);

				ps.setDouble(1,orderid);  
				ps.setString(2,userid);  
				ps.setString(3,productnamedropdown);  
				ps.setDouble(4,price); 
				ps.setDouble(5,quantity);				
				ps.setDouble(6,totalCost);
				ps.setString(7,day);
				
				ps.executeUpdate(); 
			
			
		}

		out.println("<html>");
		out.println("<head><title>Order Placed</title></head>");
		out.println("<body align='center'>Order Placed Successfully<br>");
		out.println("<br><form action='Salesmanhome.jsp'><input type = 'submit' value='Back' name='Back'></form>");
		out.println("</html>");
	}

	else if (request.getParameter("delorder") != null) {
		String userid = request.getParameter("email");
		String productnamedropdown = request
				.getParameter("productnamedropdown");
		String orderid = request.getParameter("orderid");
		long orderId = Long.parseLong(orderid);
		DBCollection orders = mdb.getCollection("Orders");
		BasicDBObject bdo = new BasicDBObject();
		bdo.put("userName", userid);
		bdo.put("orderId", orderId);
		bdo.put("productName", productnamedropdown);
		orders.remove(bdo);
		
		PreparedStatement preparedStmt = null;
			Connection con = null;
			
		Statement stmt = null;	
					Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				stmt = con.createStatement();
				 String sql=null;
				 String orderno=orderid;
		 sql = "delete FROM CustomerOrders where OrderId =?";
				 preparedStmt = con.prepareStatement(sql);
				 preparedStmt.setString(1, orderno);
				
				preparedStmt.execute();
		
		out.println("<html>");
		out.println("<head><title>Order Deleted</title></head>");
		out.println("<body align='center'>Order Deleted Successfully<br>");
		out.println("<br><form action='Salesmanhome.jsp'><input type = 'submit' value='Back' name='Back'></form>");
		out.println("</html>");

	} else if (request.getParameter("uporder") != null) {
		String userid = request.getParameter("email");
		String productnamedropdown = request
				.getParameter("productnamedropdown");
		String orderid = request.getParameter("orderid");
		String prprice = request.getParameter("prprice");
		String qty = request.getParameter("quantity");
		long orderId = Long.parseLong(orderid);
		double productPrice = Double.parseDouble(prprice);
		double quantity = Double.parseDouble(qty);
		double total = (productPrice * quantity);
		DBCollection orders = mdb.getCollection("Orders");
		BasicDBObject bdo = new BasicDBObject();
		bdo.append(
				"$set",
				new BasicDBObject()
						.append("productPrice", productPrice)
						.append("productQuantity", quantity)
						.append("totalCost", total));
		BasicDBObject bdo1 = new BasicDBObject().append("userName",
				userid).append("orderId", orderId).append("productName", productnamedropdown);
		orders.update(bdo1, bdo);
		
		 PreparedStatement preparedStmt = null;
			Connection con = null;
			
		Statement stmt = null;	
		Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				stmt = con.createStatement();
				 String sql10=null;
				 String orderno=orderid;
				 
				  sql10 = "UPDATE CustomerOrders SET orderPrice =?, orderQuantity =?, finalCost =? where orderName =? AND OrderId =?";
				  preparedStmt = con.prepareStatement(sql10);
				 
				  preparedStmt.setString(1, prprice);
				   preparedStmt.setString(2, qty);
				    preparedStmt.setDouble(3, total);
				preparedStmt.setString(4, productnamedropdown);
				 preparedStmt.setString(5, orderno);
				
				preparedStmt.execute();
				 
		
		out.println("<html>");
		out.println("<head><title>Order Updated</title></head>");
		out.println("<body align='center'>Order Updated Successfully<br>");
		out.println("<br><form action='Salesmanhome.jsp'><input type = 'submit' value='Back' name='Back'></form>");
		out.println("</html>");

	}
%>