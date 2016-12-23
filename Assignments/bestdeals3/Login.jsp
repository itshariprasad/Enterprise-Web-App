<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import=" com.mongodb.*"%>

<%@ page import="Javabeans.*" %>

<%
	String loginas = request.getParameter("login");
	String uname = request.getParameter("email");
	String upass = request.getParameter("password");
	request.getSession().setAttribute("username", uname);
	

	MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");	

	
	try {
		if (loginas.equals("customer")) {

		DBCollection cdetails = mdb.getCollection("Cdetails"); 
		
			
		
			BasicDBObject bdo = new BasicDBObject();

			bdo.put("username", uname);
			DBCursor c = cdetails.find(bdo);
			BasicDBObject bd = null;

			if (c.count() == 0) {
				out.println("<html>");
				out.println("<head><title>Invalid</title></head>");
				out.println("<body align='center'>Invalid Username/Password<br>");
				out.println("<br><a href='Log.jsp'>Try again</a>");
				out.println("</html>");
			} else {
				while (c.hasNext()) {
					bd = (BasicDBObject) c.next();
				}

				String Username = bd.getString("username");
				String Password = bd.getString("password");

				if (Username.equals(uname) && Password.equals(upass)) {
					response.sendRedirect("Custhome.jsp");
%>

<%
	}

				else {
					out.println("<html>");
					out.println("<head><title>Invalid</title></head>");
					out.println("<body align='center'>Invalid Username/Password<br>");
					out.println("<br><a href='Log.jsp'>Try again</a>");
					out.println("</html>");
				}
			}
		}

		else if (loginas.equals("storemanager")) {

			DBCollection smdetails = mdb.getCollection("Smdetails");  
				
			BasicDBObject bdo = new BasicDBObject();

			bdo.put("username", uname);
			DBCursor c = smdetails.find(bdo);
			BasicDBObject bd = null;

			if (c.count() == 0) {
				out.println("<html>");
				out.println("<head><title>Invalid</title></head>");
				out.println("<body align='center'>Invalid Username/Password<br>");
				out.println("<br><a href='Log.jsp'>Try again</a>");
				out.println("</html>");
			} else {
				while (c.hasNext()) {
					bd = (BasicDBObject) c.next();
				}

				String Username = bd.getString("username");
				String Password = bd.getString("password");

				if (Username.equals(uname) && Password.equals(upass)) {
					response.sendRedirect("Managerhome.jsp");
%>


<%
	}

				else {
					out.println("<html>");
					out.println("<head><title>Invalid</title></head>");
					out.println("<body align='center'>Invalid Username/Password<br>");
					out.println("<br><a href='Log.jsp'>Try again</a>");
					out.println("</html>");
				}
			}
		}

		else if (loginas.equals("salesman")) {

			 DBCollection sdetails = mdb.getCollection("Sdetails");
			
			BasicDBObject bdo = new BasicDBObject();

			bdo.put("username", uname);
			DBCursor c = sdetails.find(bdo);
			BasicDBObject bd = null;

			if (c.count() == 0) {
				out.println("<html>");
				out.println("<head><title>Invalid</title></head>");
				out.println("<body align='center'>Invalid Username/Password<br>");
				out.println("<br><a href='Log.jsp'>Try again</a>");
				out.println("</html>");
			} else {
				while (c.hasNext()) {
					bd = (BasicDBObject) c.next();
				}

				String Username = bd.getString("username");
				String Password = bd.getString("password");

				if (Username.equals(uname) && Password.equals(upass)) {
					response.sendRedirect("Salesmanhome.jsp");
%>


<%
	}

				else {
					out.println("<html>");
					out.println("<head><title>Invalid</title></head>");
					out.println("<body align='center'>Invalid Username/Password<br>");
					out.println("<br><a href='Log.jsp'>Try again</a>");
					out.println("</html>");
				}
			}
		}
	} catch (Exception e) {
			e.printStackTrace();
		}	
%>

