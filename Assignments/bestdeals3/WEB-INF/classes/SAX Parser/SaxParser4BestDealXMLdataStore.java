
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 



The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/




package SAX Parser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParser4BestDealXMLdataStore extends DefaultHandler {
    Console console;
    List<Console> consoles;
    String consoleXmlFileName;
    String elementValueRead;

    
    public SaxParser4BestDealXMLdataStore(String consoleXmlFileName) {
        this.consoleXmlFileName = consoleXmlFileName;
        consoles = new ArrayList<Console>();
        parseDocument();
        prettyPrint();
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


    private void prettyPrint() {
	
        for (Console console: consoles) {
            	System.out.println("console #"+ console.id +":");
		System.out.println("\t\t retailer: " + console.retailer);
		System.out.println("\t\t name: " + console.name);
		for (String accessory: console.accessories) {
			System.out.println("\t\t accessory: " + accessory);
		}
        }
    }





////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("console")) {
            console = new Console();
            console.setId(attributes.getValue("id"));
            console.setRetailer(attributes.getValue("retailer"));
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("console")) {
            consoles.add(console);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            console.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            console.setName(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("accessory")){
           console.getAccessories().add(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            console.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////

    public static void main(String[] args) {
        new SaxParser4BestDealXMLdataStore("ProductCatalog.xml");

    }

}
