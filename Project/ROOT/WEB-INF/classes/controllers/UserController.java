

package controllers;

import java.io.IOException;
import beans.UserBean;
import dao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/user")
public class UserController extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session = request.getSession();
		
		String type = request.getParameter("userType");
        String emailId = request.getParameter("emailId");
        String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		String attr = "";
		if(type.equals("admin"))
		{
			if(emailId.equals("admin@scarlethotel.com"))
				if(password.equals("1234"))
				{
					session.setAttribute("user", emailId);
					response.sendRedirect("/CSPprojectTeam5/AdminPage");
					return;
				}
				else
				{
					attr = "?type=login&status=false";
					response.sendRedirect("home.jsp"+attr);
					return;
				}
			
		}
		else if(type.equals("manager"))
		{
			if(emailId.equals("manager@scarlethotel.com"))
				if(password.equals("1234"))
				{
					session.setAttribute("user", emailId);
					response.sendRedirect("/CSPprojectTeam5/GetManager");
					return;
				}
				else
				{
					attr = "?type=login&status=false";
					response.sendRedirect("home.jsp"+attr);
					return;
				}
			
		}
		
		if(userDao.checkUser(emailId, password,type))
		{
			
			attr = "?type=login&status=true";
			session.setAttribute("user", emailId);
			session.setAttribute("userObj", userDao.getUser(emailId));
			response.sendRedirect("home.jsp"+attr);
			return;
		}
		else
		{
			attr = "?type=login&status=false";
			response.sendRedirect("home.jsp"+attr);
			return;
		}
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
		UserBean username = new UserBean();
		
		username.setEmailId(request.getParameter("emailId"));
		username.setFullName(request.getParameter("fullName"));
		username.setPassword(request.getParameter("password"));
		username.setPhoneNumber(request.getParameter("phoneNumber"));
		username.setAge(Integer.parseInt(request.getParameter("age")));
		username.setGender(request.getParameter("gender"));
		username.setOccupation(request.getParameter("occupation"));
		username.setType("customer");
		
		String userid =  request.getParameter("emailId");
		String name = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phoneNumber");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String occupation = request.getParameter("occupation");
	    String password1 = request.getParameter("password");
	    String repassword = request.getParameter("rpassword");
	    //System.out.println("Helllo=====3333=========o");
		
		MySQLstoreUtilities user1=new MySQLstoreUtilities();
		user1.insertUser(userid, name, phone, age, gender, occupation, password1, repassword, request,response);
		UserDao userDao = new UserDao();
		String attr = "";
		if(userDao.findUser(username.getEmailId()))
		{
			attr = "?type=signup&status=true";
			userDao.insertUser(username);
		}
		else
		{
			attr = "?type=login&status=false";
		}
		response.sendRedirect("home.jsp"+attr);
	}
}
