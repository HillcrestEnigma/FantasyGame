import java.util.ArrayList;
import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm
class DarkRoom extends Room {
    private boolean radioactive;
	
    /**
     * Constructs a DarkRoom
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

    public DarkRoom(int x, int y, long seed) {
        this(x, y, 5, false);
        Random rng = new Random(seed);
        inventory.addItem(new Item("Gold", rng.nextInt(5)));
        radioactive = rng.nextInt(100) < 10;
    }
	
    /**
     * Have Elf interact with DarkRoom.
     *
     * @param elf
     */
    public boolean enter(Entity entity) {
        return true;
    }

    public void look() {
        System.out.println("The room is very dark, and you make out only a few details.");
    }

    public boolean isRadioactive() {
        return radioactive;
    }
}
