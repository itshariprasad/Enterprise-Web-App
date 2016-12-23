
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

public class Deleteorder extends HttpServlet {
	
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	PrintWriter out = response.getWriter();  
	  String ordernumber=request.getParameter("ordernumber");
	  if(ordernumber!=null)
	  {
		try{
				ServletContext sc = request.getSession().getServletContext();
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
	ServletContext sc = request.getSession().getServletContext();
	
	File orderFile= new File(sc.getRealPath("OrderDetails.txt"));

	response.setContentType("text/html");
    
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
	if(orderFile.canRead()){

	
	String title = "Your Orders";
    /*
	out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=LEFT>" + title + "</H1>\n" +
				"<TABLE BORDER=1 ALIGN=LEFT>\n" +
                "<TR BGCOLOR=\"#75E9F2\">\n" +
                "<TH>Order Number<TH>Email id<TH>First Name<TH>Last Name<TH>Order Delivery Date");
		*/ 
	BufferedReader br = new BufferedReader(new FileReader(orderFile));
	
	String line = null;
	String num = ordernumber;
	while ((line = br.readLine()) != null) {
	
	String[] orderdetails=line.split("-");
	
	//out.println("<TR><TD>" + orderdetails[0] + "</TD><TD>" + orderdetails[1] + "</TD><TD>" + orderdetails[2] + "</TD><TD>" + orderdetails[3] + "</TD><TD>" + orderdetails[4]+ "</TD></TR>");	
	if(num!=orderdetails[0]){
	
	out.println(ordernumber+ ": Order id has been deleted");
	//out.println("<TR><TD colspan='4'><FORM> Enter Order Id to Delete : <INPUT TYPE='TEXT' NAME='ordernumber' SIZE=10 VALUE=''>&nbsp&nbsp<INPUT TYPE='SUBMIT' VALUE='Delete Order'></FORM>" );
    //out.println("<TR><TD colspan='4'><h4> Order id deleted</h4>" );
    
	out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM></TD></TR>" );	
	out.println("</TABLE>");
	return;
	}
	
	}
	
		out.println(ordernumber+ ": Order id is invalid");
	//out.println("<TR><TD colspan='4'><FORM> Enter Order Id to Delete : <INPUT TYPE='TEXT' NAME='ordernumber' SIZE=10 VALUE=''>&nbsp&nbsp<INPUT TYPE='SUBMIT' VALUE='Delete Order'></FORM>" );
    //out.println("<TR><TD colspan='4'><h4> Order id deleted</h4>" );
    
	out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM></TD></TR>" );	
	out.println("</TABLE>");
	
	
	
	}
	else{
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'>NO ORDER Details Found</font>");
	}
  }

}

	
	
	
	
	
	/*
  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
	
	response.setContentType("text/html");
	
	PrintWriter out = response.getWriter();  
	  //String ordernumber=request.getParameter("ordernumber");
	  
	  
	  ServletContext sc = request.getSession().getServletContext();
	
				//File file= new File(sc.getRealPath("registration.txt"));
		
		//request.getRequestDispatcher("link.html").include(request, response);
		String lineToRemove = request.getParameter("ordernumber");
		
		
		
		File inputFile = new File(sc.getRealPath("OrderDetails.txt"));  // Your file  
		File tempFile = new File(sc.getRealPath("myTempFile.txt"));// temp file
boolean login = false;
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		String line;
		while((line = reader.readLine()) != null){
			
			String[] details = line.split("-");
                String orderid = details[0];
				
				out.println(orderid);
				
                if(lineToRemove.equals(orderid)){
				login = true;
				break;                 
				}
				}
		if(login){
			
		String ordernumber = lineToRemove;
		
		
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

				File tempFilee = new File(inFile.getAbsolutePath() + ".tmp");

				BufferedReader br = new BufferedReader(new FileReader(sc.getRealPath("OrderDetails.txt")));
				PrintWriter pw = new PrintWriter(new FileWriter(tempFilee));

				String linee = null;
				int deleteFlag=0;
				long datediff=0;
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				Date currentDate=new Date();
				String tDate=df.format(currentDate);
				
				while ((linee = br.readLine()) != null) {

					String[] orderdetails=linee.split("-");
					
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
		originalFile.setWritable(true);
		
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
	
		
	
		
		
String currentLine;

while((currentLine = reader.readLine()) != null) {
	String trimmedLine = currentLine.trim();
    if(trimmedLine.equals(lineToRemove)) continue;

    writer.write(currentLine);
	out.println(currentLine);
	
}


writer.close();
reader.close();
//File f = new File(sc.getRealPath("OrderDetails.txt"));

File del = new File(sc.getRealPath("file.txt"));
//inputFile.delete();
Boolean success = tempFile.renameTo(del);	
out.println(success);
		
	String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
		 
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'>Deleted the order: </font>"+lineToRemove);
	out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM>");
  }
  
  else{	
	String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
		 
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'>You have no orders with the number : </font>"+lineToRemove);
	out.println("<FORM action='/bestdeals/HomePage1'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM>");
  }
  }
}  
		
		
		
		
		/*
		try {

      File inFile = new File(sc.getRealPath("OrderDetails.txt"));

      if (!inFile.isFile()) {
        out.println("Parameter is not an existing file");
        return;
      }

      //Construct the new file that will later be renamed to the original filename.
      //File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

      BufferedReader br = new BufferedReader(new FileReader(inFile));
      //PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

      String data = null;

      //Read from the original file and write to the new
      //unless content matches data to be removed.
   

        while((data=br.readLine())!= null) {
        String[] de = data.split("-"); 
        if(de[0].equals(lineToRemove)) {
           data.trim();
		   out.println(lineToRemove + "has been deleted");
        }
      }
	  //pw.close();
      br.close();
	String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
		 
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'></font>");
	out.println("<FORM action='/bestdeals/HomePage'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM>");
	
      //Delete the original file
      if (!inFile.delete()) {
        out.println("Could not delete file");
        return;
      }

      //Rename the new file to the filename the original file had.
      /*
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
}				
				
				
				
		
	  
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
                "<TH>Order Number<TH>First Name<TH>Last Name<TH>Order Delivery Date");
	
				 
	BufferedReader br = new BufferedReader(new FileReader(orderFile));
	
	String line = null;

	
	
	out.println("<TR><TD colspan='4'>Order Id Deleted " );
    
	out.println("<FORM action='/bestdeals/HomePage'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM></TD></TR>" );	
	out.println("</TABLE>");

	}
	
	else{
		 
		 
	out.println(docType +"<HTML>\n" +"<font color='RED' size='12'>NO ORDER Details Found</font>");
	out.println("<FORM action='/bestdeals/HomePage'><INPUT TYPE='SUBMIT' VALUE='Back to Store'></FORM>");
	}
	
  }

}
*/