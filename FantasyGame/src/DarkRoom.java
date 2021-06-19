import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm
/**
 * A subclass of Room with extra properties as Castle rooms
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
class DarkRoom extends Room {
    //Instance variable
    private boolean radioactive;
	
    /**
     * Constructs a DarkRoom
     * 
     * @param x The x co-ordinate of the room
     * @param y The y co-ordinate of the room
     * @param goldAmt The amount of the gold.
     * @param radioactive If radioactivity is present
     */
    public DarkRoom(int x, int y, int goldAmt, boolean radioactive) {
        super("Castle", x, y);
	this.radioactive = radioactive;
        inventory.addItem(new Item("Gold", goldAmt));
    }
    /**
     * Overloaded DarkRoom constructor
     * 
     * @param x
     * @param y
     * @param seed 
     */
    public DarkRoom(int x, int y, long seed) {
        this(x, y, 10, false);
        Random rng = new Random(seed);
        inventory.addItem(new Item("Gold", rng.nextInt(10)));
        radioactive = rng.nextInt(100) < 10;
    }
	
    /**
     * Have Elf interact with DarkRoom.
     *
     * @param elf
     */
    @Override
    public boolean enter(Entity entity) {
        return true;
    }
    
    /**
     * Allows the Elf to look around the dark room
     */
    @Override
    public void look() {
        System.out.println("The room is very dark, and you make out only a few details.");
    }
    
    /**
     * Returns whether the DarkRoom is radioactive or not
     * 
     * @return radioactive
     */
    public boolean isRadioactive() {
        return radioactive;
    }
}