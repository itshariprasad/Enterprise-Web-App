import java.util.HashMap;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {

    //public static Map<String,UserClass> users = new HashMap<String,UserClass>();
    customer user=new customer();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	   user.selectUser();
	   response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        
	   
        String userid =  request.getParameter("emailId");
		String name = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phoneNumber");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");
        String password1 = request.getParameter("password1");
        String repassword = request.getParameter("rpassword");
        user.insertUser(userid, name, phone, age, gender, occupation, password1, repassword, request,response);
        
       
}

protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
		out.println("<li class='end'><a href=/webapps1/log.html'> Home Page! Welcom to our world</a></li>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }



protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
