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

public class DealMatchesUtilities extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String pprice = "";
		PrintWriter out = res.getWriter();
		String pname = "";
		
		File file = new File("C:\\apache-tomcat-7.0.34\\webapps\\bestdeals4\\WEB-INF\\classes\\Deals\\DealMatches.txt");
		try{
BufferedReader reader = new BufferedReader(new FileReader(file));
String line = reader.readLine();
List<String> lines = new ArrayList<String>();
while (line != null) {
     lines.add(line);
     line = reader.readLine();
}

/*
Random r = new Random();
String randomLine1 = lines.get(r.nextInt(lines.size()));

String randomLine2 = lines.get(r.nextInt(lines.size()));

if(randomLine1.equals(randomLine2)){
		randomLine2 = lines.get(r.nextInt(lines.size()));
}
else{
	//nothing
}


String findhttp = randomLine1.substring(0,4);
String findhttp1 = randomLine2.substring(0,4);
*/

if(lines.size()==0){
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
				
				out.println("<h2>We beat our competitors in all aspects. Price-Match Guaranteed </h2>"
				+"<h3>No Offers Found</h3>"+"<BR>"
				+"<h2>Deal Matches</h2>"
				+"<h3>No Deals Found</h3>"
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

else{
String findhttp = null;
for ( int i = 0;  i < lines.size(); i++){
            String tempName = lines.get(i);
            findhttp = tempName.substring(0,4);
				if(findhttp.startsWith("http")){
                lines.remove(i);
            }
        }
		
Random r = new Random();
String randomLine1 = lines.get(r.nextInt(lines.size()));

String randomLine2 = lines.get(r.nextInt(lines.size()));

while((randomLine1.equals(randomLine2))) {
		randomLine2 = lines.get(r.nextInt(lines.size()));
}


/*
while(findhttp.startsWith("http")){
	randomLine1 = lines.get(r.nextInt(lines.size()));
	findhttp = randomLine1;
	if(randomLine1.equals(randomLine2)){
		randomLine2 = lines.get(r.nextInt(lines.size()));
}
}

while(findhttp1.startsWith("http")){
	randomLine2 = lines.get(r.nextInt(lines.size()));
	findhttp1 = randomLine2;
	}
*/

ProductStore productStore = new ProductStore();
		Set<String> s = productStore.getProducts().keySet();
		Iterator<String> it = s.iterator();
String namep = null;
out.println("<html><head><script type='text/javascript' src='javascript.js'></script>"
				+ "<link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>"
				+ "Deal Products"
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
				+"<h2>We beat our competitors in all aspects. Price-Match Guaranteed </h2>"
				+ "<h3>"+ randomLine1+"</h3>"
				+"<h3>"+ randomLine2+"</h3>"
				+"<h2>Deal Matches</h2>");
				
		while (it.hasNext()) {
			pname = it.next();
			namep = productStore.getProducts().get(pname).getProductName();
			if(randomLine1.contains(namep) || randomLine2.contains(namep)){
				
				out.println( "<form action='AddViewServlet' method='post'>"
					+ "<table class='table-1' align='center'>"
					+ "<tr>"
					+ "<th rowspan='3'><img src='Images/"
					+ productStore.getProducts().get(pname).getProductName()
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
					+ productStore.getProducts().get(pname).getProductName()
					+ "</textarea></td>"
					+ "<td>$<textarea name='prprice' readonly='readonly' rows='1' cols='6'>"
					+ productStore.getProducts().get(pname).getProductPrice()
					+ "</textarea></td>"
					+ "</tr>"
					+ "</table>"
					+ "</form>");
				
			}
			
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
				+ "<div class='foo'> <p>Copyright &copy www.bestdeals.co.in</p> </div></div></body></html>");
				
		
		}
		}
		catch(Exception e){
			out.println(e);
		}
	}
		
}
