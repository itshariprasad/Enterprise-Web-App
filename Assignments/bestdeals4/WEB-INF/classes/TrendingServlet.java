import java.io.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class TrendingServlet extends HttpServlet {

	String gbdd = "";
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		
		String Name = (String)req.getAttribute("uname");

		try {

			MongoClient mongo = new MongoClient("localhost", 27017);

			DB db = mongo.getDB("Bestdeals");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews = db.getCollection("CustomerReviews");
			
			if (req.getParameter("trending") != null) {
				
				//out.println("HI");
				
				boolean countOnly = true;
				DBObject match = null;
				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				AggregationOutput aggregate1 = null;
				AggregationOutput aggregate2 = null;
				AggregationOutput aggregate3 = null;
				
						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$retailerZipcode");
						groupFields.put("count", new BasicDBObject("$sum", 1));
						groupFields.put("productName", new BasicDBObject(
								"$push", "$productName"));
						groupFields.put("productPrice", new BasicDBObject(
								"$push", "$productPrice"));
						groupFields.put("review", new BasicDBObject("$push",
								"$reviewText"));
						groupFields.put("rating", new BasicDBObject("$push",
								"$reviewRating"));

						group = new BasicDBObject("$group", groupFields);

						projectFields = new BasicDBObject("_id", 0);
						projectFields.put("Zip", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("Price", "$productPrice");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);
						
						DBObject sort = new BasicDBObject();
						DBObject orderby = null;
						DBObject limit = null;
						
						sort.put("Review Count",-1);
						orderby=new BasicDBObject("$sort",sort);
						limit=new BasicDBObject("$limit",5);


						aggregate1 = myReviews.aggregate(group, project, orderby,limit);
						
						
						// Construct the page content
						constructTop5ByZip(aggregate1, out, countOnly);
						
					
						
						//constructTop5ByLike();
					boolean countOnly2 = true;
				DBObject match2 = null;
				DBObject groupFields2 = null;
				DBObject group2 = null;
				DBObject projectFields2= null;
				DBObject project2 = null;
				
				
						groupFields2 = new BasicDBObject("_id", 0);
						groupFields2.put("_id", "$retailerCity");
						groupFields2.put("count", new BasicDBObject("$sum", 1));
						groupFields2.put("productName", new BasicDBObject(
								"$push", "$productName"));
						groupFields2.put("productPrice", new BasicDBObject(
								"$push", "$productPrice"));
						groupFields2.put("review", new BasicDBObject("$push",
								"$reviewText"));
						groupFields2.put("rating", new BasicDBObject("$push",
								"$reviewRating"));

						group2 = new BasicDBObject("$group", groupFields2);

						projectFields2 = new BasicDBObject("_id", 0);
						projectFields2.put("City", "$_id");
						projectFields2.put("Review Count", "$count");
						projectFields2.put("Product", "$productName");
						projectFields2.put("Price", "$productPrice");
						projectFields2.put("User", "$userName");
						projectFields2.put("Reviews", "$review");
						projectFields2.put("Rating", "$rating");

						project2 = new BasicDBObject("$project", projectFields2);
						
						DBObject sort2 = new BasicDBObject();
						DBObject orderby2 = null;
						DBObject limit2 = null;
						
						sort2.put("Review Count",-1);
						orderby2=new BasicDBObject("$sort",sort2);
						limit2=new BasicDBObject("$limit",5);


						aggregate2 = myReviews.aggregate(group2, project2, orderby2,limit2);
						//constructTop5ByLike(aggregate2, out, countOnly2);				
					
						
						MongoClient mongo1 = new MongoClient("localhost", 27017);

			DB db1 = mongo1.getDB("Bestdeals");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews1 = db1.getCollection("CustomerReviews");
			
			
			BasicDBObject whereQuery = new BasicDBObject();
    whereQuery.put("reviewRating", 5);
    BasicDBObject fields = new BasicDBObject().append("_id",false);
    fields.put("productName", 1);
	
	String [] ppname = new String[20];
	String pname = null;
  out.println("<h4> Top 5 Most Liked </h4>");
    DBCursor cursor = myReviews1.find(whereQuery, fields);
	int cc = 0;
	while (cursor.hasNext()) {
		    BasicDBObject obj = (BasicDBObject) cursor.next();
     pname = obj.getString("productName");

       // out.println(pname);
	ppname[cc] = pname;
	cc++;
	
	}

	//out.println(ppname[0]);
	
	for (int m=0; m<5; m++){
		if((ppname[m])==null){
			break;
		}
			String LINE ="<br>";
			out.print(ppname[m]+LINE);
	
	}	
		
		/*	
			BasicDBObject query = new BasicDBObject(); 
// configure fields to be returned (true/1 or false/0 will work)
// YOU MUST EXPLICITLY CONFIGURE _id TO NOT SHOW
BasicDBObject fields = new BasicDBObject("reviewRating",5).append("_id",false);

// do a query without specifying fields (and print results)


// do a query specifying the fields (and print results)
DBCursor curs = myReviews1.find(query, fields);
while(curs.hasNext()) {
   DBObject o = curs.next();
   out.println(o.toString());
}
			
			
			
			/*
//BasicDBObject queryDetails = new BasicDBObject();
// queryDetails.put("reviewRating", 5);
     DBCursor find = myReviews1.find({},{reviewRating:5});

     String places = null;
	 String[] place= null;
	 int list=0;
     while(find.hasNext())
     {
         places=find.next().get("productName").toString();       
			place[list] = places;
			list++;
     
	 } 
	  out.println(place);
     for(int i=0;i<place.length;i++)
     {
         out.println(place[i]);
     }
						
		*/				
						
		
						
						boolean countOnly3 = true;
				DBObject match3 = null;
				DBObject groupFields3 = null;
				DBObject group3 = null;
				DBObject projectFields3= null;
				DBObject project3 = null;
				
				
						groupFields3 = new BasicDBObject("_id", 0);
						groupFields3.put("_id", "$productName");
						groupFields3.put("count", new BasicDBObject("$sum", 1));
						groupFields3.put("productName", new BasicDBObject(
								"$push", "$productName"));
						groupFields3.put("productPrice", new BasicDBObject(
								"$push", "$productPrice"));
						groupFields3.put("review", new BasicDBObject("$push",
								"$reviewText"));
						groupFields3.put("rating", new BasicDBObject("$push",
								"$reviewRating"));

						group3 = new BasicDBObject("$group", groupFields3);

						projectFields3 = new BasicDBObject("_id", 0);
						projectFields3.put("Product", "$_id");
						projectFields3.put("Review Count", "$count");
						projectFields3.put("Product", "$productName");
						projectFields3.put("Price", "$productPrice");
						projectFields3.put("User", "$userName");
						projectFields3.put("Reviews", "$review");
						projectFields3.put("Rating", "$rating");

						project3 = new BasicDBObject("$project", projectFields3);
						
						DBObject sort3 = new BasicDBObject();
						DBObject orderby3 = null;
						DBObject limit3 = null;
						
						sort3.put("Review Count",-1);
						orderby3=new BasicDBObject("$sort",sort3);
						limit3=new BasicDBObject("$limit",5);


						aggregate3 = myReviews.aggregate(group3, project3, orderby3,limit3);
						//constructTop5ByProduct(aggregate3, out, countOnly3);
						
						
		






		
						MongoClient mongo2 = new MongoClient("localhost", 27017);

			DB db2 = mongo2.getDB("Bestdeals");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews2 = db2.getCollection("CustomerReviews");
			
			
			BasicDBObject whereQuery2 = new BasicDBObject();
    //whereQuery.put("productName", );
    BasicDBObject fields2 = new BasicDBObject().append("_id",false);
    fields2.put("productName", 1);
	
	//String [] ppname2 = new String[20];
	String pname2 = null;
  out.println("<h4>Top 5 most sold product regardless of ratings</h4>");
    DBCursor cursor2 = myReviews2.find(whereQuery2, fields2);
	//int cc2 = 0;
	
	List<String> list = new ArrayList<String>();
	while (cursor2.hasNext()) {
		    BasicDBObject obj2 = (BasicDBObject) cursor2.next();
     pname2 = obj2.getString("productName");

       //out.println(pname2);
	//ppname2[cc2] = pname2;
	//cc2++;
	list.add(pname2);
	
	}
	
	//out.println(list);
	
	String LINE ="<br>";
//	out.println("\nExample 1 - Count 'a' with frequency"+LINE);
//	out.println("Iphone 7 : " + Collections.frequency(list, "Iphone 7")+LINE);

//	out.println("\nExample 2 - Count all with frequency"+LINE);
	Set<String> uniqueSet = new HashSet<String>(list);
	for (String temp : uniqueSet) {
//		out.println(temp + ": " + Collections.frequency(list, temp)+LINE);
	}

	//out.println("\nExample 3 - Count all with Map"+LINE);
	Map<String, Integer> map = new HashMap<String, Integer>();

	for (String temp : list) {
		Integer count = map.get(temp);
		map.put(temp, (count == null) ? 1 : count + 1);
	}
	//printMap(map,out);

	//out.println("\nSorted Map"+LINE);
	Map<String, Integer> treeMap = new TreeMap<String, Integer>(map);
	Map<String, Integer> sortedMap = sortByValue(treeMap);
	printMap(sortedMap,out);

  

  
	
	
/*
TreeSet<String> unique = new TreeSet<String>();
        for(String str:ppname2){
            if(!unique.add(str)){
				out.println("*************");
                out.println("Duplicate Entry is: "+str);
        
		   
			
			}
			
        }
		
		 Object[] strArray = unique.toArray();
        /* All elements of array... 
        for (int i = 0; i < strArray.length; i++) {

            out.println("Array element : " + strArray[i]);
        } 
		
	out.println("*************");
		
	/*

String [] temp = new String[20];
int l=0;
	 for (int j=0;j<ppname2.length-1;j++){
  for (int k=j+1;k<ppname2.length;k++){
    if (ppname2[k].equals(ppname2[j]) && (j != k)){
		String get = ppname2[k];
		
		temp[l] = get;
		
		out.println("*************");
		out.println(temp[l]);
		l++;
	}
  }	
	 }
	out.println(temp[0]);
	out.println("*************");

	for (int m=0; m<5; m++){
		if((ppname[m])==null){
			break;
		}
			String LINE ="<br>";
			out.print(ppname[m]+LINE);
	
	}	
		*/
						
						
						
						
								
						String backURL =
          res.encodeURL("/bestdeals4/Home.html");

	out.println("</TABLE>\n" +
           "<FORM ACTION=\"" + backURL + "\">\n" +
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Back to Best Deal Store\">\n" +
           "</CENTER></BIG></FORM>");
    
	out.println("</BODY></HTML>");
						
						
						
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
			}
			
	
private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }
	
public static void printMap(Map<String, Integer> map, PrintWriter out){

String LINE ="<br>";
	
	for (Map.Entry<String, Integer> entry : map.entrySet()) {
//		out.println("Key : " + entry.getKey() + " Value : "	+ entry.getValue()+LINE);
	}
	
	
	Set<Entry<String, Integer>> set = map.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
		
		int mapcount=1;
        for(Map.Entry<String, Integer> entry:list){
			out.println(entry.getKey()+LINE);
            //out.println(entry.getKey()+" ==== "+entry.getValue()+" Product Sold");
			mapcount++;
			if(mapcount>5){break;}
        }
	
  }
  
	public void constructTop5ByProduct(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h4> Top 5 most sold product regardless of ratings </h4>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			tableData = "<tr><td>Product: " + bobj.getString("Product")
			// + "</td>&nbsp" + "<td>Reviews Found: "
			// + bobj.getString("Review Count") + "</td>
				+ "</tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

			List<Double> price = new ArrayList<Double>();
			List<String> product = new ArrayList<String>();
			List<String> review = new ArrayList<String>();
			List<Integer> rate = new ArrayList<Integer>();

			for (Object object : priceList) {
				price.add((Double) object);
			}
			for (Object object : productList) {
				product.add((String) object);
			}
			for (Object object : productReview) {
				review.add((String) object);
			}
			for (Object object : rating) {
				rate.add((Integer) object);
			}
			int j = 0;
int pcount=0;
			while (!(rate.isEmpty()) && j < 5) {
				int max = Collections.max(rate);
				int i = rate.indexOf(max);

				tableData = "<tr rowspan = \"3\"><td> Product: "
						+ product.get(i) + "</br>" + "Price: " + price.get(i)
						+ "</br>" + "Rating: " + rate.get(i) + "</br>"
						+ "Review: " + review.get(i) + "</td></tr>";
				pcount = pcount+1;
				pageContent = "<table class = \"query-table\">" + tableData
						+ "</table>";
				//out.println(pageContent);
				rate.remove(i);
				price.remove(i);
				product.remove(i);
				review.remove(i);
				j++;
				//pcount = pcount+1;
			}
			//out.println(pcount);
		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h4><font face='cambria'>No Data Found for Top 5 most sold product regardless of ratings</font></h4>";
			out.println(pageContent);
		}
		out.println("</section>");

	}

	
	
	
	public void constructTop5ByZip(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h4> Top 5 zip-codes where maximum number of products sold </h4>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			tableData = "<tr><td>Zip: " + bobj.getString("Zip")
			// + "</td>&nbsp" + "<td>Reviews Found: "
			// + bobj.getString("Review Count") + "</td>
					+ "</tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

			List<Double> price = new ArrayList<Double>();
			List<String> product = new ArrayList<String>();
			List<String> review = new ArrayList<String>();
			List<Integer> rate = new ArrayList<Integer>();

			for (Object object : priceList) {
				price.add((Double) object);
			}
			for (Object object : productList) {
				product.add((String) object);
			}
			for (Object object : productReview) {
				review.add((String) object);
			}
			for (Object object : rating) {
				rate.add((Integer) object);
			}
			int j = 0;
			int zipcount=0;
			while (!(rate.isEmpty()) && j < 5) {
				int max = Collections.max(rate);
				int i = rate.indexOf(max);

				tableData = "<tr rowspan = \"3\"><td> Product: "
						+ product.get(i) + "</br>" + "Price: " + price.get(i)
						+ "</br>" + "Rating: " + rate.get(i) + "</br>"
						+ "Review: " + review.get(i) + "</td></tr>";
				zipcount=zipcount+1;
			/*
				pageContent = "<table class = \"query-table\">" + tableData
						+ "</table>";
				out.println(pageContent);
				
				*/
				rate.remove(i);
				price.remove(i);
				product.remove(i);
				review.remove(i);
				j++;
			}
			out.println(zipcount+" Product sold");
			out.println(" ");
		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h4><font face='cambria'>No Data Found for Top 5 zip-codes where maximum number of products sold</font></h4>";
			out.println(pageContent);
		}
		out.println("</section>");

	}
	
	public void constructTop5ByLike(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
				
				MongoClient mongo1 = new MongoClient("localhost", 27017);

			DB db1 = mongo1.getDB("Bestdeals");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews1 = db1.getCollection("CustomerReviews");
BasicDBObject queryDetails = new BasicDBObject();
 queryDetails.put("reviewRating", 5);
     DBCursor find = myReviews1.find(queryDetails);

     String places = null;
	 String[] place= null;
	 int list=0;
     while(find.hasNext())
     {
         places=find.next().get("productName").toString();       
			place[list] = places;
			list++;
     
	 } 
	  out.println(place);
     for(int i=0;i<place.length;i++)
     {
         out.println(place[i]);
     }
				
				/*
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int pnamecount=0;
		
		out.println("<h4> Top 5 Most Liked </h4>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			//pnamecount=pnamecount+1;
			//tableData = "<tr><td>Product Name: " + bobj.getString("Product")
			//+ "</td>&nbsp" + "<td>Reviews Found: "
			// + bobj.getString("Review Count") + "</td>
					//+ "</tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

			List<Double> price = new ArrayList<Double>();
			List<String> product = new ArrayList<String>();
			List<String> review = new ArrayList<String>();
			List<Integer> rate = new ArrayList<Integer>();

			for (Object object : priceList) {
				price.add((Double) object);
			}
			for (Object object : productList) {
				product.add((String) object);
			}
			for (Object object : productReview) {
				review.add((String) object);
			}
			for (Object object : rating) {
				rate.add((Integer) object);
			}

			int j = 0;
				while (!(rate.isEmpty()) && j < 5) {
				int max = Collections.max(rate);
				int i = rate.indexOf(max);

				
				tableData = "<tr rowspan = \"3\"><td> Product: "
						+ product.get(i) + "</br>" + "</td></tr>";

				
				pageContent = "<table class = \"query-table\">" + tableData
						+ "</table>";
				//out.println(pageContent);
				rate.remove(i);
				price.remove(i);
				product.remove(i);
				review.remove(i);
				j++;
				}
			}
			//out.println("<h1><font face='cambria'>Total Reviews: " + j
				//	+ "</font></h1>");
			//out.println(pnamecount);
		

		// No data found
		if (rowCount == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found for Top 5 Most Liked</font></h1>";
			out.println(pageContent);
		}
		out.println("</section>");
*/
	}
	
			
	public void constructPageTop(PrintWriter out) {
		String pageHeading = "Query Result";
		String myPageTop = "<html><head><link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>Data Analytics</title></head>"
				+ "<body><header>"
				+ "<h1><font face='Cambria'>BEST DEALS</font></h1>"
				+ "<h2>The Best Online Retail Store You Can Get</h2></header>"
				+ "<nav><ul>"
				+ "<li><a href='Home.html' class='home'><font face='Cambria'>HOME</font></a></li>"
				+ "<li><a href='Phone.html' class='phone'><font face='Cambria'>SMART PHONES</font></a></li>"
				+ "<li><a href='Tablet.html' class='tablet'><font face='Cambria'>TABLETS</font></a></li>"
				+ "<li><a href='Laptop.html' class='laptop'><font face='Cambria'>LAPTOPS</font></a></li>"
				+ "<li><a href='Tv.html' class='tv'><font face='Cambria'>TV</font></a></li>"
				+ "</ul>"
				+ " <li> <a href='DealMatchesUtilities'><font face = 'Cambria' >DEALS</font></a></li>"
				+ "<a href='CustomerLogoutServlet'>SIGN OUT</a> <a href='ViewOrderServlet'>CART</a> <a href='GetOrders'>VIEW ORDERS</a>"
				+ "</nav>"
				+ "<div id='contain'>"
				+ "<section id='desc'>"
				+ "<h2>" + pageHeading + "</h2>";

		out.println(myPageTop);
	}

	
	public void constructPageBottom(PrintWriter out) {
		String myPageBottom = "<aside class='vnav'>"
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
				+ "<li><h3>Data Analytics</h3><ul>"
				+ "<li><a href='OrderAnalysis'>Data Analytics</a></li>"
				+ "</ul></li>"
				+ "</ul></aside>"
				+ "<div class='clear'></div>"
				
				+ "</body></html>";

		out.println(myPageBottom);
	}
	
			
	
	

	/*
	public void constructtrendingByLike(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1>Top five most liked products</h1>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			tableData = "<tr><td>City: " + bobj.getString("City")
			// + "</td>&nbsp" + "<td>Reviews Found: "
			// + bobj.getString("Review Count") + "</td>
					+ "</tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

			List<Double> price = new ArrayList<Double>();
			List<String> product = new ArrayList<String>();
			List<String> review = new ArrayList<String>();
			List<Integer> rate = new ArrayList<Integer>();

			for (Object object : priceList) {
				price.add((Double) object);
			}
			for (Object object : productList) {
				product.add((String) object);
			}
			for (Object object : productReview) {
				review.add((String) object);
			}
			for (Object object : rating) {
				rate.add((Integer) object);
			}

			int max = Collections.max(rate);
			int i = rate.indexOf(max);
			int count = 0;

			for (int j = 0; j < rate.size(); j++) {
				if (rate.get(j) == max && product.get(j).equals(product.get(i))) {
					count++;
				} else {

				}
			}

			tableData = "<tr rowspan = \"3\"><td> Product: " + product.get(i)
					+ "</br>" + "Price: " + price.get(i) + "</br>" + "Rating: "
					+ rate.get(i) + "</br>" + "Review: " + review.get(i)
					+ "</td></tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

			out.println("<h1><font face='cambria'>Total Reviews: " + count
					+ "</font></h1>");

		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
			out.println(pageContent);
		}
		out.println("</section>");

	}
	*/

}
