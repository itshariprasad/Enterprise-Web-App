<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.MongoClient"%>
<%
	MongoClient mg = new MongoClient("localhost", 27017);
	DB mdb = mg.getDB("Assignment3");

	if (request.getParameter("delp") != null) {
		String deldropdown = request.getParameter("deldropdown");
		DBCollection products = mdb.getCollection("Products");
		BasicDBObject bdo = new BasicDBObject();
		bdo.put("field0", deldropdown);
		products.remove(bdo);
		out.println("<html>");
		out.println("<head><title>Success</title></head>");
		out.println("<body align='center'>Product Deleted Successfully<br>");
		out.println("<br><a href='Managerhome.jsp'>Back</a>");
		out.println("</html>");

	}

	else if (request.getParameter("upp") != null) {
		String updropdown = request.getParameter("updropdown");
		String pname = request.getParameter("pname");
		String upPrice = request.getParameter("pprice");
		String ptype = request.getParameter("ptype");
		String warranty = request.getParameter("warranty");
		String manrebate = request.getParameter("manurebate");
		String rdiscount = request.getParameter("rdiscount");
		DBCollection products = mdb.getCollection("Products");
		BasicDBObject bdo = new BasicDBObject();
		bdo.append("$set", new BasicDBObject().append("field0", pname)
				.append("field1", upPrice).append("field4", ptype)
				.append("field5", warranty).append("field6", manrebate)
				.append("field7", rdiscount));
		BasicDBObject bdo1 = new BasicDBObject().append("field0",
				updropdown);
		products.update(bdo1, bdo);
		out.println("<html>");
		out.println("<head><title>Success</title></head>");
		out.println("<body align='center'>Product Updated Successfully<br>");
		out.println("<br><a href='Managerhome.jsp'>Back</a>");
		out.println("</html>");
	}
%>