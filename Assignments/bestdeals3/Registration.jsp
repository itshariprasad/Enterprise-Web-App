<%@page import=" com.mongodb.*"%>
<%@page import="java.sql.*"%>

<%
String loginas = request.getParameter("login");
String uname = request.getParameter("email");
String upass = request.getParameter("password");
String cpass = request.getParameter("cpassword");
String username = request.getParameter("name");
Statement stmt = null;
			Connection con = null;

if(loginas.equals("customer")){
	
MongoClient mg = new MongoClient("localhost", 27017);
DB mdb = mg.getDB("Assignment3");

DBCollection cdetails = mdb.getCollection("Cdetails");

Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbname=null;
				



if(upass.equals(cpass)){
	
BasicDBObject bdo = new BasicDBObject("name", "pass").append("username", uname)
.append("password", upass);

cdetails.insert(bdo);

	
				String insertIntoCustomerRegisterQuery = "INSERT INTO registration"+"(username,email,password,repassword,registration_type)" + "VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,username);  
				ps.setString(2,uname);  
				ps.setString(3,upass);  
				ps.setString(4,cpass);  
				ps.setString(5,"customer");
						  
				ps.executeUpdate();

				out.println("<html>");
				out.println("<head><title>Account Created</title></head>");
				
				out.println("<body align='center'>Account created successfully<br>");
				
				out.println("</html>");
				response.sendRedirect("Log.jsp");
}
else{
	out.println("<html>");
	out.println("<head><title>Wrong Password</title></head>");
	out.println("<body align='center'>Passwords do not match<br>");
	out.println("<br><a href='Reg.jsp'>Back</a>");
	out.println("</html>");
}
}


	
	else if(loginas.equals("salesman")){
		MongoClient mg = new MongoClient("localhost", 27017);
		DB mdb = mg.getDB("Assignment3");

		
		
		Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbname=null;
		
		
		
		DBCollection sdetails = mdb.getCollection("Sdetails");
		if(upass.equals(cpass)){
			
		BasicDBObject bdo = new BasicDBObject("name", "pass").append("username", uname)
		.append("password", upass);

		sdetails.insert(bdo);
		
		String insertIntoCustomerRegisterQuery = "INSERT INTO registration"+"(username,email,password,repassword,registration_type)" + "VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,username);  
				ps.setString(2,uname);  
				ps.setString(3,upass);  
				ps.setString(4,cpass);    
				ps.setString(5,"salesman");
						  
				ps.executeUpdate();  
				
				out.println("<html>");
				out.println("<head><title>Account Created</title></head>");
				
				out.println("<body align='center'>Account created successfully<br>");
				
				out.println("</html>");
				response.sendRedirect("Log.jsp");
	}
		else{
			out.println("<html>");
			out.println("<head><title>Wrong Password</title></head>");
			out.println("<body align='center'>Passwords do not match<br>");
			out.println("<br><a href='Reg.jsp'>Back</a>");
			out.println("</html>");
		}
	}
	
	else if(loginas.equals("storemanager")){
	MongoClient mg = new MongoClient("localhost", 27017);
	DB mdb = mg.getDB("Assignment3");
	
	Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealsdb","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbname=null;
	

	DBCollection smdetails = mdb.getCollection("Smdetails");
	if(upass.equals(cpass)){
		
	BasicDBObject bdo = new BasicDBObject("name", "pass").append("username", uname)
	.append("password", upass);

	smdetails.insert(bdo);
	
		String insertIntoCustomerRegisterQuery = "INSERT INTO registration"+"(username,email,password,repassword,registration_type)" + "VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,username);  
				ps.setString(2,uname);  
				ps.setString(3,upass);  
				ps.setString(4,cpass);  
				ps.setString(5,"storemanager");
						  
				ps.executeUpdate();  
				
				out.println("<html>");
				out.println("<head><title>Account Created</title></head>");
				
				out.println("<body align='center'>Account created successfully<br>");
				
				out.println("</html>");
				response.sendRedirect("Log.jsp");
}
	else{
		out.println("<html>");
		out.println("<head><title>Wrong Password</title></head>");
		out.println("<body align='center'>Passwords do not match<br>");
		out.println("<br><a href='Reg.jsp'>Back</a>");
		out.println("</html>");
	}
}

%>