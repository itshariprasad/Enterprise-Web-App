<%@ page language="java" %>
<%@ page import="com.mongodb.*"%>
<%@ page import="java.net.UnknownHostException"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="connection.*"%>
<%@ page import="java.sql.Array"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE HTML>
<HTML>

<HEAD>

<META name="viewport" content="width=device-width, initial-scale=1.0" />
<LINK href="css/bootstrap.min.css" rel="stylesheet"></LINK>

<link rel="icon" type="image/gif" href="images/thedrake.jpg" />
<TITLE>Deal Matches Hotel</TITLE>

</HEAD>

<BODY>
<%
	String emailId = (String) session.getAttribute("user");
%>
<HEADER>

		<DIV class="navbar navbar-inverse navbar-fixed-top">

			<b><DIV class="container">

					<H1>
						<a href="home.jsp" class="navbar-brand">SCARLET HOTEL</a>
					</H1>

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
									<LI><a href="profile.jsp">Profile</a></LI>
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
								<label for="emailid">Email ID:</label> <input type="email"
									class="form-control" id="emailid" name="emailId" required
									placeholder="example@example.com" />
							</div>
							<div class="form-group">
								<label for="password">Password:</label> <input type="password"
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
								<label for="name">Full Name:</label> <input type="text"
									class="form-control" id="name" name="fullName" required
									placeholder="First Name Last Name" />
							</div>
							<div class="form-group">
								<label for="phone">Phone Number:</label> <input type="number"
									class="form-control" id="phone" name="phoneNumber" placeholder="Enter phone number" />
							</div>
							<div class="form-group">
								<label for="age">Age:</label> <input type="number"
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
								<label for="occupation">Occupation:</label> <input type="text"
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
		File file = new File("/Users/harsh/Downloads/apache-tomcat-7.0.34/webapps/Root/WEB-INF/classes/deals/DealMatches.txt");
		try{
BufferedReader reader = new BufferedReader(new FileReader(file));
String line = reader.readLine();
List<String> lines = new ArrayList<String>();
while (line != null) {
     lines.add(line);
     line = reader.readLine();
}
int i=0;
MongoClient mg = new MongoClient("localhost", 27017);
				DB mdb = mg.getDB("ScarletHotel");
DBCollection myReviews2 = mdb.getCollection("Hotels");		

if(lines.size()==0){
out.println("<h2 style=\" padding-top: 100px; width: 700px;\">");

	%>
<h2 style=\' padding-top: 100px; width: 700px;\'>We beat our competitors in all aspects. Price-Match Guaranteed </h2>
				<h3>No Offers Found</h3><BR>
				<h2>Deal Matches</h2>
				<h3>No Deals Found</h3>
<% 
} else {
String findhttp = null;

Random r = new Random();

String randomLine1 = lines.get(r.nextInt(lines.size()));

String randomLine2 = lines.get(r.nextInt(lines.size()));

while((randomLine1.equals(randomLine2))) {
		randomLine2 = lines.get(r.nextInt(lines.size()));
}

String namep = null;
BasicDBObject whereQuery2 = new BasicDBObject();
    BasicDBObject fields2 = new BasicDBObject().append("_id",false);
    fields2.put("city", 1);
	
	String pname2 = null;
    DBCursor cursor2 = myReviews2.find(whereQuery2, fields2);
	
	List<String> list = new ArrayList<String>();
	while (cursor2.hasNext()) {
		    BasicDBObject obj2 = (BasicDBObject) cursor2.next();
     pname2 = obj2.getString("city");
	list.add(pname2);
	
	}
	
	for(String temp : list){
	if(randomLine1.contains(temp) ){
		

%>
<td style="vertical-align: middle;">
 <h2 style="text-align: center;"><font color="#FF0000"><b>We beat our competitors in all aspects. Price-Match Guaranteed</b> </font> </h2></td>
				<h3><%=randomLine1%></h3><br>
			<td style="vertical-align: middle;">
 <h2 style="text-align: center;"><font color="#FF0000"><b>Deal Matches</b> </font> </h2></td>
	

<%
MongoClient mongo1 = new MongoClient("localhost", 27017);

			DB db1 = mongo1.getDB("ScarletHotel");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews1 = db1.getCollection("Hotels");
			
			
			BasicDBObject whereQuery = new BasicDBObject();
    
    BasicDBObject fields = new BasicDBObject().append("_id",false);
    
	
	String   hname = null;
	String   hcity = null;
	String   himages = null;
	String   hfeatures = null;
	String   hrating = null;
	String   htotalRooms = null;
	String   hminPrice = null;
	String   hHotelId = null;
	String pname = null;
 
    DBCursor cursor = myReviews1.find(whereQuery, fields);
	int cc = 0;
	while (cursor.hasNext()) {
		    BasicDBObject obj = (BasicDBObject) cursor.next();
     hname = obj.getString("hotelName");
	 hcity = obj.getString("city");
himages = obj.getString("images");
hfeatures = obj.getString("features");
hrating = obj.getString("rating");
htotalRooms = obj.getString("totalRooms");
hminPrice = obj.getString("minPrice");
hHotelId = obj.getString("hotelId");

himages = himages.replace("\"", "").replace("[","").replace("]","");

if(hcity.equals(temp)){
	
	
%>
	<div class="bdy">
	<DIV class="sortList">
	<div class="row">
	<DIV class="col-lg-3 col-md-6 col-sm-12">
		
			<div class="row">
					<div class="col-lg-12">
			<div data-interval="false" id="myCarousel<%=i%>"
										class="carousel slide" data-ride="carousel">

										<!-- Wrapper for slides -->
										<div class="carousel-inner" role="listbox">
										
										<div class="item active">	
												<img class="smallImgSz img-responsive" src="<%=himages%>"
													alt="scarlethotel">
										
										</div>

										
										</div>

									</div>
								</div>
							</div>  	
							<!-- end of corousel -->   
		
	</DIV>
	<DIV class="col-lg-3 col-md-4">
		<H1 class="bl"><font color="#F1C40F"> <%=hname%> </font></H1>
  		<H1 class="bl"><font color="#F1C40F "><%=hcity %></font></H1>
  		<h3 class="bl"><font color="#F1C40F "> Rating: <%=hrating%>/5.0 </font></H3>  		
  	</DIV>

	<DIV class="col-lg-3 col-md-4">
		<h3 class="bl"><font color="#F1C40F  "><b>Rooms available: <%=htotalRooms %></font></b1></H3>
		<h3 class="bl"><font color="#F1C40F"> Price: $<%=hminPrice%> </font><SMALL class="bl"><font color="#F1C40F  ">per night</SMALL></font></H3>
		<br/><br/>
  		<a class="btn btn-success ml" href="reviews?hotelId=<%=hHotelId %>" >SELECT</a>
  	</DIV>
	</div>
	</DIV>
<%
}
}
}
}
}
}
		catch(Exception e){
			out.println(e);
		}
		
%>
</DIV>
</div>

<FOOTER>

	<DIV class="hidden-xs navbar navbar-static-bottom">

		<DIV class="container">

			<p>&copy;2016 <a href="#">www.ScarletHotels.com</a></p>

		</DIV>

	</DIV>
	
</FOOTER>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="js/bootstrap.js"></script>

</BODY>

</HTML>
