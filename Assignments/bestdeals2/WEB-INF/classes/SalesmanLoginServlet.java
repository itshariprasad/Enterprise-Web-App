import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SalesmanLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();
			Statement stmt = null;
			Connection con = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbemail=null;
				 String dbpwd = null;
				 String dbname= null;
				 String dbtype = null;
				 
				 String email = req.getParameter("usermail");
			String password = req.getParameter("password");
				 
				 sql = "SELECT username,email, password, registration_type FROM Registration";
				 ResultSet rs = stmt.executeQuery(sql);
				
				 // Extract data from result set
				 while(rs.next()){
						
						
						dbemail = rs.getString("email");
						dbpwd = rs.getString("password");
						dbtype = rs.getString("registration_type");
						String type = "salesman";
						 
						if(dbemail.equals(email) && dbpwd.equals(password) && dbtype.equals(type)){
					 HashMaSt.getHmst().put(email, password);
					dbname = rs.getString("username");
				 }	 
						
				 }
					 
			

			
			Boolean b = HashMaSt.getHmst().containsKey(email);

			if (b && HashMaSt.getHmst().get(email).equals(password)) {
				req.getSession().setAttribute("email", email);
				
				HashPname.getPn().clear();
				HashPprice.getPp().clear();
				HashPname.getLn().clear();
				HashPprice.getLp().clear();
				UpdateOrderServlet.m2.clear();
				UpdateOrderServlet.l2.clear();
				UpdateOrderServlet.m3.clear();
				UpdateOrderServlet.l3.clear();
				out.println("<html>");
				out.println("<head><title>Salesman Logged in </title></head>");
				out.println("<body align='center'>Salesman Logged in Succesfully!! Welcome, "+dbname);
				out.println("</html>");
				
				req.getRequestDispatcher("/Home1.html").include(req, res);
				
				
			
			} else {
				out.println("<html>");
				out.println("<head><title>Invalid</title></head>");
				out.println("<body align='center'>Invalid Username/Password<br>");
				out.println("<br><a href='Rlogin.html'>Try again</a>");
				out.println("</html>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
