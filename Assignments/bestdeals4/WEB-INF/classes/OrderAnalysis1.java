
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderAnalysis1 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HashADU.getAdu().put("ALL_PRODUCTS","0");
		HashADU.getAdu().put("IPhone 7","800");
		HashADU.getAdu().put("Samsung Galaxy Note5","500");
		HashADU.getAdu().put("Nexus 7","600");
		HashADU.getAdu().put("Sony Xperia","550");
		HashADU.getAdu().put("iPad Pro","600");
		HashADU.getAdu().put("Samsung Galaxy Tab 4","550");
		HashADU.getAdu().put("Nexus Tab 9","599");
		HashADU.getAdu().put("Lenovo Tab 2","499");
		HashADU.getAdu().put("MacBook Air","1700");
		HashADU.getAdu().put("Lenovo Laptop","1300.99");
		HashADU.getAdu().put("Dell Inspiron i7","700");
		HashADU.getAdu().put("Asus Laptop","899");
		HashADU.getAdu().put("LG 4K Ultra HD Smart LED TV","3000");
		HashADU.getAdu().put("Samsung Curved 4K Ultra HD Smart LED TV","3999");
		HashADU.getAdu().put("Toshiba LED 1080p HDTV","1600");
		HashADU.getAdu().put("Sony 4K Ultra HD Smart LED TV","3999");
		HashADU.getAdu().put("LG headphones","100");
		HashADU.getAdu().put("Ligthening Fast Charger","50");
		HashADU.getAdu().put("Blu-ray player 3D","349.99");

		PrintWriter pw = resp.getWriter();
		pw.println("<html><head><link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>Trending</title></head>"
				+ "<body><header>"
				+ "<h1><font face='Cambria'>BEST DEALS</font></h1>"
				+ "<h2>The Best Online Retail Store You Can Get</h2></header>"
				+ "<nav><ul>"
				+ "<li><a href='Home.html' class='home'><font face='Cambria'>HOME</font></a></li>"
				+ "<li><a href='Phone.html' class='phone'><font face='Cambria'>SMART PHONES</font></a></li>"
				+ "<li><a href='Tablet.html' class='tablet'><font face='Cambria'>TABLETS</font></a></li>"
				+ "<li><a href='Laptop.html' class='laptop'><font face='Cambria'>LAPTOPS</font></a></li>"
				+ "<li><a href='Tv.html' class='tv'><font face='Cambria'>TV</font></a></li>"
				+ " <li> <a href='DealMatchesUtilities'><font face = 'Cambria' >DEALS</font></a></li>"
				+ "</ul>"
				+ "<a href='Index.html'>SIGN OUT</a> <a href='ViewOrderServlet'>CART</a> <a href='GetOrders'>VIEW ORDERS</a>"
				+ "</nav>"
				+ "<div id='contain'>"
				+ "<section id='desc'>"
				+ "<form method='post' action='TrendingServlet'>"
				+ "<table class='query-table'>"
				+ "<tr><th colspan='4'><b> Grouping </b></th></tr>"
				
				+ "<tr><td>"
				+ "Top 5 Most Liked</td><td colspan='3'>"
				+ "</select></td></tr>"
				
				+ "<tr><td>"
				+ "Top 5 zip-codes where maximum number of products sold</td><td colspan='3'>"
				+"</td></tr>"
				
				+ "<tr><td>"
				+ "Top 5 most sold products regardless of the rating</td><td colspan='3'>"
				+"</td></tr>"
				
				+ "<td colspan='2'><input type='submit' name='trending' value='Trending'/></td>"
				+ "</tr></table></form></section>"
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
}
