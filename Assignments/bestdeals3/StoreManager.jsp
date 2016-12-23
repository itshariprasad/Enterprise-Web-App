<%@page import="org.apache.commons.io.FilenameUtils"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page
	import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page
	import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.IOUtils"%>
<%@page import=" com.mongodb.*"%>
<%@page import=" java.io.*"%>
<%@page import=" java.io.ByteArrayOutputStream"%>
<%@page import=" java.awt.image.BufferedImage"%>
<%@page import=" javax.imageio.ImageIO"%>
<%@page import=" java.io.File"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import=" com.mongodb.*"%>

<%@ page import="Javabeans.*" %>


<%
	MongoClient mg = new MongoClient("localhost", 27017);
	DB mdb = mg.getDB("Assignment3");

	DBCollection products = mdb.getCollection("Products");
	BasicDBObject bdo = new BasicDBObject("key", "value");

	int counter = 0;
	File file;
	int maxFileSize = 5000 * 1024 * 1024;
	int maxMemSize = 5000 * 1024 * 1024;
	String path = "/Images";
	//ServletContext context = pageContext.getServletContext();
	//String filePath = context.getInitParameter("file-upload");
	String filePath = getServletContext().getRealPath(path) + "/";

	String contentType = request.getContentType();
	if ((contentType.indexOf("multipart/form-data") >= 0)) {

		DiskFileItemFactory factory = new DiskFileItemFactory();

		factory.setSizeThreshold(maxMemSize);

		factory.setRepository(new File("c:\\temp"));

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(maxFileSize);
		try {

			List fileItems = upload.parseRequest(request);

			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (fi.isFormField()) {
					String fieldValue = fi.getString();
					bdo.append("field" + counter, fieldValue);
					System.out.println(fieldValue);
					counter++;
				}

				else {
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					bdo.append("field" + counter, fileName);
					counter++;
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath
								+ fileName.substring(fileName
										.lastIndexOf("\\")));
					} else {
						file = new File(filePath
								+ fileName.substring(fileName
										.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					bdo.append("field" + counter,
							System.currentTimeMillis());
					products.insert(bdo);
					out.println("<html>");
					out.println("<head><title>Success</title></head>");
					out.println("<body align='center'>Product Added Successfully<br>");
					out.println("<br><a href='Managerhome.jsp'>Back</a>");
					out.println("</html>");

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	} else {
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Image</title>");
		out.println("</head>");
		out.println("<body align = 'center'>");
		out.println("<p>Failed</p>");
		out.println("<br><a href='Managerhome.jsp'>Back</a>");
		out.println("</body>");
		out.println("</html>");
	}
%>