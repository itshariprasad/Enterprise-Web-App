<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import=" com.mongodb.*"%>

<%@ page import="Javabeans.*" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="StyleSheet.css">
<title>Salesman</title>
</head>

<header>
	<h1>
			<font face="Cambria">BEST DEALS</font>
		</h1><h2>The Best Online Retail Store You Can Get</h2>
	</header>

<nav>
	<ul>
		<li><a href="#" class="home"><font face="Cambria">HOME</font></a></li>
		<li><a href="#" class="phone"><font face="Cambria">SMART PHONES</font></a></li>
		<li><a href="#" class="tablet"><font face="Cambria">TABLETS</font></a></li>
		<li><a href="#" class="laptop"><font face="Cambria">LAPTOPS</font></a></li>
		<li><a href="#" class="tv"><font face="Cambria">TV</font></a></li>
	</ul>
	<a href="Log.jsp">SIGN OUT</a> <a href="#">WELCOME, SALESMAN</a>

</nav>

<body class="index">

	<section class="cl">

		<form action="Salesman.jsp">
			<table class="query-table" align="left">
				<tr>
					<td><label>User Name:</label></td>

					<td><input type="text" name="name" required="required"></td>
				</tr>
				<tr>
					<td><label>Email Id:</label></td>

					<td><input type="email" name="email" required="required"></td>
				</tr>
				<tr>
					<td><label>New Password:</label></td>

					<td><input type="password" name="password" required="required"></td>
				</tr>
				<tr>
					<td><label>Confirm Password:</label></td>

					<td><input type="password" name="cpassword"
						required="required"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Create Account"
						name="createaccount"></td>
				</tr>
			</table>
		</form>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		<form action="Salesman.jsp">
			<table class="query-table" align="left">
				<tr>
					<td><label>User Email Id:</label></td>

					<td><input type="email" name="email" required="required"></td>
				</tr>

				<tr>
					<td><label>Product Name:</label></td>
					<td><select name="productnamedropdown">
							<%
							MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");	

					 DBCollection products = mdb.getCollection("Products"); 
							
												BasicDBObject bdbo1 = new BasicDBObject();
												DBCursor dbc1 = products.find(bdbo1);
												if (dbc1.count() == 0) {
							%>

							<option value="noproducts">No Products</option>

							<%
								} else {
													while (dbc1.hasNext()) {

														BasicDBObject bdc1 = (BasicDBObject) dbc1
																.next();
							%>

							<option value="<%=bdc1.getString("field0")%>"><%=bdc1.getString("field0")%></option>


							<%
								}
												}
							%>
					</select></td>
				</tr>

				<tr>
					<td><label>Quantity:</label></td>

					<td><input type="number" name="quantity" required="required" min="0"></td>
				</tr>


				<tr>
					<td><input type="submit" value="Add Order" name="addorder"></td>
				</tr>
			</table>
		</form>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		<form action="Salesman.jsp">
			<table class="query-table" align="left">

				<tr>
					<td><label>User Email Id:</label></td>

					<td><input type="email" name="email" required="required"></td>
				</tr>

				<tr>
					<td><label>Order Id:</label></td>

					<td><input type="text" name="orderid" required="required"></td>
				</tr>

				<tr>
					<td><label>Product Name:</label></td>
					<td><select name="productnamedropdown">
							<%
								BasicDBObject bdbo2 = new BasicDBObject();
												DBCursor dbc2 = products.find(bdbo2);
												if (dbc2.count() == 0) {
							%>

							<option value="noproducts">No Products</option>

							<%
								} else {
													while (dbc2.hasNext()) {

														BasicDBObject bdc2 = (BasicDBObject) dbc2
																.next();
							%>

							<option value="<%=bdc2.getString("field0")%>"><%=bdc2.getString("field0")%></option>


							<%
								}
												}
							%>
					</select></td>
				</tr>

				<tr>
					<td><input type="submit" value="Delete Order" name="delorder"></td>
				</tr>
			</table>
		</form>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br>
		
		<form action="Viewallorders.jsp">
			<table class="query-table" align="left">

				<tr>
					<td><label>Customer Orders   </label><input type="submit" value="View All Orders" name="vieworder"></td>
				</tr>
			</table>
		</form>

		<br> <br>
		<br> <br>
		<form action="Salesman.jsp">
			<table class="query-table" align="left">

				<tr>
					<td><label>User Email Id:</label></td>

					<td><input type="email" name="email" required="required"></td>
				</tr>

				<tr>
					<td><label>Order Id:</label></td>

					<td><input type="text" name="orderid" required="required"></td>
				</tr>

				<tr>
					<td><label>Product Name:</label></td>
					<td><select name="productnamedropdown">
							<%
								BasicDBObject bdbo3 = new BasicDBObject();
												DBCursor dbc3 = products.find(bdbo3);
												if (dbc3.count() == 0) {
							%>

							<option value="noproducts">No Products</option>

							<%
								} else {
													while (dbc3.hasNext()) {

														BasicDBObject bdc3 = (BasicDBObject) dbc3
																.next();
							%>

							<option value="<%=bdc3.getString("field0")%>"><%=bdc3.getString("field0")%></option>


							<%
								}
												}
							%>
					</select></td>
				</tr>

				<tr>
					<td><label>Product Price:</label></td>

					<td><input type="number" name="prprice" required="required" step="any" min="0"></td>
				</tr>

				<tr>
					<td><label>Quantity:</label></td>

					<td><input type="number" name="quantity" required="required" min="0"></td>
				</tr>

				<tr>
					<td><input type="submit" value="Update Order" name="uporder"></td>
				</tr>
			</table>
		</form>

	</section>

</body>
<footer class="foot"> Copyright &copy; www.bestdeals.co.in </footer>
</html>