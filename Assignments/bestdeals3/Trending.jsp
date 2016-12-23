
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import=" com.mongodb.*"%>

<%

	String gbdd = "";
		MongoClient mongo = new MongoClient("localhost", 27017);

			DB db = mongo.getDB("Assignment3");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews = db.getCollection("CustomerReviews");
			
			if (request.getParameter("trending") != null) {
				
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
						
						
						int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h4> Top 5 zip-codes where maximum number of products sold </h4>");
		for (DBObject result : aggregate1.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			tableData = "<tr><td>Zip: " + bobj.getString("Zip")
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
			
				rate.remove(i);
				price.remove(i);
				product.remove(i);
				review.remove(i);
				j++;
			}
			out.println(zipcount+" Product sold");
			out.println(" ");
		}

		
		if (rowCount == 0) {
			pageContent = "<h4><font face='cambria'>No Data Found for Top 5 zip-codes where maximum number of products sold</font></h4>";
			out.println(pageContent);
		}
		out.println("</section>");
							
%>

<%
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
						
						int rowCount1 = 0;
		int productCount1 = 0;
		String tableData1 = " ";
		String pageContent1 = " ";
		int pnamecount1=0;
		
		out.println("<h4> Top 5 Most Liked </h4>");
		for (DBObject result : aggregate2.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount1++;
			

			pageContent1 = "<table class = \"query-table\">" + tableData1
					+ "</table>";
			out.println(pageContent1);

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

				
				tableData1 = "<tr rowspan = \"3\"><td> Product: "
						+ product.get(i) + "</br>" + "</td></tr>";

				
				pageContent1 = "<table class = \"query-table\">" + tableData1
						+ "</table>";
			
				rate.remove(i);
				price.remove(i);
				product.remove(i);
				review.remove(i);
				j++;
				}
			}
		
		

	
		if (rowCount1 == 0) {
			pageContent1 = "<h1><font face='cambria'>No Data Found for Top 5 Most Liked</font></h1>";
			out.println(pageContent1);
		}
		out.println("</section>");

%>						

<%
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

						int rowCount3 = 0;
		int productCount3 = 0;
		String tableData3 = " ";
		String pageContent3 = " ";

		out.println("<h4> Top 5 most sold product regardless of ratings </h4>");
		for (DBObject result : aggregate3.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount3++;
			tableData3 = "<tr><td>Product: " + bobj.getString("Product")
			// + "</td>&nbsp" + "<td>Reviews Found: "
			// + bobj.getString("Review Count") + "</td>
				+ "</tr>";

			pageContent3 = "<table class = \"query-table\">" + tableData3
					+ "</table>";
			out.println(pageContent3);

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

				tableData3 = "<tr rowspan = \"3\"><td> Product: "
						+ product.get(i) + "</br>" + "Price: " + price.get(i)
						+ "</br>" + "Rating: " + rate.get(i) + "</br>"
						+ "Review: " + review.get(i) + "</td></tr>";
				pcount = pcount+1;
				pageContent3 = "<table class = \"query-table\">" + tableData3
						+ "</table>";
				//out.println(pageContent3);
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
		if (rowCount3 == 0) {
			pageContent3 = "<h4><font face='cambria'>No Data Found for Top 5 most sold product regardless of ratings</font></h4>";
			out.println(pageContent3);
		}
		out.println("</section>");

	}
%>
<html>
<br> <br> <br> <br>
			<form class="form1" action="OrderAnalysis1.jsp" align="center">
				<input type="submit" value="Back">
			</form>
</html>