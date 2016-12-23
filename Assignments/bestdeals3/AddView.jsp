<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%
	String productname = request.getParameter("prname");
	String productprice = request.getParameter("prprice");
	if (request.getParameter("writerev") != null) {
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Write Review</title>
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


		<section class="wrev">
			<form action="Writerev.jsp">
				<table align="center">
					<tr>
						<td><label>Product Name</label></td>
						<td>:</td>
						<td><input type="text" value="<%=productname%>" name="pmodel"
							required="required"></td>
					</tr>
					<tr>
						<td><label>Product Category</label></td>
						<td>:</td>
						<td><select name="pcategory">
								<option value="Phone">Smart Phone</option>
								<option value="Tablet">Tablet</option>
								<option value="Laptop">Laptop</option>
								<option value="Tv">Tv</option>
								<option value="Tv Accessory">Accessory - TV</option>
								<option value="Tablet Accessory">Accessory - Tablet</option>
								<option value="Other">Other</option>
						</select></td>
					</tr>
					<tr>
						<td><label>Product Price</label></td>
						<td>:</td>
						<td><input type="text" value="<%=productprice%>" name="pprice"
							required="required"></td>
					</tr>
					<tr>
						<td><label>Retailer Name</label></td>
						<td>:</td>
						<td><input type="text" name="retailer" required="required"></td>
					</tr>
					<tr>
						<td><label>Retailer Zip</label></td>
						<td>:</td>
						<td><input type="text" name="rzip" required="required"></td>
					</tr>
					<tr>
						<td><label>Retailer City</label></td>
						<td>:</td>
						<td><input type="text" name="rcity" required="required"></td>
					</tr>
					<tr>
						<td><label>Retailer State</label></td>
						<td>:</td>
						<td><input type="text" name="rstate" required="required"></td>
					</tr>
					<tr>
						<td><label>Product On Sale</label></td>
						<td>:</td>
						<td><input type="radio" name="pos" value="Yes"
							checked="checked">Yes <input type="radio" name="pos"
							value="No" required="required">No</td>
					</tr>
					<tr>
						<td><label>Manufacturer Name</label></td>
						<td>:</td>
						<td><select name="mname">
								<option value="Apple">Apple</option>
								<option value="Sony">Sony</option>
								<option value="Samsung">Samsung</option>
								<option value="Nexus">Nexus</option>
								<option value="LG">LG</option>
								<option value="Dell">Dell</option>
								<option value="Other">Other</option>
						</select></td>
					</tr>
					<tr>
						<td><label>Manufacturer Rebate</label></td>
						<td>:</td>
						<td><input type="radio" name="mrebate" value="Yes">Yes
							<input type="radio" name="mrebate" value="No" checked="checked">No</td>
					</tr>
					<tr>
						<td><label>User Id</label></td>
						<td>:</td>
						<td><input type="text" name="userid" required="required"
							value="<%=request.getSession().getAttribute("username")%>"></td>
					</tr>
					<tr>
						<td><label>Age</label></td>
						<td>:</td>
						<td><input type="text" name="uage" required="required"></td>
					</tr>
					<tr>
						<td><label>Gender</label></td>
						<td>:</td>
						<td><input type="radio" name="gender" value="Male"
							checked="checked">Male <input type="radio" name="gender"
							value="Female">Female</td>
					</tr>
					<tr>
						<td><label>Occupation</label></td>
						<td>:</td>
						<td><input type="text" name="occu" required="required"></td>
					</tr>
					<tr>
						<td><label>Rating</label></td>
						<td>:</td>
						<td><input type="radio" name="rating" value="1">1 <input
							type="radio" name="rating" value="2">2 <input
							type="radio" name="rating" value="3">3 <input
							type="radio" name="rating" value="4">4 <input
							type="radio" name="rating" value="5" checked="checked">5</td>
					</tr>
					<tr>
						<td><label>Date</label></td>
						<td>:</td>
						<td><input type="date" placeholder="mm/dd/yyyy" name="rdate"
							required="required"></td>
					</tr>
					<tr>
						<td><label>Review Text</label></td>
						<td>:</td>
						<td><textarea rows="5" cols="50" name="reviewt"
								placeholder="Your Review..."></textarea></td>
					</tr>
				</table>
				<br> <br> <input type="submit" value="Submit Review">
			</form>
		</section>



		<div class="clear"></div>



		<div class="foo">Copyright © www.bestdeals.co.in</div>
	</div>

</body>
</html>

<%
	}

	else if (request.getParameter("viewreview") != null) {
		MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");

		DBCollection creviews = mdb.getCollection("CustomerReviews");

		BasicDBObject bdo = new BasicDBObject();
		bdo.put("productName", productname);

		DBCursor c = creviews.find(bdo);
%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>User Reviews</title>
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
			<h2>
				User Reviews:
				<%=productname%></h2>

			<%
				if (c.count() == 0) {
			%>

			<h1>No one has reviewed this product</h1>


			<%
				} else {
			%>

			<table class="query-table" align="left">
				<%
					while (c.hasNext()) {
								BasicDBObject bd = (BasicDBObject) c.next();
				%>

				<tr>
					<td>Product Name</td>
					<td>:</td>
					<td><%=bd.getString("productName")%></td>
				</tr>

				<tr>
					<td>Product Category</td>
					<td>:</td>
					<td><%=bd.getString("productCategory")%></td>
				</tr>

				<tr>
					<td>Product Price</td>
					<td>:</td>
					<td><%=bd.getDouble("productPrice")%></td>
				</tr>

				<tr>
					<td>Retailer</td>
					<td>:</td>
					<td><%=bd.getString("retailerName")%></td>
				</tr>

				<tr>
					<td>Zip Code</td>
					<td>:</td>
					<td><%=bd.getString("retailerZipcode")%></td>
				</tr>

				<tr>
					<td>City</td>
					<td>:</td>
					<td><%=bd.getString("retailerCity")%></td>
				</tr>

				<tr>
					<td>State</td>
					<td>:</td>
					<td><%=bd.getString("retailerState")%></td>
				</tr>

				<tr>
					<td>Product on Sale</td>
					<td>:</td>
					<td><%=bd.getString("productOnSale")%></td>
				</tr>

				<tr>
					<td>Manufacturer Name</td>
					<td>:</td>
					<td><%=bd.getString("consoleManufacturer")%></td>
				</tr>

				<tr>
					<td>Manufacturer Rebate</td>
					<td>:</td>
					<td><%=bd.getString("manufacturerRebate")%></td>
				</tr>

				<tr>
					<td>User Id</td>
					<td>:</td>
					<td><%=bd.getString("userID")%></td>
				</tr>

				<tr>
					<td>Age</td>
					<td>:</td>
					<td><%=bd.getInt("userAge")%></td>
				</tr>

				<tr>
					<td>Gender</td>
					<td>:</td>
					<td><%=bd.getString("userGender")%></td>
				</tr>

				<tr>
					<td>Occupation</td>
					<td>:</td>
					<td><%=bd.getString("userOccupation")%></td>
				</tr>

				<tr>
					<td>Review Rating</td>
					<td>:</td>
					<td><%=bd.getInt("reviewRating")%></td>
				</tr>

				<tr>
					<td>Review Date</td>
					<td>:</td>
					<td><%=bd.getString("reviewDate")%></td>
				</tr>

				<tr>
					<td>Review Text</td>
					<td>:</td>
					<td><%=bd.getString("reviewText")%></td>
				</tr>


				<%
					}
				%>
			</table>
			<%
				}
			%>

		</section>


		<div class="clear"></div>



		<div class="foo">Copyright © www.bestdeals.co.in</div>
	</div>

</body>
</html>
<%
	} else if (request.getParameter("addtocart") != null) {
		String userid = (String) request.getSession().getAttribute(
				"username");
		String productName = request.getParameter("prname");
		double productPrice = Double.parseDouble(request
				.getParameter("prprice"));
		

		MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");

		DBCollection products = mdb.getCollection("Products");
		BasicDBObject bdo = new BasicDBObject();
		bdo.put("field0", productName);
		DBCursor c = products.find(bdo);
		BasicDBObject bd = null;
		while (c.hasNext()) {
			bd = (BasicDBObject) c.next();
		}
		String imageName = bd.getString("field8");
		String manrebate = bd.getString("field6");
		String retdiscount = bd.getString("field7");
		double rebate = Double.parseDouble(manrebate);
		double discount = Double.parseDouble(retdiscount);

		productPrice = productPrice - (rebate + discount);

		double quantity = 1;
		double total = (productPrice * quantity);

		DBCollection ccart = mdb.getCollection("CustomerCart");
		BasicDBObject bdo1 = new BasicDBObject("name", "value")
				.append("userName", userid)
				.append("imageName", imageName)
				.append("productName", productName)
				.append("productPrice", productPrice)
				.append("productQuantity", quantity)
				.append("totalCost", total);

		BasicDBObject bdo2 = new BasicDBObject();
		bdo2.put("productName", productname);
		bdo2.put("userName", userid);
		DBCursor c1 = ccart.find(bdo2);
		BasicDBObject bd2 = null;
		if (c1.count() == 0) {
			ccart.insert(bdo1);
			response.sendRedirect("Cart.jsp");
		} else {
			while (c1.hasNext()) {
				bd2 = (BasicDBObject) c1.next();
			}
			double updatedQuantity = bd2.getDouble("productQuantity") + 1;
			double updatedTotal = (bd2.getDouble("productPrice") * updatedQuantity);
			BasicDBObject bdo3 = new BasicDBObject().append("$set",new BasicDBObject().append("productQuantity",updatedQuantity).append("totalCost", updatedTotal));
			BasicDBObject bdo4 = new BasicDBObject().append("userName",userid).append("productName", productname);
			ccart.update(bdo4, bdo3);
			response.sendRedirect("Cart.jsp");
		}

	}
%>