

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

/** Shows all items currently in ShoppingCart. Clients
 *  have their own session that keeps track of which
 *  ShoppingCart is theirs. If this is their first visit
 *  to the order page, a new shopping cart is created.
 *  Usually, people come to this page by way of a page
 *  showing catalog entries, so this page adds an additional
 *  item to the shopping cart. But users can also
 *  bookmark this page, access it from their history list,
 *  or be sent back to it by clicking on the "Update Order"
 *  button after changing the number of items ordered.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class OrderPage extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    ShoppingCart cart;
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
      if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("shoppingCart", cart);
      }
      String itemID = request.getParameter("itemID");
      if (itemID != null) {
        String numItemsString =
          request.getParameter("numItems");
        if (numItemsString == null) {
    	  
          cart.addItem(itemID);
        } else {
          int numItems;
          try {
            numItems = Integer.parseInt(numItemsString);
			
          } catch(NumberFormatException nfe) {
            numItems = 1;
          }
          cart.setNumOrdered(itemID, numItems);
        }
      }
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Status of Your Order";
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
    synchronized(session) {
      List itemsOrdered = cart.getItemsOrdered();
      if (itemsOrdered.size() == 0) {
        out.println("<H2><I>No items in your cart...</I></H2>");
       
	  
	 int cartItems = itemsOrdered.size();

	 
	 if (cartItems == 0) {
	 session.setAttribute("cartItems","0");
        cartItems = 0;
      }
	  
	  String backURL =
          response.encodeURL("/bestdeals/HomePage1");
       
        // "Proceed to Checkout" button below table
		 out.println
          ("<TR><TD colspan='4'>" +
           "<FORM ACTION=\"" + backURL + "\">\n" +
           "<BIG><LEFT>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Continue Shopping\">\n" +
           "</LEFT></BIG></FORM>");
	  
	  }
	  
	  else {
	  session.setAttribute("cartItems",itemsOrdered.size());
        out.println
          ("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
           "<TR BGCOLOR=\"#75E9F2\">\n" +
           "  <TH>Item ID<TH>Description\n" +
           "  <TH>Unit Cost<TH>Number<TH>Total Cost");
        ItemOrder order;
        NumberFormat formatter =
          NumberFormat.getCurrencyInstance();
        for(int i=0; i<itemsOrdered.size(); i++) {
          order = (ItemOrder)itemsOrdered.get(i);
          out.println
            ("<TR>\n" +
             "  <TD>" + order.getItemID() + "\n" +
             "  <TD>" + order.getShortDescription() + "\n" +
             "  <TD>" +
             formatter.format(order.getUnitCost()) + "\n" +
             "  <TD>" +
             "<FORM>\n" +  // Submit to current URL
             "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"\n" +
             "       VALUE=\"" + order.getItemID() + "\">\n" +
             "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
             "       SIZE=3 VALUE=\"" + 
             order.getNumItems() + "\">\n" +
             "<SMALL>\n" +
             "<INPUT TYPE=\"SUBMIT\"\n "+
             "       VALUE=\"Update Order\">\n" +
             "</SMALL>\n" +
			 "</FORM>\n" +
             "  <TD>" +
             formatter.format(order.getTotalCost())+ "</TR>");
        }
        String checkoutURL =
          response.encodeURL("/bestdeals/PlaceOrder.htm");
		  String backURL =
          response.encodeURL("/bestdeals/HomePage1");
       
        // "Proceed to Checkout" button below table
		 out.println
          ("<TR><TD colspan='4'>" +
           "<FORM ACTION=\"" + backURL + "\">\n" +
           "<BIG><LEFT>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Continue Shopping\">\n" +
           "</LEFT></BIG></FORM>");
      

        out.println
          ("" +
           "<FORM ACTION=\"" + checkoutURL + "\">\n" +
           "<BIG><RIGHT>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Proceed to Checkout\">\n" +
           "</RIGHT></BIG></FORM></TD></TR></TABLE>\n");
      }
      out.println("</BODY></HTML>");
    }
  }
}
