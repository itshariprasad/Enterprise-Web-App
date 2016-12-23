
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
		out.println("<HTML>\n" + "<HEAD><TITLE>" + "New Products"
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#D8D8D8\">\n"
				+ "<H1 ALIGN=\"CENTER\">"
				+ "These are the new products available" + "</H1>");
		if (s.isEmpty()){
				out.println("Currently there are no new products");
				out.println("<a href='Home.html'><button>Back to Home</button></a>");
		}
		while (it.hasNext()) {
			pname = it.next();
			
			pprice = HashADU.getAdu().get(pname);

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
					+ "<a href='Home.html'><button>Back to Home</button></a>"
					+ "<br><br><br><br>" + "</BODY>" + "</HTML>");
		}
	}

}
