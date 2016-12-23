
<%@page import="java.io.IOException" %>

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

		<html><head><link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>Data Analytics</title></head>
				  <header>
	<h1>
			<font face="Cambria">BEST DEALS</font>
		</h1>
		<h2>The Best Online Retail Store You Can Get</h2>
	</header>


<nav>
	<ul>
		<li><a href="Managerhome.jsp" class="home"><font face="Cambria">HOME</font></a></li>
		<li><a href="#" class="phone"><font face="Cambria">SMART PHONES</font></a></li>
		<li><a href="#" class="tablet"><font face="Cambria">TABLETS</font></a></li>
		<li><a href="#" class="laptop"><font face="Cambria">LAPTOPS</font></a></li>
		<li><a href="#" class="tv"><font face="Cambria">TV</font></a></li>
	</ul>
	<a href="Log.jsp">SIGN OUT</a> <a href="#">WELCOME</a>

</nav>
		 <body>		
				   <div id='contain'> 
				   <section id='desc'> 
				   <form method='post' action='FetchData.jsp'> 
				   <table class='query-table'> 
				   <tr><th colspan='4'><b> Simple Search </b></th></tr> 
				   <tr><td><input type='checkbox' name='queryCheckBox'value='productName' checked> Select</td> 
				   <td>Product Name:</td> 
				   <td><select name='productName'> 
				   	<option value='ALL_PRODUCTS'>All Products</option> 
				   	<option value='IPhone 7'>IPhone 7</option> 
				   	<option value='Samsung S7 Edge'>Samsung S7 Edge</option> 
				   	<option value='Nexus 7'>Nexus 7</option> 
				   	<option value='One plus 2'>One plus 2</option> 
				   	<option value='IPad air 2'>IPad air 2</option> 
				   	<option value='Samsung Galaxy Tab 4'>Samsung Galaxy Tab 4</option> 
				   	<option value='Nexus Tab 9'>Nexus Tab 9</option> 
				   	<option value='Lenovo Tab 2'>Lenovo Tab 2</option> 
				   	<option value='Apple MacBook Pro'>Apple MacBook Pro</option> 
				   	<option value='Samsung Chromebook 3'>Samsung Chromebook 3</option> 
				   	<option value='Dell Inspiron i7'>Dell Inspiron i7</option> 
				   	<option value='Asus ZenBook'>Asus ZenBook</option> 
				   	<option value='LG 4K Ultra HD Smart LED TV'>LG 4K Ultra HD Smart LED TV</option> 
				   	<option value='Samsung Curved 4K Ultra HD Smart LED TV'>Samsung Curved 4K Ultra HD Smart LED TV</option> 
				   	<option value='TCL 4K Ultra HD Roku Smart LED TV'>TCL 4K Ultra HD Roku Smart LED TV</option> 
				   	<option value='Dolby Philips Home 3K Speaker'>Dolby Philips Home 3K Speaker</option> 
				   	<option value='Ligthening Fast Charger'>Ligthening Fast Charger</option> 
				   	<option value='Blu-ray player 3D'>Blu-ray player 3D</option> 
				   </select></td><td></td></tr> 
				   <tr><td><input type='checkbox' name='queryCheckBox' 
				   value='productCategory'> Select</td> 
				   <td>Product Category:</td> 
				   <td><select name='productCategory'> 
				   	<option value='All_C'>All</option> 
				   	<option value='Phone'>Smart Phones</option> 
				   	<option value='Tablet'>Tablets</option> 
				   <option value='Laptop'>Laptops</option> 
				   <option value='Tv'>Tvs</option> 
				   <option value='Accessories'>Accessories</option> 
				   </select></td> 
				   <td></td> 
				   </tr> 
				   <tr><td><input type='checkbox' name='queryCheckBox' 
				   value='productPrice'> Select</td> 
				   <td>Product Price:</td> 
				   <td><input type='number' name='productPrice' value='0' size=10 /></td> 
				   <td><input type='radio' name='comparePrice' value='EQUALS_TO' 
				   checked> Equals <br> <input type='radio' 
				   name='comparePrice' value='GREATER_THAN'> Greater Than <br> 
				   <input type='radio' name='comparePrice' value='LESS_THAN'> 
				   Less Than</td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='retailerName'> Select</td> 
				   <td>Retailer:</td> 
				   <td><input type='text' name='retailerName'></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='retailerZipcode'> Select</td> 
				   <td>Retailer Zip code:</td> 
				   <td><input type='number' name='retailerZipcode' 
				   size=10 /></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='retailerCity'> Select</td> 
				   <td>Retailer City:</td> 
				   <td><input type='text' name='retailerCity' value='All'/></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='retailerState'> Select</td> 
				   <td>Retailer State:</td> 
				   <td><input type='text' name='retailerState' /></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' value='pos'> 
				   Select</td> 
				   <td>Product On Sale:</td> 
				   <td><input type='radio' name='pos' value='Yes'>Yes 
				   <input type='radio' name='pos' value='No'>No</td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' value='mname'> 
				   Select</td> 
				   <td>Manufacturer Name:</td> 
				   <td><select name='mname'> 
				   <option value='Apple'>Apple</option> 
				   <option value='Sony'>Sony</option> 
				   <option value='Samsung'>Samsung</option> 
					   <option value='Nexus'>Nexus</option> 
				   <option value='LG'>LG</option> 
				   <option value='Dell'>Dell</option> 
				   <option value='OnePlus'>OnePlus</option> 
				   <option value='Lenovo'>Lenovo</option> 
				   <option value='Asus'>Asus</option> 
				   <option value='TCL'>TCL</option> 
				   <option value='Philips'>Philips</option> 
				   <option value='Panosonic'>Panosonic</option> 
				   </select></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' value='mrebate'> 
				   Select</td> 
				   <td>Manufacturer Rebate:</td> 
				   <td><input type='radio' name='mrebate' value='Yes'>Yes 
				   <input type='radio' name='mrebate' value='No'>No</td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='userid'> Select</td> 
				   <td>User Id:</td> 
				   <td><input type='text' name='userid' /></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='uage'> Select</td> 
				   <td>Age:</td> 
				   <td><input type='number' name='uage' 
				   size=10 /></td> 
				   <td><input type='radio' name='compareAge' value='EQUALS' 
				   checked> Equals <br> <input type='radio' 
				   name='compareAge' value='GREATER'> Greater Than <br> 
				   <input type='radio' name='compareAge' value='LESS'> 
				   Less Than</td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' value='gender'> 
				   Select</td> 
				   <td>Gender:</td> 
				   <td><input type='radio' name='gender' value='Male'>Male 
				   <input type='radio' name='gender' value='Female'>Female</td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='occu'> Select</td> 
				   <td>Occupation:</td> 
				   <td><input type='text' name='occu' /></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' 
				   value='reviewRating'> Select</td> 
				   <td>Review Rating:</td> 
				   <td><select name='reviewRating'> 
				   <option value='1' selected>1</option> 
				   <option value='2'>2</option> 
				   <option value='3'>3</option> 
				   <option value='4'>4</option> 
				   <option value='5'>5</option> 
				   </select></td> 
				   <td><input type='radio' name='compareRating' 
				   value='EQUALS_TO' checked> Equals <br> 
				   <input type='radio' name='compareRating' value='GREATER_THAN'> 
				   Greater Than</td> 
				   </tr> 
				   <tr> 
				   <td><input type='checkbox' name='queryCheckBox' value='rdate'> 
				   Select</td> 
				   <td>Date:</td> 
				   <td><input type='date' name='rdate' /></td> 
				   <td></td> 
				   </tr> 
				   <tr> 
				   <td>Return:</td> 
				   <td colspan='4'><select name='returnValue'> 
				   <option value='ALL' selected>All</option> 
				   <option value='TOP_5'>Top 5</option> 
				   <option value='TOP_10'>Top 10</option> 
				   <option value='LATEST_5'>Latest 5</option> 
				   <option value='LATEST_10'>Latest 10</option> 
				   </select></td> 
				   </tr> 
				   <tr><th colspan='4'><b> Grouping </b></th></tr> 
				   <tr><td><input type='checkbox' name='extraSettings'value='GROUP_BY'>  
				   Group By</td><td colspan='3'> 
				   <select name='groupByDropdown'> 
				   <option value='retailerCity' selected>City</option> 
				   <option value='productName'>Product Name</option> 
				   <option value='productCategory'>Product Category</option> 
				   <option value='productPrice'>Product Price</option> 
				   <option value='retailerName'>Retailer</option> 
				   <option value='retailerZipcode'>Zip Code</option> 
				   <option value='retailerState'>State</option> 
				   <option value='productOnSale'>Product On Sale</option> 
				   <option value='consoleManufacturer'>Manufacturer Name</option> 
				   <option value='manufacturerRebate'>Manufacturer Rebate</option> 
				   <option value='userID'>User Id</option> 
				   <option value='userAge'>Age</option> 
				   <option value='userGender'>Gender</option> 
				   <option value='userOccupation'>Occupation</option> 
				   <option value='reviewRating'>Rating</option> 
				   <option value='reviewDate'>Date</option> 
				   </select></td></tr> 
				   <tr><td><input type='checkbox' name='extraSettings' value='HIGH_PRICE'>  
				   Highest Price</td><td colspan='3'> 
				   <select name='highpriceDropdown'> 
				   <option value='HIGH_BY_CITY' selected>City</option> 
				   <option value='HIGH_BY_ZIP'>Zip Code</option> 
				   </select></td></tr> 
				   <tr><td><input type='checkbox' name='extraSettings' value='TOP_LIKED'>  
				   Top 5 Liked</td><td colspan='3'> 
				   <select name='top5Dropdown'> 
				   <option value='TOP5_BY_CITY' selected>City</option> 
				   </select></td></tr> 
				   <tr><td><input type='checkbox' name='extraSettings' value='TOP_RATED'>  
				   Rating 5</td><td colspan='3'> 
				   <select name='rating5Dropdown'> 
				   <option value='RATING5_BY_CITY' selected>City</option> 
				   </select></td></tr> 
				   <tr><td><input type='checkbox' name='extraSettings' value='MEDIAN'>  
				   Median</td><td colspan='3'> 
				   <select name='medianDropdown'> 
				   <option value='MEDIAN_BY_CITY' selected>City</option> 
				   </select></td></tr> 
				   <tr><td><input type='checkbox' name='extraSettings' value='ADVANCED'>  
				   Advanced Search</td><td colspan='3'> 
				   <input type='text' name='advances'> 
				   </td></tr> 
				   <tr><td colspan='2'><input type='submit' name='find' value='Find Data'/></td> 
				   <td colspan='2'><input type='submit' name='trending' value='Trending'/></td> 
				   </tr></table></form>
				  
				   </section> 
				   
				   
				
				   <div class='clear'></div> 
				   <div class='foo'> <p>Copyright � www.bestdeals.co.in</p> </div></div></body></html>