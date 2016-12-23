<%@ page language="java" import="com.mongodb.*"
	import="java.net.UnknownHostException" import="java.util.*"
	import="java.text.*" import="connection.*"
	import="beans.RoomBean"
import="java.sql.Array"
import="beans.*"
import="controllers.*"
import="java.util.ArrayList"
	%>

<!DOCTYPE HTML>
<HTML>

<HEAD>

<META name="viewport" content="width=device-width, initial-scale=1.0" />
<LINK href="css/bootstrap.min.css" rel="stylesheet"></LINK>
<LINK href="fonts/css/styles.css" rel="stylesheet"></LINK>
<link rel="icon" type="image/gif" href="images/thedrake.jpg" />
<TITLE>Scarlet Hotel-Booking</TITLE>

</HEAD>

<BODY>
<%
	session.setAttribute("hotelId", Integer.parseInt(request.getParameter("hotelId")));
	session.setAttribute("roomId", Integer.parseInt(request.getParameter("roomId")));
	session.setAttribute("numOfRooms", Integer.parseInt(request.getParameter("r"+request.getParameter("roomId"))));
	String emailId = (String) session.getAttribute("user");
%>
	<HEADER>

		<DIV class="navbar navbar-inverse navbar-fixed-top">

			<DIV class="container">

					<H1>
						<b><a href="#" class="navbar-brand">SCARLET HOTEL</a></b>
					</H1>

			</DIV>
				
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
							<H4 class="text-center">
								Sign In As: <small><select name="userType">
										<option value="customer">Customer</option>
										<option value="manager">Manager</option>
										<option value="admin">Admin</option></select></small>
							</H4>
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
						<DIV class="modal-footer">
							<a class="mr" href="#signUp" data-toggle="modal"
								data-dismiss="modal">New to Scarlet-Hotel? Sign Up</a> <input
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
	<DIV class="img-responsive bdy container-fluid mt2 inputForm row1">

	
	<%
	ArrayList<HotelBean> hotels = (ArrayList<HotelBean>)session.getAttribute("hotels");
	int hotelId = Integer.parseInt(request.getParameter("hotelId"));
	HotelBean hotel = new HotelBean();
	for(HotelBean Thotel : hotels) {
		if(Thotel.getHotelId() == hotelId)
			hotel = Thotel;
	}
	%>
	
	
	
		<div class="row">
			<div class="col-sm-4 col-sm-offset-4">

				<h3>Hotel Name: <%=hotel.getHotelName() %></h3>
				
				<h3>Room Type:  <%=hotel.getRoomTypes() %></h3>
				<form action="bookRoom">
				
					<div class="form-group">
						<label for="bookingName">Booking Name: </label> <input type="text"
							class="form-control" id="bookingName" name="bookingName" required
							placeholder="Enter Booking Name" />
					</div>
					
					<div class="form-group">
						<label for="paymeth">Method of payment: </label> 
						<select class="form-control" id="paymeth" name="paymentMethod">
						<option value="credit">Credit</option>
						<option value="debit">Debit</option>
						</select>
					</div>

					<div class="form-group">
						<label for="cardid">Card Number: </label> <input type="number"
							class="form-control" id="cardid" name="cardNumber" required
							placeholder="Enter Card Number" />
					</div>

					<div class="form-group">
						<label for="cvv">CVV: </label> <input type="number"
							class="form-control" id="cvv" name="cvv" required
							placeholder="Enter CVV" />
					</div>

					<div class="form-group">
						<label for="expdt">Expiration Date: </label> <input type="month"
							class="form-control" id="expdt" name="expirationDate" required 
							/>
					</div>

					<div class="form-group">
						<label for="namecard">Name on Card: </label> <input
							type="text" class="form-control" id="namecard" name="nameOnCard"
							required placeholder="Enter Name on Card" />
					</div>

					<div class="form-group">
						<label for="addrss">Billing Address: </label>
						<input type="text" class="form-control" id="addrss" name="billingAddress" placeholder="Enter Billing Address" />
					</div>
					
					<div class="row"><div class="col-md-8">
					<input class="btn btn-warning" type="submit" name="reserveRoom"
						value="RESERVE" />
						</div>
						<div class="col-md-4">
					<a class="btn btn-warning" href="index.jsp">BACK TO HOME</a>
						</div>
						</div>

				</form>

			</div>
		</div>

	</DIV>

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
	<script src="js/javascript.js"></script>
	<script src="js/CityNameAutoFill.js"></script>
</BODY>

</HTML>
