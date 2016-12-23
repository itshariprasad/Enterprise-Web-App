<%@page import="java.util.Calendar"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mongodb.BasicDBList"%>
<%@page import="com.mongodb.AggregationOutput"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>

<%@page import="Javabeans.Update"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.*"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Orders</title>
</head>
<body class="home">

	<header>

		<h1>
			<font face="Cambria">BEST DEALS</font>
		</h1>
		<h2>The Best Online Retail Store You Can Get</h2>
	</header>

	<%
	
	MongoClient mg11 = new MongoClient("localhost", 27017);
				DB mdb11 = mg11.getDB("Assignment3");
				String userid11 = (String) request.getSession().getAttribute(
						"username");
	DBCollection ccart11 = mdb11.getCollection("CustomerCart");
				BasicDBObject bdbo11 = new BasicDBObject();
				bdbo11.put("userName", userid11);
				DBCursor dbc11 = ccart11.find(bdbo11);
				
						int cartItems = 0;
				if (dbc11.count() == 0) {
					cartItems =0;
				}
				else{
					cartItems = (int) dbc11.count();
				}
	%>
	
	
	<nav>
		<ul>
  <li><a href="Home.jsp" class="home"><font face = "Cambria">HOME</font></a></li>
  <li><a href="Phone.jsp" class="phone"><font face = "Cambria">SMART PHONES</font></a></li>
  <li><a href="Tablet.jsp" class="tablet"><font face = "Cambria">TABLETS</font></a></li>
  <li> <a href="Laptop.jsp" class="laptop"><font face = "Cambria" >LAPTOPS</font></a></li>
  <li> <a href="Tv.jsp" class="tv"><font face = "Cambria" >TV</font></a></li>
  </ul>
		<a href="Log.jsp">SIGN OUT</a> <a href="Cart.jsp">CART (<%=cartItems%> ITEMS)</a> <a
			href="ViewOrders.jsp">MY ORDERS</a>

	</nav>

	<div id="contain">

		<section id="desc">

			<%
				Update up = new Update();
				MongoClient mg = new MongoClient("localhost", 27017);
				DB mdb = mg.getDB("Assignment3");

				String userid = (String) request.getSession().getAttribute(
						"username");
				
				
				if(request.getParameter("cancelorder")!= null){
					String orderid = request.getParameter("orderid");
					String deliverydate = request.getParameter("deliverydate");
					long oid = Long.parseLong(orderid);
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
					c.setTime(sdf.parse(deliverydate));
					Calendar c1 = Calendar.getInstance();
					SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
					String futuredate = formattedDate.format(c.getTime());
					String presentdate = formattedDate.format(c1.getTime());
					Date d1 = null;
					Date d2 = null;
					d1 = formattedDate.parse(presentdate);
					d2 = formattedDate.parse(futuredate);
					long diff = d2.getTime() - d1.getTime();
					long diffDays = diff / (24 * 60 * 60 * 1000);
					up.setUserId(userid);
					up.setOrderid(oid);
					
					DBCollection orders = mdb.getCollection("Orders");
					BasicDBObject bdo = new BasicDBObject();
					bdo.put("userName", up.getUserId());
					bdo.put("orderId", up.getOrderid());
					
					PreparedStatement preparedStmt = null;
			Connection con = null;
			
		Statement stmt = null;	
					Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				stmt = con.createStatement();
				 String sql=null;
				 String orderno=orderid;
									
					if(diffDays >= 5 ){
						orders.remove(bdo);
						
						 sql = "delete FROM CustomerOrders where OrderId =?";
				 preparedStmt = con.prepareStatement(sql);
				 preparedStmt.setString(1, orderno);
				
				preparedStmt.execute();
						
					}
					
					else{%>
					
					<h1 align="center">You cannot cancel the order 5 business days before the delivery date</h1>
				<%		
					}
					
				}

				NumberFormat formatter = NumberFormat
						.getCurrencyInstance(Locale.US);

				DBCollection orders = mdb.getCollection("Orders");
				BasicDBObject bdo = new BasicDBObject();
				bdo.put("userName", userid);
				DBCursor dbc = orders.find(bdo);

				
				DBObject match = null;
				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				AggregationOutput aggregate = null;
				groupFields = new BasicDBObject("_id", 0);
				groupFields.put("_id", "$orderId");
				groupFields.put("imageName", new BasicDBObject("$push",
						"$imageName"));
				groupFields.put("productName", new BasicDBObject("$push",
						"$productName"));
				groupFields.put("productPrice", new BasicDBObject("$push",
						"$productPrice"));
				groupFields.put("productQuantity", new BasicDBObject("$push",
						"$productQuantity"));
				groupFields.put("totalCost", new BasicDBObject("$push",
						"$totalCost"));
				groupFields.put("deliveryDate", new BasicDBObject("$push",
						"$deliveryDate"));

				group = new BasicDBObject("$group", groupFields);

				projectFields = new BasicDBObject("_id", 0);
				projectFields.put("OrderId", "$_id");
				projectFields.put("Image", "$imageName");
				projectFields.put("Product", "$productName");
				projectFields.put("Price", "$productPrice");
				projectFields.put("Quantity", "$productQuantity");
				projectFields.put("Total", "$totalCost");
				projectFields.put("Delivery", "$deliveryDate");

				project = new BasicDBObject("$project", projectFields);
				match = new BasicDBObject("$match", new BasicDBObject("userName",
						userid));
				aggregate = orders.aggregate(match, group, project);
				int rowCount = 0;
				int productCount = 0;
				for (DBObject result1 : aggregate.results()) {
			%>
			<h1 align="center">Your Orders</h1>
			<%
				break;
				}
			%>
			<%
				for (DBObject result : aggregate.results()) {
					BasicDBObject bobj = (BasicDBObject) result;
					BasicDBList imageList = (BasicDBList) bobj.get("Image");
					BasicDBList productList = (BasicDBList) bobj.get("Product");
					BasicDBList priceList = (BasicDBList) bobj.get("Price");
					BasicDBList quantityList = (BasicDBList) bobj.get("Quantity");
					BasicDBList totalList = (BasicDBList) bobj.get("Total");
					BasicDBList deliveryList = (BasicDBList) bobj.get("Delivery");

					rowCount++;
			%>

			<table class="table-2" align="center">
				<tr>
					<th colspan="6">Order Id: <%=bobj.getString("OrderId")%></th>
				</tr>
				<tr>
					<th>Product</th>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Cost</th>
					<th>Estimated Delivery</th>
				</tr>

				<%
					while (productCount < productList.size()) {
				%>

				<tr>
					<td><img src="Images/<%=imageList.get(productCount)%>"
						height="70px" width="70px"></td>
					<td><%=productList.get(productCount)%></td>
					<td><%=formatter.format(priceList.get(productCount))%></td>
					<td><%=new Double(quantityList.get(productCount)
							.toString()).intValue()%></td>
					<td><%=formatter.format(totalList.get(productCount))%></td>
					<td><%=deliveryList.get(productCount)%></td>
				</tr>

				<%
					productCount++;
						}
				%>
			</table>
			<br> <br> 
			<form class="form1" action="#" align="center">
				<input type="hidden" value="<%=bobj.getString("OrderId")%>"
					name="orderid"> <input type="hidden"
					value="<%=deliveryList.get(productCount - 1)%>" name="deliverydate">
				<input type="submit" value="Cancel Order" name="cancelorder">
			</form>
			<%
				productCount = 0;
				}
				if (rowCount == 0) {
			%>

			<h1 align="center">No orders placed recently</h1>

			<%
				}
			%>

		</section>


	<aside class="vnav">
