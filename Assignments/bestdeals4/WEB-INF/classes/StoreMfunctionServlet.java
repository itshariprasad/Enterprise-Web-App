import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreMfunctionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter pw = res.getWriter();
		Statement stmt = null;
			Connection con = null;
		
		
		if (req.getParameter("add") != null) {
			String pname = req.getParameter("pname");
			String pprice = req.getParameter("pprice");
			
			//String ppic = req.getParameter("ppic");
			String pretailer = req.getParameter("pretailer");
			String pcondition = req.getParameter("pcondition");
			String ptype = req.getParameter("ptype");
			
			
			
			ProductStore productStore = new ProductStore();
			
			int psize = productStore.getProducts().size();
			
			
			psize = psize +1;
			String numberAsString = Integer.toString(psize);
			Products products = new Products(numberAsString, pname, pprice);
			productStore.getProducts().put(pname, products);
			HashADU.getAdu().put(pname, pprice);
			
			try {
			Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				 
				
				 String sql1 = "SELECT id FROM Products";
				 ResultSet rs1 = stmt.executeQuery(sql1);
				 int count =0;
				 while(rs1.next()){
					 count++;
				 }
				 
			
			String insertIntoCustomerRegisterQuery = "INSERT INTO Products"+"(id,pname,pic,retailer,conditions,productType,Totalprice)" + "VALUES (?,?,?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,pname);  
				ps.setString(2,pname);  
				ps.setString(3,pname+".jpg");  
				ps.setString(4,pretailer);  
				ps.setString(5,pcondition);
				ps.setString(6,ptype);
				ps.setString(7,pprice);
						  
				ps.executeUpdate();  
			
			pw.println("<HTML>" + "<title>Success</title>" + "<body>");
			pw.println("<H2 align='center'>" + "Item Added Successfully"
					+ "<br>" + "Customers can view the products by signing in"
					+ "</H2>");
					
					
			pw.println("<a href='Index.html'>"+ "Back to Home"+ "</a>");
			
			pw.println("</body>" + "</html>");
			
			System.out.println(HashADU.getAdu());
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		if (req.getParameter("del") != null) {
			String pname = req.getParameter("pname");
			
			
			ProductStore productStore = new ProductStore();
			
			int psize = productStore.getProducts().size();
			
			
			psize = psize;
			String numberAsString = Integer.toString(psize);
			
			productStore.getProducts().remove(pname);
			
			HashADU.getAdu().remove(pname);
			
			
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				PreparedStatement preparedStmt = null;
				stmt = con.createStatement();
				String sql=null;
				// String dbname=null;
				 sql = "delete FROM Products where pname =?";
				 preparedStmt = con.prepareStatement(sql);
				 preparedStmt.setString(1, pname);
				
				preparedStmt.execute();
				
			
			pw.println("<HTML>" + "<title>Success</title>" + "<body>");
			pw.println("<H2 align='center'>" + "Item Deleted Successfully"
					+ "</H2>");
					
					pw.println("<a href='Index.html'>"+ "Back to Home"+ "</a>");
			
			pw.println("</body>" + "</html>");
			System.out.println(HashADU.getAdu());
		
		 }
			
			catch (Exception e)
			{
      pw.println("Got an exception! ");
      pw.println(e.getMessage());
				}
		}

		if (req.getParameter("upd") != null) {
			String pname = req.getParameter("pname");
			String pprice = req.getParameter("pprice");

			
			
			ProductStore productStore = new ProductStore();
			
			int psize = productStore.getProducts().size();
			
			productStore.getProducts().remove(pname);
			
			psize = psize;
			String numberAsString = Integer.toString(psize);
			Products products = new Products(numberAsString, pname, pprice);
			productStore.getProducts().put(pname, products);
			
			
			HashADU.getAdu().put(pname, pprice);
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				stmt = con.createStatement();
				PreparedStatement preparedStmt = null;
				String sql10 = "UPDATE Products SET Totalprice =? where pname =?";
				  preparedStmt = con.prepareStatement(sql10);
				 preparedStmt.setString(1, pprice);
				  
				 preparedStmt.setString(2, pname);
				
				preparedStmt.execute();
			
			pw.println("<HTML>" + "<title>Success</title>" + "<body>");
			pw.println("<H2 align='center'>" + "Item Updated Successfully"
					+ "</H2>");
					
					pw.println("<a href='Index.html'>"+ "Back to Home"+ "</a>");
			
			pw.println("</body>" + "</html>");
			System.out.println(HashADU.getAdu());
		}
		
			catch (Exception e)
			{
      pw.println("Some fields are missing! Please try again");
      
				}
		}
	}
}
