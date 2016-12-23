
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.awt.SunHints.Value;

public class UpdateOrderServlet {

	String ID;
	double qty;
	double finalval;
	
	int iterator;
	int i;
	
	
	public static Map<String, List<Double>> m2 = new HashMap<String, List<Double>>();
	public static Map<String, List<String>> m3 = new HashMap<String, List<String>>();
	public static List<Double> l2 = new ArrayList<>();
	public static List<String> l3 = new ArrayList<>();

	
	public double getQuantity() {
		return qty;
	}
	public void setQuantity(double qty) {
		this.qty = qty;
	}
		
	public int getItr() {
		return iterator;
	}
	public void setItr(int iterator) {
		this.iterator = iterator;
	}
	public String getId() {
		return ID;
	}
	public void setId(String ID) {
		this.ID = ID;
	}
	
	public void setMap(int i){
		List<String> nameval = HashPname.getPn().get(ID);
		List<Double> priceval = HashPprice.getPp().get(getId());
		List<Double> value = m2.get(getId());
		List<String> values = m3.get(getId());
		try{
		if((value.get(i)%priceval.get(i)) == 0 ){
			
			//valu.set(i, (val.get(i)*(getQuantity()+1)));
		}
		else{
		l2.add(priceval.get(i));
		m2.put(getId(), l2);
		l3.add(nameval.get(i));
		m3.put(getId(), l3);
		}
		}catch(Exception e){
			
			l2.add(priceval.get(i));
			m2.put(getId(), l2);
			l3.add(nameval.get(i));
			m3.put(getId(), l3);
			
		}
		}
		
	public void setTotal(){
		
		List<Double> priceval = HashPprice.getPp().get(getId());
		List<String> nameval = HashPname.getPn().get(ID);
		List<Double> value = m2.get(getId());
		List<String> values = m3.get(getId());
		
		finalval = (priceval.get(getItr()))* getQuantity();
		
		values.set(getItr(), nameval.get(getItr()));
		value.set(getItr(), finalval);
		
		System.out.println(m2);
	}
}
