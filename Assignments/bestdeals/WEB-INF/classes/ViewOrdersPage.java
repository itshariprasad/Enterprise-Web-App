
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import java.util.Random;
import javax.servlet.http.*;
import java.util.*;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have
 *  no values or multiple values.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class ViewOrdersPage extends HttpServlet {
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	
	response.setContentType("text/html");
	
	PrintWriter out = response.getWriter();  
	  String ordernumber=request.getParameter("ordernumber");
	  
	  
	  ServletContext sc = request.getSession().getServletContext();
	
				File fname= new File(sc.getRealPath("registration.txt"));
		
		//request.getRequestDispatcher("link.html").include(request, response);
		
		
		String email=request.getParameter("email");
		
	if(email.equals("admin@bestdeals.com")){
	/*
	if(ordernumber!=null)
	  {
		try{
				//ServletContext sc = request.getSession().getServletContext();
				File inFile = new File(sc.getRealPath("OrderDetails.txt"));
				
				if (!inFile.isFile()) {
				out.println("File Not Found");
				return;
				}

				File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

				BufferedReader br = new BufferedReader(new FileReader(sc.getRealPath("OrderDetails.txt")));
				PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

				String line = null;
				int deleteFlag=0;
				long datediff=0;
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date currentDate=new Date();
				String tDate=df.format(currentDate);
				
				while ((line = br.readLine()) != null) {

					String[] orderdetails=line.split("-");
					String orderDeliveryDate= orderdetails[4];
					SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
					
					
					
					try{
					Date orderDate=null;
					orderDate=sdf.parse(orderDeliveryDate);
					Date todayDate=null;
					todayDate=sdf.parse(tDate);
					
					long dateDifference=orderDate.getTime()-todayDate.getTime();
					datediff=dateDifference/(24 * 60 * 60 * 1000);
					
					}
					catch(Exception e){e.printStackTrace();}
					String orderno=ordernumber;
					
					if (!orderno.equals(orderdetails[0])) {

					pw.println(line);
					pw.flush();
					}
					else {
						if(datediff<=5) 
						{
							pw.println(line);
							pw.flush();
							deleteFlag=1;
						}else{}
					}
		}
		if(deleteFlag==1){
			out.println(" Its too late to delete this order.Order Can be deleted 5 days before delivery Date");
		}
		pw.close();
		br.close();
		System.gc();
		
		File originalFile = new File(sc.getRealPath("OrderDetails.txt"));
		if (!originalFile.delete()) {
		out.println("Could not delete file");
		return;
		}
		if (!tempFile.renameTo(inFile))
		out.println("Could not rename file");

		}
		catch (FileNotFoundException ex) {
		ex.printStackTrace();
		}
		catch (IOException ex) {
		ex.printStackTrace();
		}
	  
	  
	  } 
	//ServletContext sc = request.getSession().getServletContext();
	*/
	File orderFile= new File(sc.getRealPath("OrderDetails.txt"));

	response.setContentType("text/html");
    
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	if(orderFile.canRead()){

	String title = "Your Orders";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=LEFT>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=LEFT>\n" +
                "<TR BGCOLOR=\"#75E9F2\">\n" +
                "<TH>Order Number<TH>Email id<TH>First Name<TH>Last Name<TH>Order Delivery Date");
				 
	BufferedReader br = new BufferedReader(new FileReader(orderFile));
	
	String line = null;

	while ((line = br.readLine()) != null) {
	
	String[] orderdetails=line.split("-");
	
	out.println("<TR><TD>" + orderdetails[0] + "</TD><TD>" + orderdetails[1] + "</TD><TD>" + orderdetails[2] + "</TD><TD>" + orderdetails[3]+"</TD><TD>"  + orderdetails[4] + "</TD></TR>");	
	}
	out.println("<TR><TD colspan='4'><FORM action='/bestdeals/Deleteorder'> Enter Order Id to Delete : <INPUT TYPE='TEXT' NAME='ordernumber' SIZE=10 VALUE=''>&nbsp&nbsp<INPUT TYPE='SUBMIT' VALUE='Delete Order'></FORM>" );
    out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM></TD></TR>" );	
	out.println("</TABLE>");

	}
	else{
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'>NO ORDER Details Found</font>");
	}

	}


