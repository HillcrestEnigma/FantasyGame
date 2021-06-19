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
        addRoom(new Room("Home", 0, 0));
    }
}
