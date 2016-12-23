	

/** A specialization of the CatalogPage servlet that
 *  displays a page selling three famous kids-book series.
 *  Orders are sent to the OrderPage servlet.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

public class LaptopPage extends CatalogPage {
  public void init() {
    String[] ids = {"MACBOOK", "ASUSLAPTOP", "SONYVIO", "LEVENOLAPTOP" };
    setItems(ids);
    setTitle("All BEST Laptops Available at BestDeals");
  }
}
