
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class FetchData extends HttpServlet {

	String gbdd = "";
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");

		String Name = (String)req.getAttribute("uname");
		
		PrintWriter out = res.getWriter();
		

		try {

			MongoClient mongo = new MongoClient("localhost", 27017);

			DB db = mongo.getDB("Bestdeals");

			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews = db.getCollection("CustomerReviews");

			if (req.getParameter("find") != null) {

				BasicDBObject bdo = new BasicDBObject();

				// Get the form data
				String productName = req.getParameter("productName");
				String productCategory = req.getParameter("productCategory");
				double productPrice = Double.parseDouble(req
						.getParameter("productPrice"));
				String retailerName = req.getParameter("retailerName");
				String retailerZipcode = req.getParameter("retailerZipcode");
				String retailerCity = req.getParameter("retailerCity");
				String retailerState = req.getParameter("retailerState");
				String productOnSale = req.getParameter("pos");
				String consoleManufacturer = req.getParameter("mname");
				String manufacturerRebate = req.getParameter("mrebate");
				String userID = req.getParameter("userid");
				int userAge = 0;
				try {
					userAge = Integer.parseInt(req.getParameter("uage"));
				} catch (Exception e) {

				}

				String userGender = req.getParameter("gender");

				String userOccupation = req.getParameter("occu");
				int reviewRating = Integer.parseInt(req
						.getParameter("reviewRating"));
				String reviewDate = req.getParameter("rdate");

				String compareRating = req.getParameter("compareRating");
				String comparePrice = req.getParameter("comparePrice");
				String compareAge = req.getParameter("compareAge");
				String returnValueDropdown = req.getParameter("returnValue");
				String groupByDropdown = req.getParameter("groupByDropdown");
				gbdd = groupByDropdown;
				String highPriceDropdown = req
						.getParameter("highpriceDropdown");
				String top5Dropdown = req.getParameter("top5Dropdown");
				String rating5Dropdown = req.getParameter("rating5Dropdown");
				String medianDropdown = req.getParameter("medianDropdown");
				String advances = req.getParameter("advances");

				// Boolean flags to check the filter settings
				boolean noFilter = false;
				boolean filterByProduct = false;
				boolean filterByProductC = false;
				boolean filterByPrice = false;
				boolean filterByretailer = false;
				boolean filterByZip = false;
				boolean filterByCity = false;
				boolean filterByretailerState = false;
				boolean filterBypos = false;
				boolean filterBymname = false;
				boolean filterBymrebate = false;
				boolean filterByuserid = false;
				boolean filterByuage = false;
				boolean filterByugender = false;
				boolean filterByuoccu = false;
				boolean filterByRating = false;
				boolean filterByrdate = false;

				boolean groupBy = false;
				boolean groupBySelected = false;
				boolean groupByProduct = false;
				boolean groupByProductC = false;
				boolean groupByProductPrice = false;
				boolean groupByretailer = false;
				boolean groupByZip = false;
				boolean groupByCity = false;
				boolean groupByretailerState = false;
				boolean groupBypos = false;
				boolean groupBymname = false;
				boolean groupBymrebate = false;
				boolean groupByuserid = false;
				boolean groupByuage = false;
				boolean groupByugender = false;
				boolean groupByuoccu = false;
				boolean groupByRating = false;
				boolean groupByrdate = false;

				boolean priceBycity = false;
				boolean priceByzip = false;
				boolean top5Bycity = false;
				boolean rated5Bycity = false;
				boolean medianBycity = false;
				boolean advancesearch = false;

				boolean countOnly = false;

				// Get the filters selected
				// Filter - Simple Search
				String[] filters = req.getParameterValues("queryCheckBox");
				// Filters - Group By
				String[] extraSettings = req
						.getParameterValues("extraSettings");

				DBCursor dbCursor = null;
				AggregationOutput aggregateData = null;

				// Check for extra settings(Grouping Settings)
				if (extraSettings != null) {
					// User has selected extra settings
					groupBy = true;

					for (int x = 0; x < extraSettings.length; x++) {
						switch (extraSettings[x]) {
						case "COUNT_ONLY":
							// Not implemented functionality to return count
							// only
							countOnly = true;
							break;
						case "GROUP_BY":
							groupBySelected = true;

						case "HIGH_PRICE":
							if (highPriceDropdown.equals("HIGH_BY_CITY")) {
								priceBycity = true;
							} else if (highPriceDropdown.equals("HIGH_BY_ZIP")) {
								priceByzip = true;
							}

						case "TOP_LIKED":
							if (top5Dropdown.equals("TOP5_BY_CITY")) {
								top5Bycity = true;
							}

						case "TOP_RATED":
							if (rating5Dropdown.equals("RATING5_BY_CITY")) {
								rated5Bycity = true;
							}

						case "MEDIAN":
							if (medianDropdown.equals("MEDIAN_BY_CITY")) {
								medianBycity = true;

							}

						case "ADVANCED":
							advancesearch = true;

						}
					}
				}

				// Check the main filters only if the 'groupBy' option is not
				// selected
				if (filters != null && groupBy != true) {
					for (int i = 0; i < filters.length; i++) {
						// Check what all filters are ON
						// Build the query accordingly
						switch (filters[i]) {
						case "productName":
							filterByProduct = true;
							if (!productName.equals("ALL_PRODUCTS")) {
								bdo.put("productName", productName);
							}
							break;

						case "productCategory":
							filterByProductC = true;
							if (!productCategory.equals("All_C")) {
								bdo.put("productCategory", productCategory);
							}
							break;

						case "productPrice":
							filterByPrice = true;
							if (comparePrice.equals("EQUALS_TO")) {
								bdo.put("productPrice", productPrice);
							} else if (comparePrice.equals("GREATER_THAN")) {
								bdo.put("productPrice", new BasicDBObject(
										"$gt", productPrice));
							} else if (comparePrice.equals("LESS_THAN")) {
								bdo.put("productPrice", new BasicDBObject(
										"$lt", productPrice));
							}
							break;

						case "retailerName":
							filterByretailer = true;
							bdo.put("retailerName", retailerName);
							break;

						case "retailerZipcode":
							filterByZip = true;
							bdo.put("retailerZipcode", retailerZipcode);
							break;

						case "retailerCity":
							filterByCity = true;
							if (!retailerCity.equals("All") && !groupByCity) {
								bdo.put("retailerCity", retailerCity);
							}
							break;

						case "retailerState":
							filterByretailerState = true;
							bdo.put("retailerState", retailerState);
							break;

						case "pos":
							filterBypos = true;
							bdo.put("productOnSale", productOnSale);
							break;

						case "mname":
							filterBymname = true;
							bdo.put("consoleManufacturer",
									consoleManufacturer);
							break;

						case "mrebate":
							filterBymrebate = true;
							bdo.put("manufacturerRebate", manufacturerRebate);
							break;

						case "userid":
							filterByuserid = true;
							bdo.put("userID", userID);
							break;

						case "uage":
							filterByuage = true;
							if (compareAge.equals("EQUALS")) {
								bdo.put("userAge", userAge);
							} else if (compareAge.equals("GREATER")) {
								bdo.put("userAge", new BasicDBObject("$gt",
										userAge));
							} else if (compareAge.equals("LESS")) {
								bdo.put("userAge", new BasicDBObject("$lt",
										userAge));
							}
							break;

						case "gender":
							filterByugender = true;
							bdo.put("userGender", userGender);
							break;

						case "occu":
							filterByuoccu = true;
							bdo.put("userOccupation", userOccupation);
							break;

						case "reviewRating":
							filterByRating = true;
							if (compareRating.equals("EQUALS_TO")) {
								bdo.put("reviewRating", reviewRating);
							} else if (compareRating.equals("GREATER_THAN")) {
								bdo.put("reviewRating", new BasicDBObject(
										"$gt", reviewRating));
							} else if (compareRating.equals("LESS_THAN")) {
								bdo.put("reviewRating", new BasicDBObject(
										"$lt", reviewRating));
							}
							break;

						case "rdate":
							filterByrdate = true;
							bdo.put("reviewDate", reviewDate);
							break;

						default:
							// Show all the reviews if nothing is selected
							noFilter = true;
							break;
						}
					}
				} else {
					// Show all the reviews if nothing is selected
					noFilter = true;
				}

				// Construct the top of the page
				constructPageTop(out);

				// Run the query
				if (groupBy == true) {
					// Run the query using aggregate function
					DBObject match = null;
					DBObject groupFields = null;
					DBObject group = null;
					DBObject projectFields = null;
					DBObject project = null;
					AggregationOutput aggregate = null;

					if (groupBySelected) {

						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$"+groupByDropdown);
						groupFields.put("count", new BasicDBObject("$sum", 1));
						groupFields.put("productName", new BasicDBObject(
								"$push", "$productName"));
						groupFields.put("review", new BasicDBObject("$push",
								"$reviewText"));
						groupFields.put("rating", new BasicDBObject("$push",
								"$reviewRating"));

						group = new BasicDBObject("$group", groupFields);

						projectFields = new BasicDBObject("_id", 0);
						projectFields.put("Selected", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);

						aggregate = myReviews.aggregate(group, project);

						// Construct the page content
						constructGroupBySelected(aggregate, out, countOnly);

				} 

					else if (priceBycity) {

						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$retailerCity");
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
						projectFields.put("City", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("Price", "$productPrice");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);

						aggregate = myReviews.aggregate(group, project);

						// Construct the page content
						constructPriceByCity(aggregate, out, countOnly);

					}

					else if (priceByzip) {

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

						aggregate = myReviews.aggregate(group, project);

						// Construct the page content
						constructPriceByZip(aggregate, out, countOnly);

					}

					else if (top5Bycity) {

						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$retailerCity");
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
						projectFields.put("City", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("Price", "$productPrice");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);

						aggregate = myReviews.aggregate(group, project);

						// Construct the page content
						constructTop5ByCity(aggregate, out, countOnly);

					}

					else if (rated5Bycity) {

						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$retailerCity");
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
						projectFields.put("City", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("Price", "$productPrice");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);

						aggregate = myReviews.aggregate(group, project);

						// Construct the page content
						constructrated5ByCity(aggregate, out, countOnly);

					}

					else if (medianBycity) {

						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$retailerCity");
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
						projectFields.put("City", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("Price", "$productPrice");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);

						aggregate = myReviews.aggregate(group, project);

						// Construct the page content
						constructmedianByCity(aggregate, out, countOnly);

					}

					else if (advancesearch) {
						
						groupFields = new BasicDBObject("_id", 0);
						groupFields.put("_id", "$retailerCity");
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
						projectFields.put("City", "$_id");
						projectFields.put("Review Count", "$count");
						projectFields.put("Product", "$productName");
						projectFields.put("Price", "$productPrice");
						projectFields.put("User", "$userName");
						projectFields.put("Reviews", "$review");
						projectFields.put("Rating", "$rating");

						project = new BasicDBObject("$project", projectFields);


						String pattern = ".*" + advances + ".*";
						BasicDBObject match1 = new BasicDBObject("$match",
								new BasicDBObject("reviewText",
										new BasicDBObject("$regex", pattern)));
						aggregate = myReviews.aggregate(match1,group, project);
						constructadvances(aggregate, out, countOnly);

					}

				} else {
					// Check the return value selected
					int returnLimit = 0;

					// Create sort variable
					DBObject sort = new BasicDBObject();

					if (returnValueDropdown.equals("TOP_5")) {
						// Top 5 - Sorted by review rating
						returnLimit = 5;
						sort.put("reviewRating", -1);
						dbCursor = myReviews.find(bdo).limit(returnLimit)
								.sort(sort);
					} else if (returnValueDropdown.equals("TOP_10")) {
						// Top 10 - Sorted by review rating
						returnLimit = 10;
						sort.put("reviewRating", -1);
						dbCursor = myReviews.find(bdo).limit(returnLimit)
								.sort(sort);
					} else if (returnValueDropdown.equals("LATEST_5")) {
						// Latest 5 - Sort by date
						returnLimit = 5;
						sort.put("reviewDate", -1);
						dbCursor = myReviews.find(bdo).limit(returnLimit)
								.sort(sort);
					} else if (returnValueDropdown.equals("LATEST_10")) {
						// Latest 10 - Sort by date
						returnLimit = 10;
						sort.put("reviewDate", -1);
						dbCursor = myReviews.find(bdo).limit(returnLimit)
								.sort(sort);
					} else {
						// Run the simple search query(default result)
						dbCursor = myReviews.find(bdo);
					}

					// Construct the page content
					constructDefaultContent(dbCursor, out, countOnly);
				}

				// Construct the bottom of the page
				constructPageBottom(out);
			}

			else if (req.getParameter("trending") != null) {
				boolean countOnly = true;
				DBObject match = null;
				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				AggregationOutput aggregate = null;
				groupFields = new BasicDBObject("_id", 0);
				groupFields.put("_id", "$retailerCity");
				groupFields.put("count", new BasicDBObject("$sum", 1));
				groupFields.put("productName", new BasicDBObject("$push",
						"$productName"));
				groupFields.put("productPrice", new BasicDBObject("$push",
						"$productPrice"));
				groupFields.put("review", new BasicDBObject("$push",
						"$reviewText"));
				groupFields.put("rating", new BasicDBObject("$push",
						"$reviewRating"));

				group = new BasicDBObject("$group", groupFields);

				projectFields = new BasicDBObject("_id", 0);
				projectFields.put("City", "$_id");
				projectFields.put("Review Count", "$count");
				projectFields.put("Product", "$productName");
				projectFields.put("Price", "$productPrice");
				projectFields.put("User", "$userName");
				projectFields.put("Reviews", "$review");
				projectFields.put("Rating", "$rating");

				project = new BasicDBObject("$project", projectFields);

				aggregate = myReviews.aggregate(group, project);

				// Construct the page content

				constructPageTop(out);

				constructtrendingByCity(aggregate, out, countOnly);
				constructPageBottom(out);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void constructPageTop(PrintWriter out) {
		String pageHeading = "Query Result";
		String myPageTop = "<html><head><link rel='stylesheet' type='text/css' href='StyleSheet.css'><title>Data Analytics</title></head>"
				+ "<body><header>"
				+ "<h1><font face='Cambria'>BEST DEALS</font></h1>"
				+ "<h2>The Best Online Retail Store You Can Get</h2></header>"
				
				+ "<div id='contain'>"
				+ "<section id='desc'>"
				+ "<h2>" + pageHeading + "</h2>";

		out.println(myPageTop);
	}

	public void constructDefaultContent(DBCursor dbCursor, PrintWriter out,
			boolean countOnly) {
		int count = 0;
		String tableData = " ";
		String pageContent = " ";

		while (dbCursor.hasNext()) {
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			tableData = "<tr><td>Name: <b>     "
					+ bobj.getString("productName") + " </b></td></tr>"
					+ "<tr><td>Category:    "
					+ bobj.getString("productCategory") + "<br>"
					+ "Price:       " + bobj.getDouble("productPrice")
					+ "</br>" + "Retailer:            "
					+ bobj.getString("retailerName") + "</br>"
					+ "Retailer Zipcode:    "
					+ bobj.getString("retailerZipcode") + "</br>"
					+ "Retailer City:       " + bobj.getString("retailerCity")
					+ "</br>" + "Retailer State:      "
					+ bobj.getString("retailerState") + "</br>"
					+ "Sale:                " + bobj.getString("productOnSale")
					+ "</br>" + "User ID:             "
					+ bobj.getString("userID") + "</br>"
					+ "User Age:            " + bobj.getInt("userAge")
					+ "</br>" + "User Gender:         "
					+ bobj.getString("userGender") + "</br>"
					+ "User Occupation:     "
					+ bobj.getString("userOccupation") + "</br>"
					+ "Manufacturer:        "
					+ bobj.getString("consoleManufacturer") + "</br>"
					+ "Manufacturer Rebate: "
					+ bobj.getString("manufacturerRebate") + "</br>"
					+ "Rating:              " + bobj.getInt("reviewRating")
					+ "</br>" + "Date:                "
					+ bobj.getString("reviewDate") + "</br>"
					+ "Review Text:         " + bobj.getString("reviewText")
					+ "</td></tr>";

			count++;

			out.println("<h3>" + count + "</h3>");
			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);
		}

		// No data found
		if (count == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
			out.println(pageContent);
		}

		out.println("<h1><font face='cambria'>Total Reviews: " + count
				+ "</font></h1>");

		out.println("</section>");

	}

	public void constructPageBottom(PrintWriter out) {
		String myPageBottom = "<div class='clear'></div>"
				+ "<div class='foo'> <p>Copyright © www.bestdeals.co.in</p> </div></div>"
				+ "</body></html>";

		out.println(myPageBottom);
	}

	public void constructGroupBySelected(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1>"+ "Grouped By- "+ gbdd + " </h1>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			tableData = "<tr><td>"+gbdd +": " + bobj.getString("Selected")
					+ "</td>&nbsp" + "<td>Reviews Found: "
					+ bobj.getString("Review Count") + "</td></tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

			// Now print the products with the given review rating
			while (productCount < productList.size()) {
				tableData = "<tr rowspan = \"3\"><td> Product: "
						+ productList.get(productCount) + "</br>" + "Rating: "
						+ rating.get(productCount) + "</br>" + "Review: "
						+ productReview.get(productCount) + "</td></tr>";

				pageContent = "<table class = \"query-table\">" + tableData
						+ "</table>";
				out.println(pageContent);

				productCount++;
			}

			// Reset product count
			productCount = 0;
		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
			out.println(pageContent);
		}
		out.println("</section>");

	}

	public void constructPriceByCity(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1> Highest Price Product By - City </h1>");
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

			double max = Collections.max(price);
			int i = price.indexOf(max);
			int count = 0;

			for (int j = 0; j < price.size(); j++) {
				if (price.get(j) == max
						&& product.get(j).equals(product.get(i))) {
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

	public void constructPriceByZip(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1> Highest Price Product By - Zip Code </h1>");
		for (DBObject result : aggregate.results()) {
			BasicDBObject bobj = (BasicDBObject) result;
			BasicDBList productList = (BasicDBList) bobj.get("Product");
			BasicDBList priceList = (BasicDBList) bobj.get("Price");
			BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
			BasicDBList rating = (BasicDBList) bobj.get("Rating");

			rowCount++;
			tableData = "<tr><td>Zip Code: " + bobj.getString("Zip") + "</tr>";

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

			double max = Collections.max(price);
			int i = price.indexOf(max);

			int count = 0;

			for (int j = 0; j < price.size(); j++) {
				if (price.get(j) == max) {
					count++;
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

	public void constructTop5ByCity(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1> Top 5 Liked - City </h1>");
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
			int j = 0;

			while (!(rate.isEmpty()) && j < 5) {
				int max = Collections.max(rate);
				int i = rate.indexOf(max);

				tableData = "<tr rowspan = \"3\"><td> Product: "
						+ product.get(i) + "</br>" + "Price: " + price.get(i)
						+ "</br>" + "Rating: " + rate.get(i) + "</br>"
						+ "Review: " + review.get(i) + "</td></tr>";

				pageContent = "<table class = \"query-table\">" + tableData
						+ "</table>";
				out.println(pageContent);
				rate.remove(i);
				price.remove(i);
				product.remove(i);
				review.remove(i);
				j++;
			}

		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
			out.println(pageContent);
		}
		out.println("</section>");

	}

	public void constructrated5ByCity(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1> Rating 5 - City </h1>");
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

			int j = 0;
			for (int i = 0; i < rate.size(); i++) {
				if (rate.get(i) == 5) {

					tableData = "<tr rowspan = \"3\"><td> Product: "
							+ product.get(i) + "</br>" + "Price: "
							+ price.get(i) + "</br>" + "Rating: " + rate.get(i)
							+ "</br>" + "Review: " + review.get(i)
							+ "</td></tr>";

					pageContent = "<table class = \"query-table\">" + tableData
							+ "</table>";
					out.println(pageContent);
					j++;
				}
			}
			out.println("<h1><font face='cambria'>Total Reviews: " + j
					+ "</font></h1>");

		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
			out.println(pageContent);
		}
		out.println("</section>");

	}

	public void constructtrendingByCity(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1> Trending - City </h1>");
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

	public void constructmedianByCity(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		String tableData = " ";
		String pageContent = " ";

		out.println("<h1> Median Price of Products By - City </h1>");
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

			Collections.sort(price);
			double median = 0;

			if (price.size() % 2 == 0) {
				median = (price.get((price.size() / 2) - 1) + price.get(price
						.size() / 2)) / 2;
			}

			else if (price.size() % 2 != 0) {
				median = price.get(((price.size() + 1) / 2) - 1);
			}

			tableData = "<tr rowspan = \"3\"><td> Median Price: " + median
					+ "</td></tr>";

			pageContent = "<table class = \"query-table\">" + tableData
					+ "</table>";
			out.println(pageContent);

		}

		// No data found
		if (rowCount == 0) {
			pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
			out.println(pageContent);
		}
		out.println("</section>");

	}
	
	public void constructadvances(AggregationOutput aggregate,
			PrintWriter out, boolean countOnly) {
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";

		
			out.println("<h1>Advanced Search</h1>");
			for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");

				rowCount++;
				tableData = "<tr><td> Reviews:" 
						+ "</td>&nbsp" + "</td></tr>";

				pageContent = "<table class = \"query-table\">" + tableData
						+ "</table>";
				out.println(pageContent);

				// Now print the products with the given review rating
				while (productCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "
							+ productList.get(productCount) + "</br>" + "Rating: "
							+ rating.get(productCount) + "</br>" + "Review: "
							+ productReview.get(productCount) + "</td></tr>";

					pageContent = "<table class = \"query-table\">" + tableData
							+ "</table>";
					out.println(pageContent);

					productCount++;
				}

				// Reset product count
				productCount = 0;
			}

			// No data found
			if (rowCount == 0) {
				pageContent = "<h1><font face='cambria'>No Data Found</font></h1>";
				out.println(pageContent);
			}
			out.println("</section>");


	}
	
	
	

}
