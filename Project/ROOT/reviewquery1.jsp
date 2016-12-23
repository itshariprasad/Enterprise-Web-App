<%@ page language="java"%>
<%@ page import="com.mongodb.*"%>
<%@ page import="java.net.UnknownHostException"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>



<!DOCTYPE HTML>
<HTML>

<HEAD>

<META name="viewport" content="width=device-width, initial-scale=1.0" />
<LINK href="css/bootstrap.min.css" rel="stylesheet"></LINK>
<LINK href="fonts/css/styles1.css" rel="stylesheet"></LINK>
<link rel="icon" type="image/gif" href="images/nature.jpg" />
<TITLE>Scarlet-Hotel</TITLE>

</HEAD>

<BODY>
<%
	String emailId = (String) session.getAttribute("user");
%>
	<HEADER>

<HEADER>

<DIV class="navbar navbar-inverse navbar-fixed-top">

<b><DIV class="container">

<H1>
<a href="home.jsp" class="navbar-brand">SCARLET HOTEL</a>
</H1>

<p>
<H4>
<p>Luxury hotels<br />
located at breathtaking locals<br />
that take your breath away.</p>
</H4>

</DIV>

<DIV class="container">

					<BUTTON class="navbar-toggle" data-toggle="collapse"
						data-target=".navHeaderCollapse">
						<SPAN class="icon-bar"></SPAN> <SPAN class="icon-bar"></SPAN> <SPAN
							class="icon-bar"></SPAN>
					</BUTTON>

					<DIV class="collapse navbar-collapse navHeaderCollapse">

						<UL class="nav navbar-nav navbar-left">

							<LI class="active"><a href="home.jsp">Home</a></LI>
							<LI><a href="deal.jsp">Daily Deals</a></LI>
<LI><a href="Gallery.jsp">Gallery</a></LI>
							<LI><a href="contactUs.jsp">Contact Us</a></LI>
						</UL>

						<UL class="nav navbar-nav navbar-left">


							<LI class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">Account<b class="caret"></b></a>

								<%
									if (emailId != null) {
								%>
								<UL class="dropdown-menu">
									<LI><a href="userinfo.jsp">User Information</a></LI>
									<LI><a href="getBookings">My Bookings</a></LI>
									<LI><a href="logOut.jsp">Logout</a></LI>
								</UL> <%
								 	} else {
								 %>
								<UL class="dropdown-menu">
									<LI><a href="#signIn" data-toggle="modal" id="signInButton">Sign In</a></LI>
								</UL> <%
								 	}
                                %></LI>





						</UL>
					</DIV>

				</DIV> </b>
		</DIV>

		<DIV class="modal fade mt" id="signIn" role="dialog">
			<DIV class="modal-dialog">
				<DIV class="modal-content">
					<form action="user">
						<DIV class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>

						</DIV>
						<DIV class="modal-body">
							<div class="form-group">
									<h3 style="color: red;font: verdana;" id="signInMessage"></h3>
							</div>
							<div class="form-group">
								<label for="emailid">Uder Email ID:</label> <input type="email"
									class="form-control" id="emailid" name="emailId" required
									placeholder="example@example.com" />
							</div>
							<div class="form-group">
								<label for="password">User Password:</label> <input type="password"
									class="form-control" id="password" name="password"
									pattern=".{4,}" required placeholder="minimum length 4" />
							</div>
						</DIV>
<H4 class="text-center">
Sign In As: <small><select name="userType">
<option value="customer">Customer</option>
		<option value="manager">Manager</option>
