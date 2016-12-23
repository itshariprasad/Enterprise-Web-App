package SAXParser;
import java.util.HashMap;
import java.util.Map;

public class HashMaSax {

	private static Map<String, String[]> hmsax = new HashMap<String, String[]>();

	public static Map<String, String[]> getHmsax() {
		return hmsax;
	}

	public static void setHmsax(Map<String, String[]> hmst) {
		HashMaSax.hmsax = hmsax;
	}

}
