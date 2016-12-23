
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		
		String Name = (String)req.getAttribute("uname");
		
		pw.println("<HTML>\n" + "<HEAD><TITLE>" + "Checkout"
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#D8D8D8\">\n"
				+ "<H1 ALIGN=\"CENTER\">" + "Add your personal details for this order" + "</H1>" + "<BODY align='center'>"
				+ "<FORM action='OrderItems' method='get'>"
				+ "<table align='center'>"
				+ "<tr>"
				+ "<td><label>Name:</label></td>" + "<td><input type='text' name ='name'></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><label>Address:</label></td>"
				+ "<td><input type='text' name ='address'></td>"
				+ "</tr>"
				+ "<TR>"
				+ "<TD><label>Credit Card:</label></TD>"
				+"<BR><TD>" 
				+"&nbsp;&nbsp;<INPUT TYPE='RADIO' NAME='cardType' VALUE='Visa'>Visa<BR>"
				+"&nbsp;&nbsp;<INPUT TYPE='RADIO' NAME='cardType' VALUE='MasterCard'>MasterCard<BR>"
				+"&nbsp;&nbsp;<INPUT TYPE='RADIO' NAME='cardType' VALUE='Amex'>American Express<BR>"
				+"&nbsp;&nbsp;<INPUT TYPE='RADIO' NAME='cardType' VALUE='Discover'>Discover<BR>"
				+"</TR>"
				+"<tr>"
				+ "<td><label>Credit Card No:</label></td>"
				+ "<td><input type='password' name ='credit'></td>"
				+ "</tr>"
				+"<tr>"
				+ "<td><label>Repeat Credit Card No:</label></td>"
				+ "<td><input type='password' name ='credit'></td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><label>CVV:</label></td>" + "<td><input type='text' name ='cvv'></td>"
				+ "</tr>"
				+"</table>"
				+"<br><br>"
				+ "<input type = 'submit' value='Place Order' name='add'>"
				+"<br><br>"
				+"<a href='Home.html'>Home</a>"
				+ "</FORM>" + "<BODY>" + "<HTML>" + "<br><br><br>");
	}
}
