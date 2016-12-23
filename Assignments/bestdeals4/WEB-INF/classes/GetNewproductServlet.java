import java.io.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetNewproductServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String pprice = "";
		PrintWriter out = res.getWriter();
		String pname = "";
		
		


		Set<String> s = HashADU.getAdu().keySet();
		Iterator<String> it = s.iterator();
		out.println("<html><head><script type='text/javascript' src='javascript.js'></script>"
				+ "<link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>"
				+ "New Products"
				+ "</title></head>"
				+ "<body onload='init()'><header>"
				+ "<h1><font face='Cambria'>BEST DEALS</font></h1><h2><font face='Cambria'>The Best Online Store You Can Get</font></h2></header>"
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
				+ "<input type='search' autocomplete='off' id='complete-field' onkeyup='doCompletion()' placeholder='Search...' style='padding: 5px; font-size: 16px;'></td>"
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
				+ "<section id='desc'>");
				if (s.isEmpty()){
				out.println("<h3>Currently there are no new products</h3>"
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
				//out.println("<a href='Home.html'><button>Back to Home</button></a>");
		}
		while (it.hasNext()) {
			pname = it.next();
		
		pprice = HashADU.getAdu().get(pname);
		out.println( "<form action='AddViewServlet' method='post'>"
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
					+ pname
					+ "</textarea></td>"
					+ "<td>$<textarea name='prprice' readonly='readonly' rows='1' cols='6'>"
					+ pprice
					+ "</textarea></td>"
					+ "</tr>"
					+ "</table>"
					+ "</form>"
					);
		}
		out.println("</section>"
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
		
		
/*		
		String	pprice = productStore.getProducts().get(pname).getProductPrice();
		String	pproduct = productStore.getProducts().get(pname).getProductName();
		
		String pid = productStore.getProducts().get(pname).getId();
		
		
		
		if(Integer.parseInt(pid) > 19){	
		out.println( "<form action='AddViewServlet' method='post'>"
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
					+ pproduct
					+ "</textarea></td>"
					+ "<td>$<textarea name='prprice' readonly='readonly' rows='1' cols='6'>"
					+ pprice
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
				+ "<li><a href='samsungmob.html'>Samsung S7 Edge</a></li>"
				+ "<li><a href='nexusmob.html'>Nexus 7</a></li>"
				+ "<li class='end'><a href='oneplusmob.html'>One plus 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Tablets</h4><ul>"
				+ "<li class='start'><a href='ipadtab.html'>IPad air 2</a></li>"
				+ "<li><a href='samsungtab.html'>Samsung Galaxy Tab 4</a></li>"
				+ "<li><a href='nexustab.html'>Nexus Tab 9</a></li>"
				+ "<li class='end'><a href='lenovotab.html'>Lenovo Tab 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Laptops</h4><ul>"
				+ "<li class='start'><a href='iphonelap.html'>Apple MacBook Pro</a></li>"
				+ "<li><a href='samsunglap.html'>Samsung Chromebook 3</a></li>"
				+ "<li><a href='delllap.html'>Dell Inspiron i7</a></li>"
				+ "<li class='end'><a href='asuslap.html'>Asus ZenBook</a></li>"
				+ "</ul>"
				+ "<li><h4>TVs</h4><ul>"
				+ "<li class='start'><a href='lgtv.html'>LG 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='samsungtv.html'>Samsung Curved 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='tcltv.html'>TCL 4K Ultra HD Roku Smart LED TV</a></li>"
				+ "<li class='end'><a href='sonytv.html'>Sony 4K Ultra HD Smart LED TV</a></li>"
				+ "</ul>"
				+ "</li>"
				+ "<li><h3>Accessories</h3><ul>"
				+ "<li><a href='speaker.html'>Home Speaker</a></li>"
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
		else{
		
		out.println( "<form action='AddViewServlet' method='post'>"
					+ "<table class='table-1' align='center'>"
					+ "<tr>"
					+ "<th rowspan='3'><img src='Images/"
					+ pproduct
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
					+ pproduct
					+ "</textarea></td>"
					+ "<td>$<textarea name='prprice' readonly='readonly' rows='1' cols='6'>"
					+ pprice
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
				+ "<li><a href='samsungmob.html'>Samsung S7 Edge</a></li>"
				+ "<li><a href='nexusmob.html'>Nexus 7</a></li>"
				+ "<li class='end'><a href='oneplusmob.html'>One plus 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Tablets</h4><ul>"
				+ "<li class='start'><a href='ipadtab.html'>IPad air 2</a></li>"
				+ "<li><a href='samsungtab.html'>Samsung Galaxy Tab 4</a></li>"
				+ "<li><a href='nexustab.html'>Nexus Tab 9</a></li>"
				+ "<li class='end'><a href='lenovotab.html'>Lenovo Tab 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Laptops</h4><ul>"
				+ "<li class='start'><a href='iphonelap.html'>Apple MacBook Pro</a></li>"
				+ "<li><a href='samsunglap.html'>Samsung Chromebook 3</a></li>"
				+ "<li><a href='delllap.html'>Dell Inspiron i7</a></li>"
				+ "<li class='end'><a href='asuslap.html'>Asus ZenBook</a></li>"
				+ "</ul>"
				+ "<li><h4>TVs</h4><ul>"
				+ "<li class='start'><a href='lgtv.html'>LG 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='samsungtv.html'>Samsung Curved 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='tcltv.html'>TCL 4K Ultra HD Roku Smart LED TV</a></li>"
				+ "<li class='end'><a href='sonytv.html'>Sony 4K Ultra HD Smart LED TV</a></li>"
				+ "</ul>"
				+ "</li>"
				+ "<li><h3>Accessories</h3><ul>"
				+ "<li><a href='speaker.html'>Home Speaker</a></li>"
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
				*/	
					/*
					out.println("<FORM action='AddViewServlet' method='post'>"
					+ "<label>Product Name:</label>"
					+ "<input type='text' name ='prname'+ value="
					+ pname
					+ ">"
					+ "<br><br>"
					+ "<label>Product Price:</label>"
					+ "<input type='text' name ='prprice'+ value="
					+ pprice
					+ ">"
					+ "<br><br>"
					+ "<input type='submit' name='addtocart' value='Add to Cart'>"
					+ "<br><br>"
					+ "<input type='submit' name='viewreview' value='View Review'>"
					+ "<br>"
					+ "</FORM>"
					+ "<a href='Writerev.html'><button>Write Review</button></a>"
					+ "<br><br><br><br>" + "</BODY>" + "</HTML>");
					*/
		
		/*
		out.println("</section>"
				+ "<aside class='vnav'>"
				+ "<ul><li>"
				
			+ "<h3>Categories</h3>"
				+ "<h4>Smart Phones</h4>"
				+ "<ul><li class='start'><a href='iphonemob.html'>IPhone 7</a></li>"
				+ "<li><a href='samsungmob.html'>Samsung S7 Edge</a></li>"
				+ "<li><a href='nexusmob.html'>Nexus 7</a></li>"
				+ "<li class='end'><a href='oneplusmob.html'>One plus 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Tablets</h4><ul>"
				+ "<li class='start'><a href='ipadtab.html'>IPad air 2</a></li>"
				+ "<li><a href='samsungtab.html'>Samsung Galaxy Tab 4</a></li>"
				+ "<li><a href='nexustab.html'>Nexus Tab 9</a></li>"
				+ "<li class='end'><a href='lenovotab.html'>Lenovo Tab 2</a></li>"
				+ "</ul>"
				+ "<li><h4>Laptops</h4><ul>"
				+ "<li class='start'><a href='iphonelap.html'>Apple MacBook Pro</a></li>"
				+ "<li><a href='samsunglap.html'>Samsung Chromebook 3</a></li>"
				+ "<li><a href='delllap.html'>Dell Inspiron i7</a></li>"
				+ "<li class='end'><a href='asuslap.html'>Asus ZenBook</a></li>"
				+ "</ul>"
				+ "<li><h4>TVs</h4><ul>"
				+ "<li class='start'><a href='lgtv.html'>LG 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='samsungtv.html'>Samsung Curved 4K Ultra HD Smart LED TV</a></li>"
				+ "<li><a href='tcltv.html'>TCL 4K Ultra HD Roku Smart LED TV</a></li>"
				+ "<li class='end'><a href='sonytv.html'>Sony 4K Ultra HD Smart LED TV</a></li>"
				+ "</ul>"
				+ "</li>"
				+ "<li><h3>Accessories</h3><ul>"
				+ "<li><a href='speaker.html'>Home Speaker</a></li>"
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
		*/
		
	}
		
}
