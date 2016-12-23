package beans.HotelDetails;

import java.util.ArrayList;


public class HotelPredictions {

	public HotelPredictions(){};
	
	ArrayList<HotelDetails> predictions;
	String status;
	
	public ArrayList<HotelDetails> getPredictions() {
		return predictions;
	}

	public void setPredictions(ArrayList<HotelDetails> predictions) {
		this.predictions = predictions;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}


