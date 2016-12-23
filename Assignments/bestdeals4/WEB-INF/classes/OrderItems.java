
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class OrderItems extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

			res.setContentType("text/html");
			
		PrintWriter out = res.getWriter();
		
		String Name = (String)req.getAttribute("uname");
		
		String username = req.getParameter("name");
		String address = req.getParameter("address");
		String cardnum = req.getParameter("credit");
			String id = (String) req.getSession().getAttribute("email");
			List<String> namevalues = HashPname.getPn().get(id);
			List<Double> pricevalues = HashPprice.getPp().get(id);
			List<Double> orderval = UpdateOrderServlet.m2.get(id);
			Statement stmt = null;
			Connection con = null;

			int quantity=0;
			String order_name=null;
			String order_price=null;
			
			try{
				Random r = new Random(System.currentTimeMillis());
			Long orderid = (long) (10000 + r.nextInt(20000));
			
			MongoClient mg = new MongoClient("localhost", 27017);
			DB mdb = mg.getDB("Assignment4");

			DBCollection orders = mdb.getCollection("Orders");

			
			Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdealdatabase","root","root");
				
				 stmt = con.createStatement();
				 String sql=null;
				 //String dbname=null;
				 
				 Calendar c = Calendar.getInstance();

				c.add(Calendar.DATE, 14);
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				
				

			String day = format1.format(c.getTime());
			
			
			for (int i = 0; i < namevalues.size(); i++) {
				
				 quantity = (int)(orderval.get(i)/pricevalues.get(i));
				
				 order_name = namevalues.get(i);
				 order_price = Double.toString(orderval.get(i));
				
				BasicDBObject bdo = new BasicDBObject("key", "value");
				bdo.append("userid", id).append("orderid", orderid);
				bdo.append("pname", namevalues.get(i)).append("pprice", pricevalues.get(i));
				bdo.append("quantity", (orderval.get(i)/pricevalues.get(i)));
				orders.insert(bdo);
				
			
			String insertIntoCustomerRegisterQuery  = "INSERT INTO CustomerOrders"+"(OrderId,custName,orderName,orderPrice,orderQuantity,userAddress,creditCardNo,dayexpected)" + "VALUES (?,?,?,?,?,?,?,?);";
				 PreparedStatement ps = con.prepareStatement(insertIntoCustomerRegisterQuery);

				ps.setDouble(1,orderid);  
				ps.setString(2,username);  
				ps.setString(3,order_name);  
				ps.setString(4,order_price); 
				ps.setInt(5,quantity);				
				ps.setString(6,address);
				ps.setString(7,cardnum);
				ps.setString(8,day);
				
				ps.executeUpdate(); 
			
			
			}
			
			
			out.println("<html><head><tittle><h1>Order Details</h1></title></head>"
					  +"<body align ='center' bgcolor='#D8D8D8'>"
					  +"<h3>Order Placed Successfully"
					  +"<br>"
					  +"Your Order Id is: "+ orderid
					  +"<br>"
					  +"Expected delivery date is: "+ c.getTime()
					  +"</h3>"
					  +"<a href='Home.html'>Home</a>"
					  +"</body>"
					  +"</html>"
					);
				 
				 
				 
			
			HashPname.getPn().clear();
			HashPprice.getPp().clear();
			HashPname.getLn().clear();
			HashPprice.getLp().clear();
			UpdateOrderServlet.m2.clear();
			UpdateOrderServlet.l2.clear();
			UpdateOrderServlet.m3.clear();
			UpdateOrderServlet.l3.clear();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
