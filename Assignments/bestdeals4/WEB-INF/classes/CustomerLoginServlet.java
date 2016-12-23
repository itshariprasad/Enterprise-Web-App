import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.HashMap; 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Map;
import java.util.*;
import org.xml.sax.SAXException;
import java.sql.*;
import java.io.*;

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
			
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			Statement stmt1 = null;
			Connection con1 = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbemail=null;
				 String dbpwd = null;
				
				 String dbtype = null;
				 
				
				 
				 
				 stmt1 = con.createStatement();
				 String sql1 = "SELECT id FROM Products";
				 ResultSet rs1 = stmt1.executeQuery(sql1);
				 
				 if(!rs1.next()){
					 
					 
					HashMap<String, List<Console>> hashmap = new HashMap<String, List<Console>>();
					
					
					
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandler handler = new MyHandler();
		
		
		
        saxParser.parse(new File("/apache-tomcat-7.0.34/webapps/bestdeals4/WEB-INF/classes/ProductCatalog.xml"), handler);
        //Get Consoles list
		
		
        List<Console> empList = handler.getEmpList();
        //print Console information
		String a = null;
		
		 String[] parts = null;
        for(Console emp : empList){
            //System.out.println(emp);
			 a =  emp.toString();
				  parts = a.split("\n");

		
		
		
String insertIntoCustomerRegisterQuery = "INSERT INTO Products"+"(id,pname,pic,retailer,conditions,productType,Totalprice)" + "VALUES (?,?,?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,parts[1]);  
				ps.setString(2,parts[1]);  
				ps.setString(3,parts[4]);  
				ps.setString(4,parts[2]);  
				ps.setString(5,parts[5]);
				ps.setString(6,parts[6]);
				ps.setString(7,parts[3]);
						  
				ps.executeUpdate();  

		
		} 
		
		HashMaSax.getHmsax().put("Console", parts);
					 
				 }
				 else{
					 //DO nothing
				 }
				 
				 
				 
				 String email = req.getParameter("email");
			String password = req.getParameter("password");
				 
				 String cusername = req.getParameter("name");
				 
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
					//dbname = rs.getString("username");
				 }	 
						
				 }
					 
			req.setAttribute("name",cusername);

			
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
				out.println("<body align='center'>Hello, "+cusername);
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
