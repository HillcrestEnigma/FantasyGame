import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm
/**
 * A subclass of Room with extra properties as Castle rooms
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
class LivingRoom extends Room {
    /**
     * Constructs a DarkRoom
     * 
     * @param x The x co-ordinate of the room
     * @param y The y co-ordinate of the room
     */
    public LivingRoom(int x, int y) {
        super("home", x, y);
    }
	
    /**
     * Have Elf interact with DarkRoom.
     *
     * @param elf
     */
    @Override
    public boolean enter(Entity entity, Location location, boolean verbose) {
        return true;
    }
    
    /**
     * Allows the Elf to look around the dark room
     */
    @Override
    public void look() {
        System.out.println("The room is very familiar—it's your home!");
    }
}