<ul> 
  <li>
  <h3>Categories</h3>
  <h4>Smart Phones</h4>
                   <ul>
						<li><a href="Phone.jsp">All Smart Phones</a></li>
					</ul>
              
  
  <li>
  <h4>Tablets</h4>
                     <ul>
						<li><a href="Tablet.jsp">All Tablets</a></li>
					</ul>
              
  </li>
  
  <li>
  <h4>Laptops</h4>
                      <ul>
						<li><a href="Laptop.jsp">All Laptops</a></li>
					</ul>
                
  
  </li>
  
  <li>
  <h4>TVs</h4>
                      <ul>
						<li><a href="Tv.jsp">All Tvs</a></li>
					</ul>
                
  
  </li>
  
  <li>
  <h3>Accessories</h3>
  <ul>
  <li><a href="Speaker.jsp">Home Speaker</a></li> 
  <li><a href="Charger.jsp">Tablet Charger</a></li> 
  <li><a href="bluray.jsp">Blu-ray Player</a></li> 
  </ul>
  </li>
  
  <li>
  <h3>Trending</h3>
  <ul>
  <li><a href="OrderAnalysis1.jsp">Trending</a></li> 
   </ul>
  </li>
  
</ul>   
</aside>

		<div class="clear"></div>



		<div class="foo">Copyright © www.bestdeals.co.in</div>
	</div>

</body>
</html>