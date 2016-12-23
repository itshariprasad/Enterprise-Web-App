<%@ page language="java" import="com.mongodb.*"
	import="java.net.UnknownHostException" import="java.util.*"
	import="java.text.*" import="connection.*" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Scarlet Hotels</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script>
            $.get('/Project/Header', function(data) {
                $("#header").html(data);
            });
            $.get('/Project/Footer', function(data) {
                $("#footer").html(data);
            });
        </script>
	</head>
<body class="landing">
<%
	String emailId = (String) session.getAttribute("user");
%>

	<!-- Page Wrapper -->
			<div id="page-wrapper">

				<!-- Header -->
					<header id="header" class="alt">
						
					</header>

				<!-- Banner -->
					<section id="banner">
						<div class="inner">
							<h2>Scarlet Hotels</h2>
							<p>Luxury hotels<br />
							located at breathtaking locales<br />
							that take your breath away.</p>
						</div>
						<a href="#one" class="more scrolly">Check Availability & Sign In</a>
					</section>
					
	<!-- One -->
					<section id="one" class="wrapper style2 special">
						<div class="inner">
							<header class="major">
								<h2>Check availability at your preferred destination</h2>
							</header>
							   
					 <a href="home.jsp">Sign In/Sign Up</a>
   	<form action="getHotel">
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

		</header>
                            
                                    <div class="6u 12u$(xsmall)">
                                       <label for="checkInDate">Check In Date:</label> <input style="background-color:black;"
								type="date" class="form-control" id="checkInDate"
								name="checkInDate" value='<%=td%>' min='<%=td%>' max='<%=md%>' />
                                    </div>
                                    <div class="6u$ 12u$(xsmall)">
                                        <label for="checkOutDate">Check Out Date:</label> <input style="background-color:black;"
								type="date" class="form-control" id="checkOutDate"
								name="checkOutDate" value='<%=cod%>' />
                                    </div>
                                    
                                
					<DIV class="row row1"></DIV>			
<div class="row row1">
<div class="cmt col-sm-6 col-sm-offset-2">
<div class="form-group">
<BR>
<label for="cityName">Location City Name:</label> <input type="text"
class="form-control" id="cityName" name="cityName"
placeholder="Enter Location of City Name" onkeypress="loadJSON()"
list="cityNameList" autocomplete="off" />
<datalist id="cityNameList">
</datalist>
</div>
</div>
</div>
	<div class="row row1">							
								
                            <ul class="actions">
							<div class="col-sm-2 col-sm-offset-2">
						<button type="submit" class="btn btn-warning" name="search">Check Availability</button>
					</div>
					</div>
					
                       </form>    
					
					
					</section>
					
					<!-- About Scarlet -->
					<section id="cta" class="wrapper style4">
						<div class="inner">
							<header>
								<h2>Scarlet Hotels</h2>
								<p>Now in over 200 destinations worldwide.<br>Learn more about our chain of luxury hotels.</p>
							</header>
							<ul class="actions vertical">
								<li><a href="deal1.jsp" class="button fit special">Deals</a></li>
								<li><a href="contactUs.jsp" class="button fit">Learn More</a></li>
							</ul>
						</div>
					</section>

				<!-- Footer -->
					<footer id="footer">
				<p>
					&copy;2016 <a href="#">www.ScarletHotels.com</a>
				</p>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>



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
						document.getElementById("signInMessage").innerHTML = "Exactly doesnt match! Please Retry!";
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
