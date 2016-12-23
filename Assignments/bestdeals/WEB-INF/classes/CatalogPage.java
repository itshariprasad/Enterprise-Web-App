

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Base class for pages showing catalog entries.
 *  Servlets that extend this class must specify
 *  the catalog entries that they are selling and the page
 *  title <I>before</I> the servlet is ever accessed. This
 *  is done by putting calls to setItems and setTitle
 *  in init.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public abstract class CatalogPage extends HttpServlet {
  private CatalogItem[] items;
  private String[] itemIDs;
  private String title;

  /** Given an array of item IDs, look them up in the
   *  Catalog and put their corresponding CatalogItem entry
   *  into the items array. The CatalogItem contains a short
   *  description, a long description, and a price,
   *  using the item ID as the unique key.
   *  <P>
   *  Servlets that extend CatalogPage <B>must</B> call
   *  this method (usually from init) before the servlet
   *  is accessed.
   */
  
  protected void setItems(String[] itemIDs) {
    this.itemIDs = itemIDs;
    items = new CatalogItem[itemIDs.length];
    for(int i=0; i<items.length; i++) {
      items[i] = Catalog.getItem(itemIDs[i]);
    }
  }

  /** Sets the page title, which is displayed in
   *  an H1 heading in resultant page.
   *  <P>
   *  Servlets that extend CatalogPage <B>must</B> call
   *  this method (usually from init) before the servlet
   *  is accessed.
   */
  
  protected void setTitle(String title) {
    this.title = title;
  }

  /** First display title, then, for each catalog item,
   *  put its short description in a level-two (H2) heading
   *  with the price in parentheses and long description
   *  below. Below each entry, put an order button
   *  that submits info to the OrderPage servlet for
   *  the associated catalog entry.
   *  <P>
   *  To see the HTML that results from this method, do
   *  "View Source" on KidsBooksPage or TechBooksPage, two
   *  concrete classes that extend this abstract class.
   */
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    if (items == null) {
      response.sendError(response.SC_NOT_FOUND,
                         "Missing Items.");
      return;
    }
	HttpSession session = request.getSession();
	String cartItems="";
	try{
	cartItems = session.getAttribute("cartItems").toString();
	 if (cartItems == null) {
	 session.setAttribute("cartItems","0");
        cartItems = "0";
      }
   }
	catch(Exception e){cartItems = "0";}
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
	
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
				"<HEAD><TITLE>Best Deals</TITLE><BODY BGCOLOR='#FFFF'><TABLE BORDER='0' WIDTH='100%'>"+
				"<TR><TD BGCOLOR='#75E9F2' ALIGN='CENTER' COLSPAN='2'><H1>Welcome BestDeal Store</H1><P>The best Online Retail Store </P><h3><p align='right'><a href='vieworder.html'>View Orders</a> &nbsp&nbsp&nbsp Shopping Cart(<a href='/bestdeals/OrderPage'>"+cartItems+" items </a>)</p></h3></TD></TR>"+
				"<TR><TD ALIGN='LEFT' WIDTH='20%' BGCOLOR='#C1F3CA'><FONT COLOR='#FFFF'><H2><FONT COLOR='#FF0000'>&sect;</FONT><a href='/bestdeals/PhonePage'>Smart Phones</a><BR><br><br>"+
				"<FONT COLOR='#FF000'>&sect;</FONT><a href='/bestdeals/TabletPage'>Tablets</a><BR><br><br><FONT COLOR='#FF000'>&sect;</FONT><a href='/bestdeals/LaptopPage'>Laptops</a><BR><br><br><FONT COLOR='#FF0000'>&sect;</FONT>"+
				"<a href='/bestdeals/TVPage'>TVs</a><BR><br><br></H2></TD><TD>"+
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
    CatalogItem item;
    for(int i=0; i<items.length; i++) {
      out.println("<HR>");
      item = items[i];
      // Show error message if subclass lists item ID
      // that's not in the catalog.
      if (item == null) {
        out.println("<FONT COLOR=\"RED\">" +
                    "Unknown item ID " + itemIDs[i] +
                    "</FONT>");
      } else {
        out.println();
        String formURL =
          "/bestdeals/OrderPage";
        // Pass URLs that reference own site through encodeURL.
        formURL = response.encodeURL(formURL);
        out.println
          ("<FORM ACTION=\"" + formURL + "\">\n" +
           "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
           "       VALUE=\"" + item.getItemID() + "\">\n" +
           "<H2>" + item.getShortDescription() +
           " ($" + item.getCost() + ")</H2>\n" +
           item.getLongDescription() + "\n" +
           "<P>\n<CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\" " +
           "VALUE=\"Add to Shopping Cart\">\n" +
           "</CENTER>\n<P>\n</FORM>");
      }
    }
    out.println("</TD></TR></TABLE><HR>\n</BODY></HTML>");
  }
}
