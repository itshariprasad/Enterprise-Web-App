import java.io.*;
import java.awt.Color;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.util.*;


public class SigninServlet extends HttpServlet
{
  // Determines if user already exists in the list of registrants.
  private static boolean found = false;

  // Registration information will be stored here
  
 
  
  
  public void init(ServletConfig config)
  {
    
    try 
    {
       super.init(config);
       
    }
    catch(Exception e)
    {
       e.printStackTrace();
    }
    
  }

  /**
   *  Process the GET request.
   *  @param req The request.
   *  @param res The response.
   **/
   public void doGet (HttpServletRequest req, HttpServletResponse res) 
      throws ServletException, IOException
   {         
      res.setContentType("text/html");
      ServletOutputStream out = res.getOutputStream();
      
      // Display the Web using the new HTML classes
      //out.println(showHTML());
      out.close();
   }


    public void doPost (HttpServletRequest req, HttpServletResponse res)
       throws ServletException, IOException
    {
        String nameStr   = req.getParameter("name");
		String passwordStr   = req.getParameter("password");
        String emailStr  = req.getParameter("email");
        String errorText = "";

        // Output stream to write to the servlet
        ServletOutputStream out = res.getOutputStream();

        res.setContentType("text/html");
		
		
		
		 Enumeration paramNames = req.getParameterNames();
		ServletContext sc = req.getSession().getServletContext();
		  File regPath= new File(sc.getRealPath("registration.txt"));

        // Check name & e-mail parameters for valid values
        if (nameStr.length() == 0)
           errorText += "Customer Name not entered.  ";
        if (emailStr.length() == 0)
           errorText += "E-mail not entered.  " ;
	     if (passwordStr.length() == 0)
           errorText += "Password not entered.  ";
 
        // If name & e-mail have both been provided, continue.
        if (errorText.length() == 0) 
        {
           
           try 
           {  
              //Create the registration.txt file
              FileWriter f = new FileWriter(regPath, true);
              BufferedWriter output = new BufferedWriter(f);
              
              //buffered reader for searching the file
              BufferedReader in = new BufferedReader(new FileReader(regPath));
              
              String line = in.readLine();
              
              // reset the found flag
              found = false;

              // Check to see if this customer has already registered
              //  or has already used the same e-mail address
              while (!found) 
              {
                 // if file is empty or end of file reached.
                 if (line == null)   
                    break;

                 // if customer already registered
                 if ((line.equals("Customer Name: " + nameStr)) || (line.equals("Password: " + passwordStr)) ||
                     (line.equals("Email address: " + emailStr))) 
                 {
                    // Output a message to the customer saying they have
                    // already registered
                    out.println ("<HTML> " +
                                 "<TITLE> Toolbox Registration</TITLE> " +
                                 "<META HTTP-EQUIV=\"pragma\" content=\"no-cache\"> " +
                                 "<BODY BGCOLOR=\"blanchedalmond\" TEXT=\"black\"> " );
                    out.println ("<P><HR>" +
                                 "<P>" + nameStr +
                                 "</B>, you have already registered using that " +
                                 "<B>Name</B> or <B>E-mail address</B>." +
                                 "<P> Thank You!...<P><HR>");

                    // Create a HTMLHyperlink object and display it
					
					//res.getRequestDispatcher("index.html").include(request, response);
					 String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
					 out.println(docType +"<HTML>\n" +
				"<TR><h3><p align='right'><a href='validate.html'>Login/Sign up</a> &nbsp&nbsp&nbsp <a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp <a href='/bestdeals/OrderPage'>Shopping cart items </a></p></h3></TR>");
    
					
					
					req.getRequestDispatcher("/examples.html").include(req,res);
                    out.println("</UL></BODY></HTML>");
                    found = true;
                    break;

                 }
                 else    // read the next line
                    line = in.readLine();
                 
              }
              
              // String object to hold data submitted from the HTML Form
              String data;

              // If the users name or e-mail aren't found in our
              // text file, continue.
              if (!found)
              {  
                 //------------------------------------------------------------
                 // Insert the new customer info into a file
    
                 output.write(nameStr);
               
				 output.write(":" + passwordStr);
                 
				 output.write(":" + emailStr);
                 output.newLine();
                 //------------------------------------------------------------


                 //------------------------------------------------------------
                 //Getting "USE" checkbox from form
                 
                 //------------------------------------------------------------

                 
                 output.flush();
                 output.close();
                 
                 // Print a thanks to the customer
                 out.println("<HTML>");
                 out.println("<TITLE>Thank You!</TITLE>");
                 out.println("<META HTTP-EQUIV=\"pragma\" content=\"no-cache\"> ");
                 out.println("<BODY BGCOLOR=\"blanchedalmond\">");
                 out.println("<HR><P>Thank You for Registering, <B>" + nameStr + "</B>!<P><HR>");
                 
                 // Create a HTMLHyperlink object and display it
                 //res.getRequestDispatcher("index.html").include(request, response);
				 String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
				 out.println(docType +"<HTML>\n" +
				"<HEAD><TITLE>Login</TITLE><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><h3><p align='right'><a href='validate.html'>Login/Sign up</a> &nbsp&nbsp&nbsp <a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp <a href='/bestdeals/OrderPage'>Shopping cart items </a></p></h3></TR>");
    
				 
				 
				 req.getRequestDispatcher("/examples.html").include(req,res);
                 out.println("</UL></BODY></HTML>");

              }

            }
           catch (Exception e) 
           {  
              // Show error in browser
              out.println("<HTML>");
              out.println("<TITLE>ERROR!</TITLE>");
              out.println("<META HTTP-EQUIV=\"pragma\" content=\"no-cache\"> ");
              out.println("<BODY BGCOLOR=\"blanchedalmond\">");
              out.println("<BR><B>Error Message:</B><P>");
              out.println(e + "<P>");
                    
              // Create a HTMLHyperlink object and display it
              //res.getRequestDispatcher("index.html").include(request, response);
			  
			  req.getRequestDispatcher("/examples.html").include(req,res);
              out.println("</UL></BODY></HTML>");

              e.printStackTrace();
           }
        }
        else
        {
           // Output a message to the customer saying customer name & 
           // e-mail not entered. Please try again
           /*out.println ("<HTML> " +
                        "<TITLE>Invalid Registration Form</TITLE> " +
                        "<META HTTP-EQUIV=\"pragma\" content=\"no-cache\"> " +
                        "<BODY BGCOLOR=\"blanchedalmond\" TEXT=\"black\"> " );
              
           out.println ("<HR><B>ERROR</B> in customer data - <P><B>" +
                        errorText +
                        "</B>Please Try Again... <HR>");

             */       
           // Create a HTMLHyperlink object and display it
           //res.getRequestDispatcher("index.html").include(request, response);
		   
		   String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	  
	out.println(docType +"<HTML>\n" +
				"<HEAD><TITLE>Login error!</TITLE><BODY BGCOLOR='##CCEEFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><p align='center'><b>User registration failed!</p>"+errorText+"<h3><p align='right'><a href='validate.html'>Login/Sign up</a> &nbsp&nbsp&nbsp <a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp <a href='/bestdeals/OrderPage'>Shopping cart items </a></p></h3></TR>");
    
		   
		   
		   req.getRequestDispatcher("/examples.html").include(req,res);
                       out.println("</UL></BODY></HTML>");
        }
         // Close the writer
         out.close();

         
    }   

}