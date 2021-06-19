import java.util.Random;
/**
 * A room where the player can mine
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class MiningRoom extends Room {
    //Instance variable
    private boolean radioactive;
    
    /**
     * Mining room constructor
     * 
     * @param x
     * @param y
     * @param goldAmt
     * @param chunk
     * @param radioactive 
     */
    public MiningRoom(int x, int y, int goldAmt, int chunks, boolean radioactive) {
        super("mine", x, y);
        this.radioactive = radioactive;
        inventory.addItem(new Item("Gold", goldAmt));
        inventory.addItem(new Item("Gold Chunk", chunks));
    }
    
    /**
     * Overloaded mining room constructor
     * 
     * @param x
     * @param y
     * @param seed 
     */
    public MiningRoom(int x, int y, long seed) {
        this(x, y, 20, 1, false);
        Random rng = new Random(seed);
        inventory.addItem(new Item("Gold", rng.nextInt(20)));
        inventory.addItem(new Item("Gold Chunk", rng.nextInt(1)));
        radioactive = rng.nextInt(100) < 25;
    }
    
    /**
     * Have Elf interact with MiningRoom
     *
     * @param elf
     */
    @Override
    public boolean enter(Entity entity, Location location, boolean verbose) {
        location.addRoom(this);
        if (radioactive) {
            entity.takeDamage(5, location);
            if (verbose) System.out.println("You feel the radioactivity in the room. You take 5% health damage. Your new health level is " + entity.getHealth() + "%.\n");
        }
        return true;
    }
    
    /**
     * Allows the Elf to look around the mining room
     */
    @Override
    public void look() {
        System.out.println("You see a lot of gold...is that a chunk of gold?");
    }
    
    /**
     * Returns whether the MiningRoom is radioactive or not
     * 
     * @return radioactive
     */
    public boolean isRadioactive() {
        return radioactive;
    }

    public boolean autoPickUpItems() {
        return false;
    }
 }
