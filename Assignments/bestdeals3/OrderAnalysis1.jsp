
<%@page import= "java.io.IOException"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.*"%>

<%		
		Map<String,String> nameStruct = new HashMap<String,String>();
		
		nameStruct.put("ALL_PRODUCTS","0");
		nameStruct.put("IPhone 7","800");
		nameStruct.put("Samsung S7 Edge","700");
		nameStruct.put("Nexus 7","600");
		nameStruct.put("One plus 2","550");
		nameStruct.put("IPad air 2","600");
		nameStruct.put("Samsung Galaxy Tab 4","550");
		nameStruct.put("Nexus Tab 9","599");
		nameStruct.put("Lenovo Tab 2","499");
		nameStruct.put("Apple MacBook Pro","1700");
		nameStruct.put("Samsung Chromebook 3","1300.99");
		nameStruct.put("Dell Inspiron i7","700");
		nameStruct.put("Asus ZenBook","899");
		nameStruct.put("LG 4K Ultra HD Smart LED TV","3000");
		nameStruct.put("Samsung Curved 4K Ultra HD Smart LED TV","3999");
		nameStruct.put("TCL 4K Ultra HD Roku Smart LED TV","2899");
		nameStruct.put("Dolby Philips Home 3K Speaker","550");
		nameStruct.put("Ligthening Fast Charger","50");
		nameStruct.put("Blu-ray player 3D","349.99");
%>
		<html><head><link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>Trending</title></head> 
				   <body><header>

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
				   <div id='contain'> 
				   <section id='desc'> 
				   <form method='post' action='Trending.jsp'> 
				   <table class='query-table'> 
				   <tr><th colspan='4'><b> Grouping </b></th></tr> 
				
				   <tr><td> 
				   Top 5 Most Liked</td><td colspan='3'> 
				   </select></td></tr> 
				
				   <tr><td> 
				   Top 5 zip-codes where maximum number of products sold</td><td colspan='3'> 
				  </td></tr> 
				
				   <tr><td> 
				   Top 5 most sold products regardless of the rating</td><td colspan='3'> 
				  </td></tr> 
				
				   <td colspan='2'><input type='submit' name='trending' value='Trending'/></td> 
				   </tr></table></form></section> 
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
