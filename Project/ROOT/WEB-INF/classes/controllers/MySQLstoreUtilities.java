package controllers;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.sql.*;

import dao.HotelsDao;

import java.util.*;
import java.io.*;
import java.sql.*;

public class MySQLstoreUtilities extends HttpServlet {

    public static Map<String,UserClass> users = new HashMap<String,UserClass>();
    UserClass uaObj;
    Connection connection = null;
   

    public void insertUser(String userid,String name,String phone, String age, String gender, String occupation, String password1, String repassword, HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException 
    {


    	  
        uaObj = new UserClass(userid,name);
        if (users.get(userid) !=null){

             showPage(response,"User Already Exists:");
        }else{
            try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            connection = DriverManager
            		.getConnection("jdbc:mysql://localhost:3306/Bestdeal","root", "12345xyzXYZ");
            String insertIntoCustomerRegisterQuery1 = "INSERT INTO scarletlogin111(useid,password,phone,age,gender,occupation,password1,repassword) "
            + "VALUES (?,?,?,?,?,?,?,?);";
            System.out.println("Helllo==============o");
            PreparedStatement pst =
            connection.prepareStatement(insertIntoCustomerRegisterQuery1);
            
            pst.setString(1,userid);
            pst.setString(2,name);
            pst.setString(3,phone);
            pst.setString(4,age);
        
            pst.setString(5,gender);
            pst.setString(6,occupation);
            pst.setString(7,password1);
            pst.setString(8,repassword);
            boolean inserted  = pst.execute();
            System.out.println("User Value Inserted:" + inserted);
            HttpSession session = request.getSession();
            
           
        }
        catch(Exception e){
        	   System.out.println("==========="+e);
        }
           
            

        }
}
        

        private void showPage(HttpServletResponse response, String string) throws IOException 
        {
        	 response.setContentType("text/html");
             java.io.PrintWriter out = response.getWriter();
             out.println("<html>");
             out.println("<head>");
             out.println("<title>Login Servlet Result</title>");  
             out.println("</head>");
             out.println("<body>");
             out.println("<h2>" + string + "</h2>");
     		out.println("<li class='end'><a href='/webapps1/Untitled2.html'>ClickheretoSignIn</a></li>");
             out.println("</body>");
             out.println("</html>");
             out.close();
		
        }


		public boolean userAuth(String userid,String password,HttpServletRequest request, HttpServletResponse response)

        throws ServletException, java.io.IOException{

            response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        if(users.get(userid) != null){
            if(users.get(userid).equals(userid) && users.get(password).equals(password)){
            return true; 
        }
        else{
            showPage(response,"Login Failure");
            return false;
        }}
        else
        {
            showPage(response,"Username doesnot exists");
            return false;
        }
        }
    





 public void selectUser() {

try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDeal","root","12345xyzXYZ");
        String selectfromRegistration ="select * from Login";
        PreparedStatement pst =
                connection.prepareStatement(selectfromRegistration);
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            String username = rs.getString("username");
            String password = rs.getString("password");
            UserClass userObj = new UserClass(username,password);
            if(!users.containsKey(username)){
                users.put(username,userObj);
                
            }
        }
        
    
}
    catch(Exception e){
    }
 }


public void inserthotel(String hotelName, String city, String zip, String address, String url, String typeCount,
		String description, HttpServletRequest request, HttpServletResponse response) 
{
	try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        connection = DriverManager
        		.getConnection("jdbc:mysql://localhost:3306/Bestdeal","root", "12345xyzXYZ");
        String insertIntoCustomerRegisterQuery1 = "INSERT INTO scarletloginaddhotel(hotelName,city,zip,address,url,typeCount,description) "
        + "VALUES (?,?,?,?,?,?,?);";
        System.out.println("Helllo==============o");
        PreparedStatement pst =
        connection.prepareStatement(insertIntoCustomerRegisterQuery1);
        
        pst.setString(1,hotelName);
        pst.setString(2,city);
        pst.setString(3,zip);
        pst.setString(4,address);
        pst.setString(5,url);
        pst.setString(6,typeCount);
        pst.setString(7,description);
        boolean inserted  = pst.execute();
        System.out.println("Hotel Value Inserted:" + inserted);
        HttpSession session = request.getSession();
        
       
    }
    catch(Exception e){
    	   System.out.println("==========="+e);
    }
	
}
}
