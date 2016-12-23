<%@page import=" com.mongodb.*"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Checkout</title>
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
			<h1 align="center">Checkout</h1>

			<form action="PlaceOrder.jsp">

				<table class="query-table" align="center">
					<tr>
						<td><label>Name:</label></td>

						<td><input type="text" name="uname" required="required"></td>
					</tr>
					<tr>
						<td><label>Address:</label></td>

						<td><input type="text" name="address" required="required"></td>
					</tr>
					   <TR> 
				   <TD><label>Credit Card:</label></TD> 
				  <BR><TD>  
				 <INPUT TYPE='RADIO' NAME='cardType' VALUE='Visa'>Visa<BR> 
				<INPUT TYPE='RADIO' NAME='cardType' VALUE='MasterCard'>MasterCard<BR> 
				 <INPUT TYPE='RADIO' NAME='cardType' VALUE='Amex'>American Express<BR> 
				 <INPUT TYPE='RADIO' NAME='cardType' VALUE='Discover'>Discover<BR> 
				  </TD></TR> 
					<tr>
						<td><label>Credit Card No:</label></td>

						<td><input type="text" name="cnumber" required="required"></td>
					</tr>
					<tr>
						<td><label>Repeat Credit Card No:</label></td>

						<td><input type="text" name="rcnumber" required="required"></td>
					</tr>
					<tr>
						<td><label>CVV:</label></td>

						<td><input type="password" name="cvv" required="required"></td>
					</tr>

				</table>
				<br>
				<br>
				<br> <input type="submit" value="Place Order">
			</form>

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
