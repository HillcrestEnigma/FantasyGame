import java.util.*;

/**
 * Utility class
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Util {
    
    /**
     * Returns the list of humans
     * 
     * @param items
     * @return 
     */
    public static String humanList(List<String> items) {
        String result = "";
        if (items.size() == 0) return result;
        else result = items.get(0);
        if (items.size() == 1) return result;
        for (int i=1; i<items.size()-1; i++) {
            result += ", " + items.get(i);
        }
        result += ", and " + items.get(items.size()-1);
        return result;
    }
}
