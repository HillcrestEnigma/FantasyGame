/**
 * A subclass of Room with extra properties as rooms in the home
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
class LivingRoom extends Room {
    
    /**
     * Constructs a LivingRoom
     * 
     * @param x The x co-ordinate of the room
     * @param y The y co-ordinate of the room
     */
    public LivingRoom(int x, int y) {
        super("home", x, y);

        addAction(new PotionRestockAction());
    }
	
    /**
     * Have Elf interact with LivingRoom
     *
     * @param elf
     */
    @Override
    public boolean enter(Entity entity, Location location, boolean verbose) {
        return true;
    }
    
    /**
     * Allows the Elf to look around the living room
     */
    @Override
    public void look() {
        System.out.println("The room is very familiar—it's your home!");
    }
}
