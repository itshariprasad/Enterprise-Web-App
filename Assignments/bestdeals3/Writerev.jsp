<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.MongoException"%>
<%@page import=" com.mongodb.*"%>

<%
	try {
		String productname = request.getParameter("pmodel");
		String productcat = request.getParameter("pcategory");
		double productprice = Double.parseDouble(request
				.getParameter("pprice"));
		String retailername = request.getParameter("retailer");
		String retailerzip = request.getParameter("rzip");
		String retailercity = request.getParameter("rcity");
		String retailerstate = request.getParameter("rstate");
		String sale = request.getParameter("pos");
		String manufacturer = request.getParameter("mname");
		String rebate = request.getParameter("mrebate");
		String id = request.getParameter("userid");
		int age = Integer.parseInt(request.getParameter("uage"));
		String sex = request.getParameter("gender");
		String occupation = request.getParameter("occu");
		int rev_rating = Integer.parseInt(request.getParameter("rating"));
		String rev_date = request.getParameter("rdate");
		String rev_text = request.getParameter("reviewt");

		MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");

		DBCollection creviews = mdb.getCollection("CustomerReviews");

		BasicDBObject bdo = new BasicDBObject("name", "value")
				.append("productName", productname)
				.append("productCategory", productcat)
				.append("productPrice", productprice)
				.append("retailerName", retailername)
				.append("retailerZipcode", retailerzip)
				.append("retailerCity", retailercity)
				.append("retailerState", retailerstate)
				.append("productOnSale", sale)
				.append("consoleManufacturer", manufacturer)
				.append("manufacturerRebate", rebate)
				.append("userID", id).append("userAge", age)
				.append("userGender", sex)
				.append("userOccupation", occupation)
				.append("reviewRating", rev_rating)
				.append("reviewDate", rev_date)
				.append("reviewText", rev_text);

		creviews.insert(bdo);
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Write Review</title>
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

			<h1 align="center">
				Thank You <br> Your Review has been successfully submitted
			</h1>
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
<%} catch (MongoException m) {
		m.printStackTrace();
	}
%>