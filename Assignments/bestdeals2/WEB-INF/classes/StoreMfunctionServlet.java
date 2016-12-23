
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreMfunctionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter pw = res.getWriter();
		if (req.getParameter("add") != null) {
			String pname = req.getParameter("pname");
			String pprice = req.getParameter("pprice");

			HashADU.getAdu().put(pname, pprice);
			pw.println("<HTML>" + "<title>Success</title>" + "<body>");
			pw.println("<H2 align='center'>" + "Item Added Successfully"
					+ "<br>" + "Customers can view the products by signing in"
					+ "</H2>");
					
					
			pw.println("<a href='Index.html'>"+ "Back to Home"+ "</a>");
			
			pw.println("</body>" + "</html>");
			
			System.out.println(HashADU.getAdu());
		}

		if (req.getParameter("del") != null) {
			String pname = req.getParameter("pname");

			HashADU.getAdu().remove(pname);
			pw.println("<HTML>" + "<title>Success</title>" + "<body>");
			pw.println("<H2 align='center'>" + "Item Deleted Successfully"
					+ "</H2>");
					
					pw.println("<a href='Index.html'>"+ "Back to Home"+ "</a>");
			
			pw.println("</body>" + "</html>");
			System.out.println(HashADU.getAdu());
		}

		if (req.getParameter("upd") != null) {
			String pname = req.getParameter("pname");
			String pprice = req.getParameter("pprice");

			HashADU.getAdu().put(pname, pprice);
			pw.println("<HTML>" + "<title>Success</title>" + "<body>");
			pw.println("<H2 align='center'>" + "Item Updated Successfully"
					+ "</H2>");
					
					pw.println("<a href='Index.html'>"+ "Back to Home"+ "</a>");
			
			pw.println("</body>" + "</html>");
			System.out.println(HashADU.getAdu());
		}
	}
}
