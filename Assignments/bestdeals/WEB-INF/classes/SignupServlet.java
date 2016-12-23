import java.io.*;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession; 
import java.io.*;


import java.util.*;

public class SignupServlet extends HttpServlet implements java.io.Serializable {  
        protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException { 
								
									
            response.setContentType("text/html"); 			
            PrintWriter out=response.getWriter(); 
			
			File file= new File("Usernames.txt");
			
			HashMap mapOutFile=new HashMap<String,String>();
			HashMap<String,String> h=new HashMap<String,String>();
			String username = request.getParameter("userid");
			
			
			String password = request.getParameter("password");
			
			h.put(username,password);
			if(!file.exists()){
    			file.createNewFile();				
    		} else{
						
			try{
    			FileInputStream fis=new FileInputStream(file);
    			ObjectInputStream pw=new ObjectInputStream(fis);
    			mapOutFile = (HashMap<String,String>)pw.readObject();
    			for(Map.Entry<String, String> entry :h.entrySet() ){
    				String k = entry.getKey();
    				String v = entry.getValue();
    				
    				mapOutFile.put(k, v);
    			}
    			pw.close();
    			fis.close();    			
    			}
    			catch (Exception ex) {
    		         ex.printStackTrace();
    		      }
    		}
			
			try{
			FileOutputStream fos=new FileOutputStream(file,false);
			ObjectOutputStream pw=new ObjectOutputStream(fos);
			
			pw.writeObject(mapOutFile);
			
        pw.flush();
        pw.close();
        fos.close();
		} catch (Exception ex) {
         ex.printStackTrace();
      }
			response.sendRedirect("login.jsp");
		    //session.invalidate();  
            out.close();  
    }  
}  



