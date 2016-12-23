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
import java.sql.*;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XMLParserSAX {

    public static void main(String[] args) {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	
	Statement stmt = null;
			Connection con = null;
    try {
		
		Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
		
		HashMap<String, List<Console>> hashmap = new HashMap<String, List<Console>>();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        MyHandler handler = new MyHandler();
        saxParser.parse(new File("ProductCatalog.xml"), handler);
        //Get Consoles list
        List<Console> empList = handler.getEmpList();
        //print Console information
		String a = null;
		
	//	System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
		
		hashmap.put("Console", empList);
		
        for(Console emp : empList){
            //System.out.println(emp);
			 a =  emp.toString();
				  String[] parts = a.split("\n");
				  
	/*
 System.out.println(parts[0]);
 System.out.println(parts[1]);
 System.out.println(parts[2]);
  System.out.println(parts[3]);
   System.out.println(parts[4]);
   System.out.println(parts[5]);
   System.out.println(parts[6]);
		*/
		
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
 
 //System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
 				 
//				  System.out.println(empList.get(0));
				  
				 
				 
				 
    
//System.out.println(hashmap);
	
	
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}