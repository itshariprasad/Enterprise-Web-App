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


public class CustomerLoginServlet extends HttpServlet {

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
				 
				 String email = req.getParameter("email");
			String password = req.getParameter("password");
				 
				 sql = "SELECT username,email, password, registration_type FROM Registration";
				 ResultSet rs = stmt.executeQuery(sql);
				
				 // Extract data from result set
				 while(rs.next()){
						
						
						dbemail = rs.getString("email");
						dbpwd = rs.getString("password");
						dbtype = rs.getString("registration_type");
						String type = "customer";
						
						if(dbtype.equals(type) && dbemail.equals(email) && dbpwd.equals(password)){
					 HashMa.getHm().put(dbemail, dbpwd);
					 dbname = rs.getString("username");
				 }	 
						
				 }
					 
			

			
			Boolean b = HashMa.getHm().containsKey(email);

			if (b && HashMa.getHm().get(email).equals(password)) {
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
				out.println("<head><title>Login Success</title></head>");
				out.println("<body align='center'>Hello, "+dbname);
				//out.println("<br><a href='Clogin.html'>Try again</a>");
				out.println("</html>");
				
				req.getRequestDispatcher("/Home.html").include(req, res);
				 
				//resp.sendRedirect("Home.html");
				
				
			
			} else {
				out.println("<html>");
				out.println("<head><title>Invalid</title></head>");
				out.println("<body align='center'>Invalid Username/Password<br>");
				out.println("<br><a href='Clogin.html'>Try again</a>");
				out.println("</html>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
