
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;


import java.util.List;
import java.util.Locale;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewOrderServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UpdateOrderServlet updateorder = new UpdateOrderServlet();
		String id = (String) req.getSession().getAttribute("email");
		List<String> namevalues = HashPname.getPn().get(id);
		List<Double> pricevalues = HashPprice.getPp().get(id);
		PrintWriter out = res.getWriter();
		
		String remove = req.getParameter("del");
		int r;
		
		try {
			List<Double> updateval = UpdateOrderServlet.m2.get(id);
			List<String> updatevalues = UpdateOrderServlet.m3.get(id);
			r = Integer.parseInt(remove);
			HashPname.getPn().get(id).remove(namevalues.get(r));
			HashPprice.getPp().get(id).remove(pricevalues.get(r));
			UpdateOrderServlet.m3.get(id).remove(updatevalues.get(r));
			UpdateOrderServlet.m2.get(id).remove(updateval.get(r));
			
						
		} catch (Exception e) {
			
		}
		
		
		if(HashPname.getPn().isEmpty() || namevalues.isEmpty()){
			try{
			UpdateOrderServlet.m2.get(id).removeAll(UpdateOrderServlet.l2);
			UpdateOrderServlet.m3.get(id).removeAll(UpdateOrderServlet.l3);
			out.println("<html><head><title>"+"Empty Cart"
					   +"</title>"+"</head>"+"<body align='center'><h1 font face = 'Cambria'>"
					+ "Your Cart is Empty"+"</h1>"+"<a href='Home.html'>Home</a>"+"</body></html>"
					);
			}catch(Exception e){
			out.println("<html><head><title>"+"Empty Cart"
					   +"</title>"+"</head>"+"<body align='center'><h1 font face = 'Cambria'>"
					+ "Your Cart is Empty"+"</h1>"+"<a href='Home.html'>Home</a>"+"</body></html>"
					);
			}
			
		}
		
		
		
		else if(!HashPname.getPn().isEmpty() || !namevalues.isEmpty()){
			
		
		
		
		//if( "".equals(HashPname.getPn()) || "".equals(namevalues)){
			
						updateorder.setId(id);
		
		String quantity = req.getParameter("qty");
		String iter = req.getParameter("itr");
		double qnty;
		int iteator;

		try {
			iteator = Integer.parseInt(iter);
			qnty = Integer.parseInt(quantity);
			updateorder.setQuantity(qnty);
			updateorder.setItr(iteator);
			
			updateorder.setTotal();
						
		} catch (Exception e) {
			qnty = 1;
			
			updateorder.setQuantity(qnty);
			
		}

		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		
		String checkoutURL = res.encodeURL("/bestdeals2/CheckoutServlet");
		String checkoutURL1 = res.encodeURL("/bestdeals2/Home.html");

		
		out.println("<HTML>\n" + "<HEAD><TITLE>" + "Your Cart"
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#D8D8D8\">\n"
				+ "<H1 ALIGN=\"CENTER\">" + "Your Cart" + "</H1>"
				+ "<TABLE BORDER=1 ALIGN=\"CENTER\">\n"
				+ "<TR BGCOLOR=\"#58FAF4\">\n"
				+ "  <TH>Item ID<TH>Description\n"
				+ "  <TH>Unit Cost<TH>Quantity<TH>Total Cost<TH>Delete");
		for (int i = 0; i < namevalues.size(); i++) {			
			updateorder.setMap(i);
			List<Double> updateval = UpdateOrderServlet.m2.get(id);
			List<String> updatevalues = UpdateOrderServlet.m3.get(id);
			
			int getvalues = (int)(updateval.get(i)/pricevalues.get(i));
			
			
			out.println("<TR>\n" + "  <TD>" + updatevalues.get(i) + "\n" + "  <TD>"
					+ updatevalues.get(i) + "\n" + "  <TD>"
					+ formatter.format(pricevalues.get(i)) + "\n" + "  <TD>"
					+ "<FORM>\n" + "<INPUT TYPE=\"HIDDEN\" NAME=\"itr\"\n"
					+ "       VALUE=\"" + i + "\">\n"
					+ "<INPUT TYPE=\"TEXT\" REQUIRED NAME=\"qty\"\n"
					+ "       SIZE=3 VALUE=\""+getvalues+ "\">\n" + "<SMALL>\n"
					+ "<INPUT TYPE=\"SUBMIT\"\n "
					+ "       VALUE=\"Update\">\n" + "</SMALL>\n"
					+ "</FORM>\n" + "  <TD>" + formatter.format(updateval.get(i)) + "<TD>"
					+ "<FORM>\n" + "<INPUT TYPE=\"HIDDEN\" NAME=\"del\"\n"
					+ "       VALUE=\"" + i + "\">\n" + "<SMALL>\n"
					+ "<INPUT TYPE=\"SUBMIT\"\n "
					+ "       VALUE=\"Delete\">\n" + "</SMALL>\n" + "</FORM>\n");
		}

		out.println("</TABLE>\n" + "<br><br>" + "<FORM ACTION=\"" + checkoutURL
				+ "\">\n" + "<BIG><CENTER>\n" + "<INPUT TYPE=\"SUBMIT\"\n"
				+ "       VALUE=\"Proceed to Checkout\">\n"
				+ "</CENTER></BIG></FORM>" + "  " + "<FORM ACTION=\""
				+ checkoutURL1 + "\">\n" + "<BIG><CENTER>\n"
				+ "<INPUT TYPE=\"SUBMIT\"\n"
				+ "       VALUE=\"Continue Shopping\">\n"
				+ "</CENTER></BIG></FORM>" + "</BODY></HTML>");
		
		
			
			
		}
		
		else{
						try{
			UpdateOrderServlet.m2.get(id).removeAll(UpdateOrderServlet.l2);
			UpdateOrderServlet.m3.get(id).removeAll(UpdateOrderServlet.l3);
			out.println("<html><head><title>"+"Empty Cart"
					   +"</title>"+"</head>"+"<body align='center'><h1 font face = 'Cambria'>"
					+ "Your Cart is Empty"+"</h1>"+"<a href='Home.html'>Home</a>"+"</body></html>"
					);
			}catch(Exception e){
			out.println("<html><head><title>"+"Empty Cart"
					   +"</title>"+"</head>"+"<body align='center'><h1 font face = 'Cambria'>"
					+ "Your Cart is Empty"+"</h1>"+"<a href='Home.html'>Home</a>"+"</body></html>"
					);
			
			}

		
		
		}
}
}
