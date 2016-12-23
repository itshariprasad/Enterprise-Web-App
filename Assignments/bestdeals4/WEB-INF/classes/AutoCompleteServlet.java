import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Iterator;

public class AutoCompleteServlet extends HttpServlet {

	private ServletContext context;
	private ProductStore productStore = new ProductStore();
	private HashMap<String, Products> products = ProductStore.getProducts();

	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		PrintWriter out = res.getWriter();
		
		String Name = (String)req.getAttribute("uname");

		String action = req.getParameter("action");
		String productId = req.getParameter("id");
		StringBuffer stbr = new StringBuffer();

		try {
			if (productId != null) {
				productId = productId.trim();
			} else {
				out.println("<html><head><script type='text/javascript' src='javascript.js'></script>"
						+ "<link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>Search Result</title></head>"
						+ "<body onload='init()'><header>"
						+ "<h1><font face='Cambria'>BEST DEALS</font></h1><h2><font face='Cambria'>The Best Online Retail Store You Can Get</font></h2></header>"
				+ "<nav><ul>"
				+ "<li><a href='Home.html' class='home'><font face='Cambria'>HOME</font></a></li>"
				+ "<li><a href='Phone.html' class='phone'><font face='Cambria'>SMART PHONES</font></a></li>"
				+ "<li><a href='Tablet.html' class='tablet'><font face='Cambria'>TABLETS</font></a></li>"
				+ "<li><a href='Laptop.html' class='laptop'><font face='Cambria'>LAPTOPS</font></a></li>"
				+ "<li><a href='Tv.html' class='tv'><font face='Cambria'>TV</font></a></li>"
				+ " <li> <a href='DealMatchesUtilities'><font face = 'Cambria' >DEALS</font></a></li>"
				+ "</ul>"
						+ "<a href='CustomerLogoutServlet'>SIGN OUT</a> <a href='ViewOrderServlet'>CART</a>"
						+ "<form name='autofillform' action='autocomplete'>"
						+ "<table border='0'>"
						+ "<tbody>"
						+ "<tr><td>"
						+ "<input type='search' autocomplete='off' id='complete-field' onkeyup='doCompletion()' placeholder='Search here...' style='padding: 5px; font-size: 16px;'></td>"
						+ "</tr>"
						+ "	<tr>"
						+ "<td id='auto-row'>"
						+ "<table id='complete-table' class='gridtable' style='position: absolute; width:315px;'>"
						+ "</table>"
						+ "</td></tr>"
						+ "</tbody>"
						+ "</table>"
						+ "	</form>"
						+ "</nav>"
						+ "<div id='contain'>"
						+ "<section id='desc'>"
						+ "<h1>The product you are looking for cannot be found</h1>"
						+ "</section>"
						+ "<aside class='vnav'>"
						+ "<ul><li>"
						+ "<h3>Categories</h3>"
				+ "<h4>Smart Phones</h4>"
				+ "<ul><li class='start'><a href='iphonemob.html'>IPhone 7</a></li>"
				+ "<li><a href='samsungmob.html'>Samsung Galaxy Note 5</a></li>"
				+ "<li><a href='nexusmob.html'>Nexus 7</a></li>"
				+ "<li class='end'><a href='xperia.html'>Sony Xperia</a></li>"
				+ "</ul>"
				+ "<li><h4>Tablets</h4><ul>"
				+ "<li class='start'><a href='ipadtab.html'>iPad Pro</a></li>"
				+ "<li><a href='samsungtab.html'>Samsung Galaxy Tab 4</a></li>"
				+ "<li><a href='nexustab.html'>Nexus Tab 9</a></li>"
				+ "<li class='end'><a href='lenovotab.html'>Lenovo Tab 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Laptops</h4><ul>"
				+ "<li class='start'><a href='iphonelap.html'>MacBook Air</a></li>"
				+ "<li><a href='lenonolap.html'>Lenovo Laptop</a></li>"
				+ "<li><a href='delllap.html'>Dell Inspiron i7</a></li>"
				+ "<li class='end'><a href='asuslap.html'>Asus ZenBook</a></li>"
				+ "</ul>"
				+ "<li><h4>TVs</h4><ul>"
				+ "<li class='start'><a href='lgtv.html'>LG 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='samsungtv.html'>Samsung Curved 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='toshiba.html'>Toshiba LED 1080p HDTV</a></li>"
				+ "<li class='end'><a href='sonytv.html'>Sony 4K Ultra HD Smart LED TV</a></li>"
				+ "</ul>"
				+ "</li>"
				+ "<li><h3>Accessories</h3><ul>"
				+ "<li><a href='headphone.html'>LG headphones</a></li>"
				+ "<li><a href='charger.html'>Tablet Charger</a></li>"
				+ "<li><a href='bluray.html'>Blu-ray Player</a></li>"
				+ "</ul>"
				+ "</li>"
			
				+ "<li><h3>Trending</h3><ul>"
				+ "<li><a href='OrderAnalysis1'>Trending</a></li>"
				+ "</ul></li>"
				+ "</ul></aside>"
				+ "<div class='clear'></div>"
				+ "<div class='foo'> <p>Copyright &copy www.bestdeals.co.in</p> </div></div></body></html>");

			}
			boolean namesAdded = false;
			if (action.equals("complete")) {

				if (!productId.equals("")) {

					Iterator<String> it = products.keySet().iterator();

					while (it.hasNext()) {
						String id = (String) it.next();
						Products p = (Products) products.get(id);

						if (p.getProductName().toLowerCase()
								.startsWith(productId)
								|| p.getProductName().toLowerCase()
										.contains(productId)) {

							stbr.append("<product>");
							stbr.append("<id>" + p.getId() + "</id>");
							stbr.append("<productName>"
									+ p.getProductName()
									+ "</productName>");
							stbr.append("</product>");
							namesAdded = true;
						}
					}
				}

				if (namesAdded) {
					res.setContentType("text/xml");
					res.setHeader("Cache-Control", "no-cache");
					res.getWriter().write(
							"<products>" + stbr.toString() + "</products>");
				} else {
					
					res.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			}

			if (action.equals("lookup")) {
			
				if ((productId != null) && products.containsKey(productId.trim())) {
					
				
					
					req.setAttribute("product", products.get(productId));
					out.println("<html><head><script type='text/javascript' src='javascript.js'></script>"
							+ "<link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>"
							+ products.get(productId).getProductName()
							+ "</title></head>"
							+ "<body onload='init()'><header>"
							+ "<h1><font face='Cambria'>BEST DEALS</font></h1><h2><font face='Cambria'>The Best Online Store You Can Get</font></h2></header>"
				+ "<nav><ul>"
				+ "<li><a href='Home.html' class='home'><font face='Cambria'>HOME</font></a></li>"
				+ "<li><a href='Phone.html' class='phone'><font face='Cambria'>SMART PHONES</font></a></li>"
				+ "<li><a href='Tablet.html' class='tablet'><font face='Cambria'>TABLETS</font></a></li>"
				+ "<li><a href='Laptop.html' class='laptop'><font face='Cambria'>LAPTOPS</font></a></li>"
				+ "<li><a href='Tv.html' class='tv'><font face='Cambria'>TV</font></a></li>"
				+ "</ul>"
							+ "<a href='CustomerLogoutServlet'>SIGN OUT</a> <a href='vieworder'>CART</a>"
							+ "<form name='autofillform' action='autocomplete'>"
							+ "<table border='0'>"
							+ "<tbody>"
							+ "<tr><td>"
						+ "<input type='search' autocomplete='off' id='complete-field' onkeyup='doCompletion()' placeholder='Search here...' style='padding: 5px; font-size: 16px;'></td>"
						+ "</tr>"
						+ "	<tr>"
						+ "<td id='auto-row'>"
						+ "<table id='complete-table' class='gridtable' style='position: absolute; width:315px;'>"
							+ "</table>"
							+ "</td></tr>"
							+ "</tbody>"
							+ "</table>"
							+ "	</form>"
							+ "</nav>"
							+ "<div id='contain'>"
							+ "<section id='desc'>"
							+ "<h1>Search results for: "
							+ products.get(productId).getProductName());
							
							
						if(Integer.parseInt(products.get(productId).getId()) > 19){	
							out.println("</h1>"
							+ "<form action='AddViewServlet' method='post'>"
							+ "<table class='table-1' align='center'>"
							+ "<tr>"
							+ "<th rowspan='3'><img src='Images/Consoles.jpg' height='250px' width='220px'></th>"
							+ "<td><input type='submit' name='addtocart' value='Add to Cart'></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><a href='Writerev.html'>Write Review</a></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><input type='submit' name='viewreview' value='View Review'></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><textarea name='prname' readonly='readonly' rows='1' cols='20'>"
							+ products.get(productId).getProductName()
							+ "</textarea></td>"
							+ "<td>$<textarea name='prprice' readonly='readonly' rows='1' cols='6'>"
							+ products.get(productId).getProductPrice()
							+ "</textarea></td>"
							+ "</tr>"
							+ "</table>"
							+ "</form>"
							+ "</section>"
							+ "<aside class='vnav'>"
							+ "<ul><li>"
							+ "<h3>Categories</h3>"
				+ "<h4>Smart Phones</h4>"
				+ "<ul><li class='start'><a href='iphonemob.html'>IPhone 7</a></li>"
				+ "<li><a href='samsungmob.html'>Samsung Galaxy Note 5</a></li>"
				+ "<li><a href='nexusmob.html'>Nexus 7</a></li>"
				+ "<li class='end'><a href='xperia.html'>Sony Xperia</a></li>"
				+ "</ul>"
				+ "<li><h4>Tablets</h4><ul>"
				+ "<li class='start'><a href='ipadtab.html'>iPad Pro</a></li>"
				+ "<li><a href='samsungtab.html'>Samsung Galaxy Tab 4</a></li>"
				+ "<li><a href='nexustab.html'>Nexus Tab 9</a></li>"
				+ "<li class='end'><a href='lenovotab.html'>Lenovo Tab 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Laptops</h4><ul>"
				+ "<li class='start'><a href='iphonelap.html'>MacBook Air</a></li>"
				+ "<li><a href='lenonolap.html'>Lenovo Laptop</a></li>"
				+ "<li><a href='delllap.html'>Dell Inspiron i7</a></li>"
				+ "<li class='end'><a href='asuslap.html'>Asus ZenBook</a></li>"
				+ "</ul>"
				+ "<li><h4>TVs</h4><ul>"
				+ "<li class='start'><a href='lgtv.html'>LG 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='samsungtv.html'>Samsung Curved 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='toshiba.html'>Toshiba LED 1080p HDTV</a></li>"
				+ "<li class='end'><a href='sonytv.html'>Sony 4K Ultra HD Smart LED TV</a></li>"
				+ "</ul>"
				+ "</li>"
				+ "<li><h3>Accessories</h3><ul>"
				+ "<li><a href='headphone.html'>LG headphones</a></li>"
				+ "<li><a href='charger.html'>Tablet Charger</a></li>"
				+ "<li><a href='bluray.html'>Blu-ray Player</a></li>"
				+ "</ul>"
				+ "</li>"
			
				+ "<li><h3>Trending</h3><ul>"
				+ "<li><a href='OrderAnalysis1'>Trending</a></li>"
				+ "</ul></li>"
				+ "</ul></aside>"
				+ "<div class='clear'></div>"
				+ "<div class='foo'> <p>Copyright © www.bestdeals.co.in</p> </div></div></body></html>");

				}
				else{
					out.println("</h1>"
							+ "<form action='AddViewServlet' method='post'>"
							+ "<table class='table-1' align='center'>"
							+ "<tr>"
							+ "<th rowspan='3'><img src='Images/"
							+ products.get(productId).getProductName()
							+ ".jpg' height='250px' width='220px'></th>"
							+ "<td><input type='submit' name='addtocart' value='Add to Cart'></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><a href='Writerev.html'>Write Review</a></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><input type='submit' name='viewreview' value='View Review'></td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td><textarea name='prname' readonly='readonly' rows='1' cols='20'>"
							+ products.get(productId).getProductName()
							+ "</textarea></td>"
							+ "<td>$<textarea name='prprice' readonly='readonly' rows='1' cols='6'>"
							+ products.get(productId).getProductPrice()
							+ "</textarea></td>"
							+ "</tr>"
							+ "</table>"
							+ "</form>"
							+ "</section>"
							+ "<aside class='vnav'>"
							+ "<ul><li>"
							+ "<h3>Categories</h3>"
				+ "<h4>Smart Phones</h4>"
				+ "<ul><li class='start'><a href='iphonemob.html'>IPhone 7</a></li>"
				+ "<li><a href='samsungmob.html'>Samsung Galaxy Note 5</a></li>"
				+ "<li><a href='nexusmob.html'>Nexus 7</a></li>"
				+ "<li class='end'><a href='xperia.html'>Sony Xperia</a></li>"
				+ "</ul>"
				+ "<li><h4>Tablets</h4><ul>"
				+ "<li class='start'><a href='ipadtab.html'>iPad Pro</a></li>"
				+ "<li><a href='samsungtab.html'>Samsung Galaxy Tab 4</a></li>"
				+ "<li><a href='nexustab.html'>Nexus Tab 9</a></li>"
				+ "<li class='end'><a href='lenovotab.html'>Lenovo Tab 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Laptops</h4><ul>"
				+ "<li class='start'><a href='iphonelap.html'>MacBook Air</a></li>"
				+ "<li><a href='lenonolap.html'>Lenovo Laptop</a></li>"
				+ "<li><a href='delllap.html'>Dell Inspiron i7</a></li>"
				+ "<li class='end'><a href='asuslap.html'>Asus ZenBook</a></li>"
				+ "</ul>"
				+ "<li><h4>TVs</h4><ul>"
				+ "<li class='start'><a href='lgtv.html'>LG 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='samsungtv.html'>Samsung Curved 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='toshiba.html'>Toshiba LED 1080p HDTV</a></li>"
				+ "<li class='end'><a href='sonytv.html'>Sony 4K Ultra HD Smart LED TV</a></li>"
				+ "</ul>"
				+ "</li>"
				+ "<li><h3>Accessories</h3><ul>"
				+ "<li><a href='headphone.html'>LG headphones</a></li>"
				+ "<li><a href='charger.html'>Tablet Charger</a></li>"
				+ "<li><a href='bluray.html'>Blu-ray Player</a></li>"
				+ "</ul>"
				+ "</li>"
			
				+ "<li><h3>Trending</h3><ul>"
				+ "<li><a href='OrderAnalysis1'>Trending</a></li>"
				+ "</ul></li>"
				+ "</ul></aside>"
				+ "<div class='clear'></div>"
				+ "<div class='foo'> <p>Copyright &copy www.bestdeals.co.in</p> </div></div></body></html>");
					
				}
				
			}
		}
		}		catch (Exception e) {

		}
	}
}
