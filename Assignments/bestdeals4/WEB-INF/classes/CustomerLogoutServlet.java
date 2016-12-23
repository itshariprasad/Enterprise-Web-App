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


public class CustomerLogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();
			Statement stmt = null;
			Connection con = null;
			
		try {
			/*
			Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				
				  String sql = "TRUNCATE Products";
    // Execute deletion
    stmt.executeUpdate(sql);
				*/
				
			req.getRequestDispatcher("/Index.html").include(req, res);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
