import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreMSignupServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");  
			PrintWriter out = res.getWriter();  
          
			String username = req.getParameter("name");
			String useremail = req.getParameter("email");
			String userpassword = req.getParameter("password");
			String userpassword_confirm = req.getParameter("cpassword");
			Statement stmt = null;
			Connection con = null;
			
			try{  
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 String dbname=null;
				 sql = "SELECT username FROM Registration";
				 ResultSet rs = stmt.executeQuery(sql);
				
				 // Extract data from result set
				 while(rs.next()){
					//Retrieve by column name
					
					dbname = rs.getString("username");
					 
					if(dbname.equals(username)){
					 out.println("<html>");
				out.println("<head><title>Incorrect UserName</title></head>");
				out.println("<body align='center'>User Name already used<br>");
				out.println("</html>");
				
				req.getRequestDispatcher("/Smreg.html").include(req, res);
				return;
				 }
				 }
				 
				
				if (userpassword_confirm.equals(userpassword)) {

				String insertIntoCustomerRegisterQuery = "INSERT INTO registration"+"(username,email,password,repassword,registration_type)" + "VALUES (?,?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);
				  
				ps.setString(1,username);  
				ps.setString(2,useremail);  
				ps.setString(3,userpassword);  
				ps.setString(4,userpassword_confirm);  
				ps.setString(5,"storemanager");
						  
				ps.executeUpdate();  
				
				out.println("<html>");
				out.println("<head><title>Account Created</title></head>");
				
				out.println("<body align='center'>Account created successfully<br>");
				
				out.println("</html>");
				req.getRequestDispatcher("/Smlogin.html").include(req, res);
				  
			} 
		
				else {
				out.println("<html>");
				out.println("<head><title>Incorrect Password</title></head>");
				out.println("<body align='center'>Passwords do not match<br>");
				out.println("</html>");
				
				req.getRequestDispatcher("/Smreg.html").include(req, res);
				} 
				con.close();
			}
		catch (Exception e) {
			e.printStackTrace();
		}finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            con.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(con!=null)
            con.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }
						  
				out.close();
}  
  
}  