else{


	
		
		try{
			
				
				FileReader fileReader = new FileReader(fname);
				BufferedReader bufferedReader = new BufferedReader(fileReader);				
				
				
				boolean login = false;
				String line1;
				while ((line1 = bufferedReader.readLine()) != null) {
				String[] details = line1.split(":");
                String user = details[0];
                String pass = details[1];
				String mail = details[2];
				
				if(email.equals(mail)){
				login = true;
				break;                 
				}
				}
		if(login){
	  
	  
	  
	  
	  /*
	  if(ordernumber!=null)
	  {
		try{
				//ServletContext sc = request.getSession().getServletContext();
				File inFile = new File(sc.getRealPath("OrderDetails.txt"));
				
				
				if (!inFile.isFile()) {
				out.println("File Not Found");
				return;
				}

				File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

				BufferedReader br = new BufferedReader(new FileReader(sc.getRealPath("OrderDetails.txt")));
				PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

				String line = null;
				int deleteFlag=0;
				long datediff=0;
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date currentDate=new Date();
				String tDate=df.format(currentDate);
				
				while ((line = br.readLine()) != null) {

					String[] orderdetails=line.split("-");
					
					String orderDeliveryDate= orderdetails[4];
					SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
					
					
					
					try{
					Date orderDate=null;
					orderDate=sdf.parse(orderDeliveryDate);
					Date todayDate=null;
					todayDate=sdf.parse(tDate);
					
					long dateDifference=orderDate.getTime()-todayDate.getTime();
					datediff=dateDifference/(24 * 60 * 60 * 1000);
					
					}
					catch(Exception e){e.printStackTrace();}
					String orderno=ordernumber;
					
					if (!orderno.equals(orderdetails[0])) {

					pw.println(line);
					pw.flush();
					}
					else {
						if(datediff<=5) 
						{
							pw.println(line);
							pw.flush();
							deleteFlag=1;
						}else{}
					}
		}
		if(deleteFlag==1){
			out.println(" Its too late to delete this order.Order Can be deleted 5 days before delivery Date");
		}
		pw.close();
		br.close();
		System.gc();
		
		File originalFile = new File(sc.getRealPath("OrderDetails.txt"));
		if (!originalFile.delete()) {
		out.println("Could not delete file");
		return;
		}
		if (!tempFile.renameTo(inFile))
		out.println("Could not rename file");

		}
		catch (FileNotFoundException ex) {
		ex.printStackTrace();
		}
		catch (IOException ex) {
		ex.printStackTrace();
		}
	  
	  
	  } 
	*/
	
	//ServletContext sc = request.getSession().getServletContext();
	
	
	File orderFile= new File(sc.getRealPath("OrderDetails.txt"));
	response.setContentType("text/html");
/*
	File newFile = new File(sc.getRealPath("newOrderDetails.txt"));
	BufferedReader nbr = new BufferedReader(new FileReader(newFile));
	
	
    
	if(nbr.readLine()!=null){
		PrintWriter writer = new PrintWriter(newFile);
		writer.print("");
		writer.close();
	}
	*/
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	if(orderFile.canRead()){
String title = "Your Orders";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=LEFT>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=LEFT>\n" +
                "<TR BGCOLOR=\"#75E9F2\">\n" +
                "<TH>Order Number<TH>Email id<TH>First Name<TH>Last Name<TH>Order Delivery Date");
	
				 
	BufferedReader br = new BufferedReader(new FileReader(orderFile));
	/*
	FileWriter f = new FileWriter(newFile, true);
    BufferedWriter output = new BufferedWriter(f);
	*/
	String line = null;

	while ((line = br.readLine()) != null) {
	
	String[] orderdetails1=line.split("-");
	String mail1 = orderdetails1[1];
	if(email.equals(mail1)){
	
	
	out.println("<TR><TD>" + orderdetails1[0] + "</TD><TD>" + orderdetails1[1] + "</TD><TD>" + orderdetails1[2] +"</TD><TD>" + orderdetails1[3] + "</TD><TD>" + orderdetails1[4] + "</TD></TR>");	
	 /*
	 output.write(orderdetails[0]);
               
				 output.write("-" + orderdetails[1]);
                 
				 output.write("-" + orderdetails[2]);
				 
				  output.write("-" + orderdetails[3]);
                 
				 output.write("-" + orderdetails[4]);
                 output.newLine();
	*/
	}
	}
	/*
	 output.flush();
     output.close();
	*/
	//out.println("<TR><TD colspan='4'><FORM> Enter Order Id to Delete : <INPUT TYPE='TEXT' NAME='ordernumber' SIZE=10 VALUE=''>&nbsp&nbsp<INPUT TYPE='SUBMIT' VALUE='Delete Order'></FORM>" );
    
	out.println("<TR><TD colspan='4'><FORM action='/bestdeals/Deleteorder'> Enter Order Id to Delete : <INPUT TYPE='TEXT' NAME='ordernumber' SIZE=10 VALUE=''>&nbsp&nbsp<INPUT TYPE='SUBMIT' VALUE='Delete Order'></FORM>" );
    
	out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM></TD></TR>" );	
	out.println("</TABLE>");

	}
	}
	else{
		 String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
		 
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'>NO ORDER Details Found</font>");
	out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM>");
	}
	}
	catch (FileNotFoundException qwerty){
                    out.print("Can't find a text file");
	}
  
  }
  }
}
