<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.DBCollection"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Charger</title>
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
			<img class="img-disp" alt="tablet" src="Images/tablet.jpg" height="20%" width="100%">

			<%
				MongoClient mg = new MongoClient("localhost", 27017);
				DB mdb = mg.getDB("Assignment3");
				DBCollection products = mdb.getCollection("Products");
				BasicDBObject bdbo = new BasicDBObject();
				bdbo.put("field3", "Tablet Accessory");
				NumberFormat formatter = NumberFormat
						.getCurrencyInstance(Locale.US);
				DBCursor dbc = products.find(bdbo);

				if (dbc.count() == 0) {
			BasicDBObject bdbo1 = new BasicDBObject();
									bdbo1.append("field0", "Fast Charger")
											.append("field1", "50")
											.append("field2", "Other")
											.append("field3", "Tablet Accessory")
											.append("field4", "New")
											.append("field5", "Yes")
											.append("field6", "0")
											.append("field7", "0")
											.append("field8", "charger1.jpg")
											.append("field9",
													System.currentTimeMillis());
									
									products.insert(bdbo1);
								
									BasicDBObject bdb = new BasicDBObject();
									bdb.put("field3", "Tablet Accessory");
									BasicDBObject sort1 = new BasicDBObject();
									sort1.put("field9", -1);
									DBCursor dbc1 = products.find(bdb).sort(sort1);

									while (dbc1.hasNext()) {

										BasicDBObject bdc = (BasicDBObject) dbc1
												.next();
			%>


			<form action="AddView.jsp" method="post">
				<input type="hidden" value="<%=bdc.getString("field0")%>"
					name="prname"> <input type="hidden"
					value="<%=bdc.getString("field1")%>" name="prprice">
				<table class="table-1" align="center">

					<tr>
						<th><%=bdc.getString("field0")%></th>
						<th>$<%=bdc.getString("field1")%>
						</th>
					</tr>

					<tr>
						<td rowspan="3"><img
							src="Images/<%=bdc.getString("field8")%>" height="20%"
							width="50%"></td>
						<td><input type="submit" name="addtocart" value="Add to Cart"></td>
					</tr>
					<tr>
						<td><input type="submit" name="writerev" value="Write Review"></td>
					</tr>
					<tr>
						<td><input type="submit" name="viewreview"
							value="View Review"></td>
					</tr>

					<tr>
						<th>Rebate: $<%=bdc.getString("field6")%></th>
						<th>Discount: $<%=bdc.getString("field7")%></th>
					</tr>


					<tr>
						<th>Product type: <%=bdc.getString("field4")%></th>
						<th>Warranty: <%=bdc.getString("field5")%></th>
					</tr>


				</table>
			</form>

			<%
				}
								}
				else {
					while (dbc.hasNext()) {

						BasicDBObject bdc = (BasicDBObject) dbc.next();
			%>


			<form action="AddView.jsp" method="post">
				<input type="hidden" value="<%=bdc.getString("field0")%>"
					name="prname"> <input type="hidden"
					value="<%=bdc.getString("field1")%>" name="prprice">
				<table class="table-1" align="center">

					<tr>
						<th><%=bdc.getString("field0")%></th>
						<th>$<%=bdc.getString("field1")%>
						</th>
					</tr>

					<tr>
						<th rowspan="3"><img
							src="Images/<%=bdc.getString("field8")%>" height="200px"
							width="200px"></th>
						<td><input type="submit" name="addtocart" value="Add to Cart"></td>
					</tr>
					<tr>
						<td><input type="submit" name="writerev" value="Write Review"></td>
					</tr>
					<tr>
						<td><input type="submit" name="viewreview"
							value="View Review"></td>
					</tr>
					
					<tr>
						<th>Rebate: $<%=bdc.getString("field6")%></th>
						<th>Discount: $<%=bdc.getString("field7")%></th>
					</tr>
					

					<tr>
						<th>Product type: <%=bdc.getString("field4")%></th>
						<th>Warranty: <%=bdc.getString("field5")%></th>
					</tr>


				</table>
			</form>

			<%
				}
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
