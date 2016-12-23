
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
 
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class AddViewServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		PrintWriter out = res.getWriter();

		try {
			String name = req.getParameter("prname");

			if (req.getParameter("viewreview") != null) {
				
				
				MongoClient mg = new MongoClient("localhost", 27017);
				DB mdb = mg.getDB("Bestdeals");

				DBCollection custreviews = mdb.getCollection("CustomerReviews");

				
				BasicDBObject bdo = new BasicDBObject();
				bdo.put("productName", name);

				DBCursor dbc = custreviews.find(bdo);
				
				
				
				
				out.println("<html>");
				out.println("<head><title>User Reviews</title> </head>");
				out.println("<body>");
				out.println("<h2 font face ='Cambria'> User Reviews:" + name
						+ "</h2>");

				out.println("<a href='Home.html'>Home</a>");
				out.println("<br><br>");

				
				
				if(dbc.count()!=0){
					out.println("<table>");

					while (dbc.hasNext()) {

						BasicDBObject bdbo = (BasicDBObject) dbc.next();

						out.println("<tr>");
						out.println("<td> Product Name </td>");
						out.println("<td>: </td>");
						String pmodel = bdbo.getString("productName");
						out.println("<td>" + pmodel + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Product Category </td>");
						out.println("<td>: </td>");
						String pcategory = bdbo.getString("productCategory");
						out.println("<td>" + pcategory + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Product Price </td>");
						out.println("<td>: </td>");
						double pprice = bdbo.getDouble("productPrice");
						out.println("<td>" + pprice + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Retailer Name </td>");
						out.println("<td>: </td>");
						String retailer = bdbo.getString("retailerName");
						out.println("<td>" + retailer + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Retailer Zip </td>");
						out.println("<td>: </td>");
						String rzip = bdbo.getString("retailerZipcode");
						out.println("<td>" + rzip + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Retailer City </td>");
						out.println("<td>: </td>");
						String rcity = bdbo.getString("retailerCity");
						out.println("<td>" + rcity + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Retailer State </td>");
						out.println("<td>: </td>");
						String rstate = bdbo.getString("retailerState");
						out.println("<td>" + rstate + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Product On Sale </td>");
						out.println("<td>: </td>");
						String pos = bdbo.getString("productOnSale");
						out.println("<td>" + pos + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Manufacturer Name </td>");
						out.println("<td>: </td>");
						String mname = bdbo.getString("consoleManufacturer");
						out.println("<td>" + mname + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Manufacturer Rebate </td>");
						out.println("<td>: </td>");
						String mrebate = bdbo.getString("manufacturerRebate");
						out.println("<td>" + mrebate + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> User Id </td>");
						out.println("<td>: </td>");
						String userid = bdbo.getString("userID");
						out.println("<td>" + userid + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Age </td>");
						out.println("<td>: </td>");
						int uage = bdbo.getInt("userAge");
						out.println("<td>" + uage + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Gender </td>");
						out.println("<td>: </td>");
						String gender = bdbo.getString("userGender");
						out.println("<td>" + gender + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Occupation </td>");
						out.println("<td>: </td>");
						String occu = bdbo.getString("userOccupation");
						out.println("<td>" + occu + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Rating </td>");
						out.println("<td>: </td>");
						int rating = bdbo.getInt("reviewRating");
						out.println("<td>" + rating + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Date </td>");
						out.println("<td>: </td>");
						String rdate = bdbo.getString("reviewDate");
						out.println("<td>" + rdate + "</td>");
						out.println("</tr>");

						out.println("<tr>");
						out.println("<td> Review Text: </td>");
						out.println("<td>: </td>");
						String reviewt = bdbo.getString("reviewText");
						out.println("<td>" + reviewt + "</td>");
						out.println("</tr>");
					
				}
				
				}
				
				
				else {
					out.println("No one has reviewed this product yet.");
					}

			} 
			
			else if (req.getParameter("addtocart") != null) {

				String email = (String) req.getSession().getAttribute("email");
				String prname = req.getParameter("prname");
				double prprice = Double.parseDouble(req.getParameter("prprice"));
				
				
				HashPname.getLn().add(prname);
				HashPname.getPn().put(email, HashPname.getLn());

				HashPprice.getLp().add(prprice);
				HashPprice.getPp().put(email, HashPprice.getLp());
				res.sendRedirect("/bestdeals2/ViewOrderServlet");
			}

		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}
