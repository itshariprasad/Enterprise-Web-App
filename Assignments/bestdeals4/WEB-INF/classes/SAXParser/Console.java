package SAXParser;

public class Console {
    private int id;
    private String name;
    private String retailer;
    private int price;
    private String image;
	 private String condition;
	  private String type;
	  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRetailer() {
        return retailer;
    }
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
	 public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return this.id+"\n" + this.name + "\n" + this.retailer + "\n" + this.price +
                "\n" + this.image+ "\n" + this.condition +"\n" + this.type;
    }
    
}