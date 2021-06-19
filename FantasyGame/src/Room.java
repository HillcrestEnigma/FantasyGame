import java.util.*;

/**
 * A room superclass for the different rooms
 * 
 * @version 06-18-2021
 * @author JingKun
 */
public class Room {
    //Instance variables
    public Inventory inventory;
    public Position position;
    private List<Action> actions;
    
    /**
     * Room constructor
     * 
     * @param location
     * @param x
     * @param y 
     */
    public Room(String location, int x, int y) {
        this.position = new Position(location, x, y);
        this.inventory = new Inventory();
        this.actions = new ArrayList<Action>();
    }
    
    /**
     * Allows the elf to enter the room and perform actions in it, if it can.
     * 
     * @param elf 
     * @return
     */
    public boolean enter(Entity entity, Location location, boolean verbose) {
        return true;
    }
    
    /**
     * Allows a player to look around in a room
     */
    public void look() {
        System.out.println("You look around... and see nothing.");
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public List<Action> getActions() {
        return actions;
    }
}
