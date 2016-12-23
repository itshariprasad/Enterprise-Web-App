<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import=" com.mongodb.*"%>

<%@ page import="Javabeans.*" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Store Manager</title>
</head>

<header>
	<h1>
			<font face="Cambria">BEST DEALS</font>
		</h1>
		<h2>The Best Online Retail Store You Can Get</h2>
	</header>


<nav>
	<ul>
		<li><a href="#" class="home"><font face="Cambria">HOME</font></a></li>
		<li><a href="#" class="phone"><font face="Cambria">SMART PHONES</font></a></li>
		<li><a href="#" class="tablet"><font face="Cambria">TABLETS</font></a></li>
		<li><a href="#" class="laptop"><font face="Cambria">LAPTOPS</font></a></li>
		<li><a href="#" class="tv"><font face="Cambria">TV</font></a></li>
	</ul>
	<a href="Log.jsp">SIGN OUT</a> <a href="#">WELCOME, MANAGER</a>

</nav>

<body class="index">

	<section class="cl">
		<form action="StoreManager.jsp" method="post"
			enctype="multipart/form-data">
			<table class="query-table" align="left">
				<tr>
					<td><label>Product Name:</label></td>

					<td><input type="text" name="pname" required="required"></td>
				</tr>
				<tr>
					<td><label>Product Price:</label></td>

					<td><input type="number" step="any" name="pprice"
						required="required" min="0"></td>
				</tr>
				<tr>
				<tr>
					<td><label>Manufacturer Name:</label></td>

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
					<td><label>Product Category:</label></td>

					<td><select name="pcat">
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
					<td><label>Product type:</label></td>

					<td><select name="ptype">
							<option value="New">New</option>
							<option value="Pre-Owned">Pre-Owned</option>

					</select></td>
				</tr>
				<tr>
					<td><label>Warranty:</label></td>

					<td><select name="warranty">
							<option value="Yes">Yes</option>
							<option value="No">No</option>

					</select></td>
				</tr>
				<tr>
					<td><label>Manufacturer Rebate:</label></td>

					<td><input type="number" name="manurebate" min="0"
						required="required" step="any"></td>
				</tr>
				<tr>
					<td><label>Retailer Discount:</label></td>

					<td><input type="number" name="rdiscount" min="0"
						required="required" step="any"></td>
				</tr>
				<tr>
					<td><label>Image:</label></td>

					<td><input type="file" name="pimage" accept="image/*"></td>
				</tr>


				<tr>
					<th><input type="submit" value="Add Product"></th>
				</tr>
			</table>


		</form>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>

		<form action="StoreManager1.jsp">
			<table class="query-table" align="left">
				<tr>
					<td><label>Product Name:</label></td>
					<td><select name="deldropdown">
							<%
							MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");	
					 DBCollection products = mdb.getCollection("Products"); 
							
												BasicDBObject bdbo = new BasicDBObject();
												DBCursor dbc = products.find(bdbo);

												if (dbc.count() == 0) {
							%>

							<option value="noProducts">No Products</option>

							<%
								}

												else {
													while (dbc.hasNext()) {

														BasicDBObject bdc = (BasicDBObject) dbc
																.next();
							%>

							<option value="<%=bdc.getString("field0")%>"><%=bdc.getString("field0")%>
							</option>

							<%
								}
												}
							%>
					</select></td>
				</tr>
				<tr>
					<th><input type="submit" value="Delete Product" name="delp"></th>
				</tr>
			</table>
		</form>

		<br> <br> <br> <br> <br> <br>
		
		<form action="OrderAnalysis.jsp">
			<table class="query-table" align="left">

				<tr>
					<td><label>Data Analystics and Trending  </label><input type="submit" value="View Analystics" name="data"></td>
				</tr>
			</table>
		</form>
		
		<br> <br> <br> 

		<form action="StoreManager1.jsp">
			<table class="query-table" align="left">
				<tr>
					<td><label>Product Name:</label></td>

					<td><select name="updropdown">
							<%
								BasicDBObject bdbo1 = new BasicDBObject();
												DBCursor dbc1 = products.find(bdbo1);

												if (dbc.count() == 0) {
							%>

							<option value="noProducts">No Products</option>

							<%
								}

												else {
													while (dbc1.hasNext()) {

														BasicDBObject bdc = (BasicDBObject) dbc1
																.next();
							%>

							<option value="<%=bdc.getString("field0")%>"><%=bdc.getString("field0")%>
							</option>

							<%
								}
												}
							%>
					</select></td>
				</tr>
				<tr>
					<td><label>Product Name:</label></td>
					<td><input type="text" name="pname" required="required">
					</td>
				</tr>
				<tr>
					<td><label>Product Price:</label></td>

					<td><input type="number" name="pprice" required="required" step="any" min="0"></td>
				</tr>

				<tr>
					<td><label>Product type:</label></td>

					<td><select name="ptype">
							<option value="New">New</option>
							<option value="Pre-Owned">Pre-Owned</option>

					</select></td>
				</tr>
				<tr>
					<td><label>Warranty:</label></td>

					<td><select name="warranty">
							<option value="Yes">Yes</option>
							<option value="No">No</option>

					</select></td>
				</tr>
				<tr>
					<td><label>Manufacturer Rebate:</label></td>

					<td><input type="number" name="manurebate" min="0"
						required="required" step="any"></td>
				</tr>
				<tr>
					<td><label>Retailer Discount:</label></td>

					<td><input type="number" name="rdiscount" min="0"
						required="required" step="any"></td>
				</tr>



				<tr>
					<th><input type="submit" value="Update Product" name="upp"></th>
				</tr>
			</table>


		</form>


	
	</section>

</body>
<footer class="foot"> Copyright&copy; www.bestdeals.co.in </footer>
</html>