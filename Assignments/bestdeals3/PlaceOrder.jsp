<%@page import="java.util.Calendar"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="java.util.Random"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.*"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Order Placed</title>
</head>
<body>

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
				String userid = (String) request.getSession().getAttribute(
						"username");
				MongoClient mg = new MongoClient("localhost", 27017);
				DB mdb = mg.getDB("Assignment3");
				DBCollection orders = mdb.getCollection("Orders");
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 14);
				Random r = new Random(System.currentTimeMillis());
				Long orderid = (long) (10000 + r.nextInt(20000));
				DBCollection ccart = mdb.getCollection("CustomerCart");
				BasicDBObject bdbo = new BasicDBObject();
				bdbo.put("userName", userid);
				DBCursor dbc = ccart.find(bdbo);
				
				String productName = null;
				double totalCost = 0;
				
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
					String userName = bdc.getString("userName");
					String imageName = bdc.getString("imageName");
					 productName = bdc.getString("productName");
					double productPrice = bdc.getDouble("productPrice");
					double productQuantity = bdc.getDouble("productQuantity");
					 totalCost = bdc.getDouble("totalCost");

					BasicDBObject bdo1 = new BasicDBObject("name", "value")
							.append("userName", userName)
							.append("orderId", orderid)
							.append("imageName", imageName)
							.append("productName", productName)
							.append("productPrice", productPrice)
							.append("productQuantity", productQuantity)
							.append("totalCost", totalCost)
							.append("deliveryDate",c.getTime());
					orders.insert(bdo1);
					
					
					String insertIntoCustomerRegisterQuery  = "INSERT INTO CustomerOrders"+"(OrderId,custName,orderName,orderPrice,orderQuantity,finalCost,dayexpected)" + "VALUES (?,?,?,?,?,?,?);";
				 PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);

				ps.setDouble(1,orderid);  
				ps.setString(2,userid);  
				ps.setString(3,productName);  
				ps.setDouble(4,productPrice); 
				ps.setDouble(5,productQuantity);				
				ps.setDouble(6,totalCost);
				ps.setString(7,day);
				
				ps.executeUpdate(); 
					
					
				}
			%>
			<h1>
			Order Placed Successfully
			<br>
			Your Order id is: <%=orderid %>
			<br>
			Expected delivery date is: <%=c.getTime() %>
			</h1>
			<%
			ccart.remove(bdbo);
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