<option value="admin">Admin</option></select></small>
</H4>
<DIV class="modal-footer">
							<a class="mr" href="#signUp" data-toggle="modal"
								data-dismiss="modal">New to Scarlet Hotels? Sign Up</a> <input
								type="submit" class="btn btn-primary" name="signIn"
								value="LOGIN" />
						</DIV>
					</form>
				</DIV>
			</DIV>
		</DIV>

		<DIV class="modal fade mt" id="signUp" role="dialog">
			<DIV class="modal-dialog">
				<DIV class="modal-content">
					<form action="user" method="post" id="signupForm" onsubmit="return verifyPassword()">
						<DIV class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<H4 class="text-center">Customer Sign Up</H4>
						</DIV>
						<DIV class="modal-body">
							<div class="form-group">
									<h3 style="color: red;font: verdana;" id="signUpMessage"></h3>
							</div>
							<div class="form-group">
								<label for="emailid1">Email ID:</label> <input type="email"
									class="form-control" id="emailid1" name="emailId" required
									placeholder="example@example.com" />
							</div>
							<div class="form-group">
								<h3 id="errorMessage" style="color: red;font: verdana;"></h3>
							</div>
							<div class="form-group">
								<label for="name">Your Full Name:</label> <input type="text"
									class="form-control" id="name" name="fullName" required
									placeholder="First Name Last Name" />
							</div>
							<div class="form-group">
								<label for="phone">Your Phone Number:</label> <input type="number"
									class="form-control" id="phone" name="phoneNumber" placeholder="Enter phone number" />
							</div>
							<div class="form-group">
								<label for="age">Your Age:</label> <input type="number"
									class="form-control" id="age" name="age" placeholder="Enter age" />
							</div>
							<div class="form-group">
								<label for="gender">Gender:</label>
								<select class="form-control" id="gender" name="gender">
								<option value="male">Male</option>
								<option value="female">Female</option>
								</select>
							</div>
							<div class="form-group">
								<label for="occupation">Your Occupation:</label> <input type="text"
									class="form-control" id="occupation" name="occupation" placeholder="Enter occupation" />
							</div>
							<div class="form-group">
								<label for="password1">Password:</label> <input type="password"
									class="form-control" id="password1" name="password"
									pattern=".{4,}" required placeholder="minimum length 4" />
							</div>
							<div class="form-group">
								<label for="rpassword">Retype Password:</label> <input
									type="password" class="form-control" id="rpassword"
									name="rpassword" pattern=".{4,}" required
									placeholder="minimum length 4" />
							</div>
							<div class="form-group">
								<h3 id="passwordMessage" style="color: red;font: verdana;"></h3>
							</div>
						</DIV>
						<DIV class="modal-footer">
							<input type="submit" class="btn btn-primary" name="signUp" value="Sign Up" />
						</DIV>
					</form>
				</DIV>
			</DIV>
		</DIV>

	</HEADER>
    <div class="inputForm mt bdy">
	<%
		
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = new Date();
		String td = df.format(dt);
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 2);
		dt = c.getTime();
		String cod = df.format(dt);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(dt);
		c1.add(Calendar.DATE, 90);
		dt = c1.getTime();
		String md = df.format(dt);
	%>



		<DIV class="container">
		
			
				<DIV class="row row1"></DIV>

				<div class="row row1"></div>
				
					<div class="col-sm-3 col-sm-offset-2">
					<p class="h3">
						
		<%
			
			MongoClient mongo = new MongoClient("localhost", 27017);
		out.println("\n");
		out.println("\n");
		out.println("\n");
		out.println("\n");
		
			out.println("<h2>");
			out.println("Trending Feature");
			out.println("</h2>");
			out.println("<h2 style=\" padding-top: 100px; width: 700px;\">");
			
			out.println("                                                             ");
			out.println("According to Number of hotels in city in trending");
			out.println("</h2>");
		// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("ScarletHotel");
			DBCollection myReviews = db.getCollection("Hotels");
			DBObject groupFields = new BasicDBObject("_id", "$hotelName");
			groupFields.put("city",new BasicDBObject("$max","$city"));
			DBObject group = new BasicDBObject("$group",groupFields);
			DBObject sort =  new BasicDBObject();
			sort.put("city",-1);
			DBObject limit=new BasicDBObject();
			DBObject orderby=new BasicDBObject();
			orderby = new BasicDBObject("$sort",sort);
			limit = new BasicDBObject("$limit",5);
			com.mongodb.AggregationOutput aggregate = myReviews.aggregate(group,orderby,limit);
			for (DBObject result : aggregate.results()) 
			{
				BasicDBObject bobj = (BasicDBObject) result;
				
				out.println("<html>");
				out.println("<head>");
				out.println("<table>");
				out.println("<th>");
				out.println("</h3>");
				int i;
				
				
				out.println(bobj.getString("city"));out.println("</h1>");
				out.println("------>");
				out.println("\n");
				out.println("\n");
				out.println("\n");
				out.println("</td>");
				out.println(bobj.getString("_id"));
				out.println("\n");
				out.println("\n");
				
			 }
		%>

		<%
		MongoClient mongo1 = new MongoClient("localhost", 27017);
		out.println("\n");
		out.println("\n");
		out.println("\n");
		out.println("\n");
		
			out.println("<h2>");
			out.println("According to Number of features in city in trending");
			out.println("</h2>");
		// if database doesn't exists, MongoDB will create it for you
			DB db1 = mongo.getDB("ScarletHotel");
			DBCollection myReviews1 = db.getCollection("Hotels");
			DBObject groupFields1 = new BasicDBObject("_id", "$hotelName");
			groupFields.put("features",new BasicDBObject("$max","$features"));
			DBObject group1 = new BasicDBObject("$group",groupFields);
			DBObject sort1 =  new BasicDBObject();
			sort1.put("features",-1);
			DBObject limit1=new BasicDBObject();
			DBObject orderby1=new BasicDBObject();
			orderby1 = new BasicDBObject("$sort",sort1);
			limit1 = new BasicDBObject("$limit",5);
			com.mongodb.AggregationOutput aggregate1 = myReviews.aggregate(group1,orderby1,limit1);
			for (DBObject result1 : aggregate1.results()) 
			{
				BasicDBObject bobj = (BasicDBObject) result1;
				
				out.println("<html>");
				out.println("<head>");
				out.println("<table>");
				out.println("<th>");
				out.println("</h3>");
				int i;
				
				
				out.println(bobj.getString("features"));
				out.println("</h1>");
				out.println("------>");
				out.println("\n");
				out.println("\n");
				out.println("\n");
				out.println("</td>");
				out.println(bobj.getString("_id"));
				out.println("\n");
				out.println("\n");
				
			 }
		
			
		%>


</div>
</div>
</Div>
</Div>

				</div>
				

			</form>

		</DIV>

	</div>

	<FOOTER>

		<DIV class="navbar navbar-static-bottom">

			<DIV class="container">

				<p>
						&copy;2016 <a href="#">www.ScarletHotels.com</a>
				</p>

			</DIV>

		</DIV>

	</FOOTER>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/CityNameAutoFill.js"></script>
	<script src="js/javascript.js"></script>

</BODY>
<%
	if(request.getParameter("type") != null)
	{
		if(request.getParameter("type").equals("login"))
		{
			if(request.getParameter("status").equals("false"))
			{
				%>
					<script>
						document.getElementById("signInMessage").innerHTML = "Credentials Don't Match";
						document.getElementById("signInButton").click();
					</script>
				<% 
			}
		}
		else
		{
			if(request.getParameter("status").equals("false"))
			{
				%>
					<script>
						document.getElementById("signUpMessage").innerHTML = "EmailId Already Exists";
						document.getElementById("signUpButton").click();
					</script>
				<% 
			}
		}
	}
	else
	{
		
	}
%>
</HTML>
