package SAXParser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MyHandler extends DefaultHandler {

    //List to hold Consoles object
    private List<Console> empList = null;
    private Console emp = null;


    //getter method for Console list
    public List<Console> getEmpList() {
        return empList;
    }

    
    boolean bName = false;
    boolean bRetailer = false;
    boolean bImage = false;
	boolean bPrice = false;
	 boolean bCondition = false;
	boolean bType = false;

	
	
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("Console")) {
            //create a new Console and put it in Map
            String id = attributes.getValue("id");
            //initialize Console object and set id attribute
            emp = new Console();
            emp.setId(Integer.parseInt(id));
            //initialize list
            if (empList == null)
                empList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("name")) {
            //set boolean values for fields, will be used in setting Console variables
            bName = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase("retailer")) {
            bRetailer = true;
        } else if (qName.equalsIgnoreCase("image")) {
            bImage = true;
        }else if (qName.equalsIgnoreCase("condition")) {
            bCondition = true;
        }else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Console")) {
            //add Console object to list
            empList.add(emp);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bPrice) {
            //price element, set Console price
            emp.setPrice(Integer.parseInt(new String(ch, start, length)));
            bPrice = false;
        } else if (bName) {
            emp.setName(new String(ch, start, length));
            bName = false;
        } else if (bRetailer) {
            emp.setRetailer(new String(ch, start, length));
            bRetailer = false;
        } else if (bImage) {
            emp.setImage(new String(ch, start, length));
            bImage = false;
        }else if (bCondition) {
            emp.setCondition(new String(ch, start, length));
            bCondition = false;
        }else if (bType) {
            emp.setType(new String(ch, start, length));
            bType = false;
        }
    }
}