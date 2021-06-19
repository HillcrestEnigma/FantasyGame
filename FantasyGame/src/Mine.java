import java.util.Random;
/**
 * The mine location
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Mine extends Location {
    
    //Instance variable
    private long seed;
    
    /**
     * Mine constructor
     * 
     * @param seed 
     */
    public Mine (long seed) {
        super("mine");
        this.seed = seed;
        addRoom(new MiningRoom(0, 0, seed));
    }
    
    /**
     * Retrieves room in mine
     * 
     * @param x
     * @param y
     * @return 
     */
    public Room getRoom(int x, int y) {
        Room room = super.getRoom(x, y);
        if (room != null) return room;

        Random rng;

        int numXCycle = Math.abs(x)*2;
        if (x > 0) numXCycle--;

        rng = new Random(seed);
        long rowSeed = seed;
        for (int i=0; i<numXCycle; i++) rowSeed = rng.nextLong();

        int numYCycle = Math.abs(y)*2;
        if (y > 0) numYCycle--;

        rng = new Random(rowSeed);
        long roomSeed = rowSeed;
        for (int i=0; i<numYCycle; i++) roomSeed = rng.nextLong();

        return new MiningRoom(x, y, roomSeed);
    }
    
    /**
     * Makes sure the user has enough gold to enter the mine
     * 
     * @param entity
     * @param verbose
     * @return 
     */
    public boolean entryRequirementMet(Entity entity, boolean verbose) {
        if (entity.inventory.updateItemQuantity("Gold", -250)) {
            if (verbose) System.out.println("Entrance fee of 250 Gold has been paid. Welcome!\n");
            return true;
        } else {
            if (verbose) System.out.println("You need to pay 250 Gold to enter the mine - Not enough gold.");
            return false;
        }
    }
}
