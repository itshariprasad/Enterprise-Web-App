import java.io.*;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreMLoginServlet extends HttpServlet {
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
						String type = "storemanager";
						 
						//if(dbemail.equals(email)){
						if(dbemail.equals(email) && dbpwd.equals(password) && dbtype.equals(type)){
					 HashMaSa.getHmsa().put(email, password);
					dbname = rs.getString("username");
					 //out.println(dbname);
				 }	 
						
				 }
					 
			

			Boolean b = HashMaSa.getHmsa().containsKey(email);

			if (b && HashMaSa.getHmsa().get(email).equals(password)) {
				out.println("<HTML>\n" + "<HEAD><TITLE>" + "StoreManagerLogin"
						+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#D8D8D8\">\n"
						+ "<H1 ALIGN=\"CENTER\">" + "Welcome, "+dbname + "</H1>"
						+ "<BODY>"

						+ "<FORM action='StoreMfunctionServlet' method='get'>"
						+ "<label>Product Name:</label>"
						+ "<input type='text' name ='pname'>" + "<br><br>"
						+ "<label>Product Price:</label>"
						+ "<input type='text' name ='pprice'>" + "<br><br>"
						+ "<input type = 'submit' value='Add' name='add'>"
						+ "</FORM>" + "<BODY>" + "<HTML>" + "<br><br><br>"

						+ "<FORM action='StoreMfunctionServlet' method='get'>"
						+ "<label>Product Name:</label>"
						+ "<input type='text' name ='pname'>" + "<br><br><br>"
						+ "<input type = 'submit' value='Delete' name='del'>"
						+ "</FORM>" + "<BODY>" + "<HTML>" + "<br><br>"

						+ "<FORM action='StoreMfunctionServlet' method='get'>"
						+ "<label>Product Name:</label>"
						+ "<input type='text' name ='pname'>" + "<br><br><br>"
						+ "<label>Updated Product Price:</label>"
						+ "<input type='text' name ='pprice'>" + "<br><br>"
						+ "<input type = 'submit' value='Update' name='upd'>"
						+ "</FORM>" + "<br><br><br><br>"
						+"<a href='/bestdeals2/OrderAnalysis'><button>Data Analystics</button></a>"
						+ "<br><br><br><br>"
						+ "<a href='Index.html'><button>Sign Out</button></a>"
						+ "<BODY>" + "<HTML>");

			} else {
				out.println("<html>");
				out.println("<head><title>Invalid</title></head>");
				out.println("<body align='center'>Invalid Username/Password<br>");
				out.println("<br><a href='Smlogin.html'>Try again</a>");
				out.println("</html>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
