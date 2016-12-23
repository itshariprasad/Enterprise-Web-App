import java.util.HashMap;

public class ProductStore {
	
	 private static HashMap<String, Products> products = new HashMap<String, Products>();

	public static HashMap<String, Products> getProducts() {
		return products;
	}
	 
 public ProductStore() {
	        
	products.put("Iphone 7", new Products("1", "Iphone 7","800"));
	products.put("Samsung Galaxy Note5", new Products("2", "Samsung Galaxy Note5","500"));
	products.put("Nexus 7", new Products("3", "Nexus 7","600"));
	products.put("Sony Xperia", new Products("4", "Sony Xperia","550"));
	products.put("iPad Pro", new Products("5", "iPad Pro","600"));
	products.put("Samsung Galaxy Tab 4", new Products("6", "Samsung Galaxy Tab 4","550"));
	products.put("Nexus Tab 9", new Products("7", "Nexus Tab 9","599"));
	products.put("Lenovo Tab 2", new Products("8", "Lenovo Tab 2","499"));
	products.put("MacBook Air", new Products("9", "MacBook Air","1700"));
	products.put("Lenovo Laptop", new Products("10", "Lenovo Laptop","1300.99"));
	products.put("Dell Inspiron", new Products("11", "Dell Inspiron","700"));
	products.put("Asus Laptop", new Products("12", "Asus Laptop","899"));
	products.put("LG 4K Ultra HD Smart LED TV", new Products("13", "LG 4K Ultra HD Smart LED TV","3000"));
	products.put("Samsung Curved 4K Ultra HD Smart LED TV", new Products("14", "Samsung Curved 4K Ultra HD Smart LED TV","3999"));
	products.put("Toshiba LED 1080p HDTV", new Products("15", "Toshiba LED 1080p HDTV","1600"));
	
	products.put("Sony 4K Ultra HD Smart LED TV", new Products("16", "Sony 4K Ultra HD Smart LED TV","3999"));
	products.put("LG headphones", new Products("17", "LG headphones","100"));
	products.put("Ligthening Fast Charger", new Products("18", "Ligthening Fast Charger","50"));
	products.put("Blu-ray player 3D", new Products("19", "Blu-ray player 3D","349.99"));
}

}
