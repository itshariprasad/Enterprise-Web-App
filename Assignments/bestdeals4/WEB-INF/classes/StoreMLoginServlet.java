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


public class StoreMLoginServlet extends HttpServlet {
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
				 String dbname= null;
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
		
		
		hashmap.put("Console", empList);
		
        for(Console emp : empList){
            //System.out.println(emp);
			 a =  emp.toString();
				  String[] parts = a.split("\n");

		
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
					 
				 }
				 else{
					 //DO nothing
				 }
				 
				 




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
						+ "<input type='text' name ='pname' required='required'>" + "<br><br>"
						+ "<label>Product Retailer:</label>"
						+ "<input type='text' name ='pretailer' required='required'>" + "<br><br>"
						+ "<label>Product Condition:</label>"
						+ "<input type='text' name ='pcondition' required='required'>" + "<br><br>"
						+ "<label>Product Type:</label>"
						+ "<input type='text' name ='ptype' required='required'>" + "<br><br>"
						+ "<label>Product Price:</label>"
						+ "<input type='number' name ='pprice' min='0' required='required'>" + "<br><br>"
						+ "<input type = 'submit' value='Add' name='add'>"
						+ "</FORM>" + "<BODY>" + "<HTML>" + "<br><br><br>"

						+ "<FORM action='StoreMfunctionServlet' method='get'>"
						+ "<label>Product Name:</label>"
						+ "<input type='text' name ='pname' required='required'>" + "<br><br><br>"
						+ "<input type = 'submit' value='Delete' name='del'>"
						+ "</FORM>" + "<BODY>" + "<HTML>" + "<br><br>"

						+ "<FORM action='StoreMfunctionServlet' method='get'>"
						+ "<label>Product Name:</label>"
						+ "<input type='text' name ='pname' required='required'>" + "<br><br><br>"
						+ "<label>Updated Product Price:</label>"
						+ "<input type='text' name ='pprice' required='required'>" + "<br><br>"
						+ "<input type = 'submit' value='Update' name='upd'>"
						+ "</FORM>" + "<br><br><br><br>"
						+"<a href='/bestdeals4/OrderAnalysis'><button>Data Analystics</button></a>"
						+ "<br><br><br><br>"
						+ "<a href='/bestdeals4/CustomerLogoutServlet'><button>Sign Out</button></a>"
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
