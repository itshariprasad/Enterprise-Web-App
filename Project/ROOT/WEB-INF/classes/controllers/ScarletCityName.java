package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.HotelDetails.HotelDetails;
import beans.HotelDetails.HotelPredictions;

@WebServlet("/cityName")
public class ScarletCityName extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ArrayList<String> scarletCityNames = new ArrayList<String>();
		String cityname = request.getParameter("cityName");
		if(cityname.split(" ").length != 1)
			return;
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			URL url = new URL("https://maps.googleapis.com/maps/api/place/autocomplete"
					+ "/json?input="+cityname+"&key=AIzaSyBi2YaZ0ae8s9eMrDEBpSKyKoHTc3CSxME");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			if (connection.getResponseCode() != 200) 
			{
				throw new RuntimeException("Failed : HTTP error code : "
						+ connection.getResponseCode());
			}

			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(
				(connection.getInputStream())));

			String a = "";
			String b;
			while ((b = bufferReader.readLine()) != null) {
				a = a + "\r\n" + b;
			}
			HotelPredictions hotelpredictions = mapper.readValue(a, HotelPredictions.class);
			for(HotelDetails hoteldetails : hotelpredictions.getPredictions())
			{
				scarletCityNames.add(hoteldetails.getHotelDescription());
				
			}
			connection.disconnect();
		  } 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(mapper.writeValueAsString(scarletCityNames));
	}
}