/**
 * Home location
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Home extends Location {
    
    /**
     * Home constructor
     */
    public Home() {
        super("home");
        addRoom(new LivingRoom(0, 0));
    }
}
