

/** A catalog that lists the items available in inventory.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class Catalog {
  // This would come from a database in real life.
  // We use a static table for ease of testing and deployment.
  // See JDBC chapters for info on using databases in
  // servlets and JSP pages.
  private static CatalogItem[] items =
    {
		new CatalogItem("DROIDMAXX2","<I>Droid Maxx 2</I> ","Engineered to last for up to 48 hours on a single charge**"+
		" Water resistant coating keeps spills, puddles, and rain from being panic-worthy "+
		" Command the DROID MAXX 2 without touching it—even when it’s asleep "+"Use DROID Zap’s simple gestures to seamlessly share pictures, videos and more ",99.99),
		new CatalogItem("MOTOG4","<I>Moto G4</I>","Moto G4 is ready when you are. It responds to your voice—no touching necessary. Twist your wrist twice, it becomes your camera. Information quietly appears on the screen—it doesn’t interrupt and it’s battery friendly."+
		"It just fits We think your phone should actually fit your hand, not the other way around. So we went ahead and made one that does just that." +
		"Just talk  Moto G4 responds to your voice, no touching necessary. And you can ask it just about anything. ",150.95),
		new CatalogItem("IPHONE6S","<I>Apple 6S</I>","Height: 4.87 inches (123.8 mm) Width: 2.31 inches (58.6 mm) Depth: 0.30 inch (7.6 mm) Weight: 3.95 ounces (112 grams) ",549.99),
        new CatalogItem("IPHONE7","<I>Apple 7</I> ","Height: 4.90 inches (124.4 mm) Width: 2.33 inches (59.2 mm) Depth: 0.35 inch (8.97 mm) Weight: 4.65 ounces (132 grams) ",899.12),
		new CatalogItem("GALAXYS7","<I>Samsung Galaxy S7</I>","4G Network HSDPA 850 / 900 / 1900 / 2100 Android OS, v5.0 2G Network GSM 850 / 900 / 1800 / 1900 Phone supports high speed HSDPA network 16 GB storage,1 GB RAM,Dimensions: 136.6 x 70.6 x 8.6mm ",579.25),
		new CatalogItem("GALAXYS7EDGE","<I>Samsung Galaxy S7 Edge</I>","cellular technology: UMTS screen size: 5 inch screen camera resolution: 13 megapixel camera frequency band: Quad-band screen resolution: 1920 x 1080 weight: 4.6 ounce",689.55),
        new CatalogItem("KINDLETAB","<I>Amazon Kindle </I> ","Touchscreen display that reads like real paper—no screen glare, even in bright sunlight"+
		"Exclusive Kindle features—now includes Goodreads integration, Kindle FreeTime, Vocabulary Builder, Word Wise (coming soon), and more"+
		"Double the on-device storage—holds thousands of books"+
		"Page turns fly with a 20% faster processor"+
		"Lighter than a paperback—fits in your pocket"+
		"Battery lasts weeks, not hours"+
		"Massive book selection, lowest prices—over a million titles less than $4.99"+
		"Download books in less than 60 seconds with built-in Wi-Fi"+
		"Encourage kids to read even more with Kindle FreeTime  ",229.95),
		new CatalogItem("NEXUSTAB","<I>Nexus Tablet</I>","Nexus 10 is the dynamic 10-inch tablet from Google. With the Super High Resolution Screen, all new multi-user features, immersive HD content and the best Google apps -- Nexus 10 has something for everyone",759.95),
		new CatalogItem("SURFACETAB","<I>The new Surface Pro 3 </I>"," This tablet has the power of laptop in a lightweight, versatile form, with a 12 inch display that will replace your other devices for good.",699.95),
        new CatalogItem("GALAXYTAB","<I>Galaxy Tablet</I> ","Samsaung Galaxy Tab 10.1 gives you better experience for work and play.Sharper HD quality screen, better web browsing with flash,better multitasking. ",399.95),
		new CatalogItem("IPAD","<I>I pad Tablet </I>","IPad Air 2 comes with our breakthrough Touch ID technology. It gives you an unprecedented level of security because it uses nature’s most perfect password: your fingerprint. So with just one touch, you can instantly unlock your iPad Air 2. But Touch ID goes beyond that. You can also make secure purchases in iTunes, iBooks, and the App Store. And with Apple Pay, you can unlock an entire world of online shopping that’s fast, convenient, and secure.",599.95),
		new CatalogItem("MACBOOK","<I>MACBOOK Pro </I>","The 13-inch MacBook Air lasts up to 9 hours between charges and the 13-inch model lasts up to an incredible 12 hours. So from your morning coffee till your evening commute, you can work unplugged. When it’s time to kick back and relax, you can get up to 9 hours of iTunes movie playback on the 11-inch model and up to 12 hours on the 13-inch model. And with up to 30 days of standby time, you can go away for weeks and pick up right where you left off.",1599.95),
        new CatalogItem("ASUSLAPTOP","<I>ASUS LAPTOP H Series </I>",
		"4 SO-DIMM slots support up to 32GB RAM"+
		"Dual fans with copper heat sinks keep temperatures low for guaranteed stability"+
		"ASUS TurboMaster GPU overclocking technology delivers 5% performance boost"+
		"Quick-access game keys and additional programmable macro keys for easy access to Steam, game recording and other frequently used commands"+
		"Enhanced audio experience with ROG AudioWizard"+
		"Next-gen 802.11ac wireless",650.95),
        new CatalogItem("SONYVIO","<I>SONY LAPTOP SVD13213CXB</I>","Its the best of both worlds: a full HD laptop and a touchscreen tablet. All in a compact, carbon fiber mobile design thats ready from the word go. Write notes or draw with the digitizer stylus, type an email or use your fingers to pinch, swipe and scroll your way through the web. Equipped with a 4th generation Intel Core i5 processor, 4GB4 of memory and weighing only 2.93 lbs . Its versatility redefined. ",799.95),
        new CatalogItem("LEVENOLAPTOP","<I>LENOVO Think Pad T Series  </I>","With legendary power, reliability, and productivity features, the T Series is a premium option for business. Long-life batteries, military-rated durability and renowned keyboards round out its incredible array of features",704.95),
        new CatalogItem("TOSHIBATV","<I>TOSHIBA Curve 4K TVs </I>","See a curve picture that’s more real world than ever. Displays your movies, sports and shows at 4x the resolution of Full HD and upscales your existing entertainment with incredible details. ",9999.95),
      	new CatalogItem("PANASONICTV","<I>PANASONIC LED TV VIERA TH-60AS700D </I>","Personalized TV experience with my Home Screen Voice Interaction Pro IPS LED Super Bright ",1800.95),
        new CatalogItem("SAMSUNGTV","<I>Samsung UHD 4K TVs </I>","See a picture that’s more lifelike than ever with Samsung Smart UHD. Samsung Ultra High Definition TV displays your movies, sports and shows at 4x the resolution of Full HD and upscales your existing entertainment with incredible detail. ",7999.95),
        new CatalogItem("SONYTV","<I>SONY 4K Ultra HD TV</I>","The Leader in 4K Entertainment. Upgrade to definition 4 times clearer than HD. ",24000.95)
        };

  public static CatalogItem getItem(String itemID) {
    CatalogItem item;
    if (itemID == null) {
      return(null);
    }
    for(int i=0; i<items.length; i++) {
      item = items[i];
      if (itemID.equals(item.getItemID())) {
        return(item);
      }
    }
    return(null);
  }
}
               
