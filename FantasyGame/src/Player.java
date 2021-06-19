import java.util.*;
/**
 * This class represents the sole character of the game.
 *
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Player extends Entity {
    
    //Instance variables
    public boolean enteredHome = false;
    public boolean enteredStockExchange = false;
    public boolean enteredMine = false;
    public boolean healed = false;
    public boolean mined = false;
    public boolean tradedStock = false;
    
    /**
     * Player constructor
     * 
     * @param p 
     */
    public Player(Position p) {
        super("Player", p);
        inventory.addItem(new Item("Potion"));
    }
    
    /**
     * Allows the player to look around in a location
     * 
     * @param location 
     */
    public void look(Location location) {
        Room room = getRoom(location);
        room.look();

        ArrayList<String> directions = new ArrayList<String>();
        if (canMoveLeft(location)) directions.add("to the left");
        if (canMoveRight(location)) directions.add("to the right");
        if (canMoveForward(location)) directions.add("forward");
        if (canMoveBehind(location)) directions.add("behind");
        if (directions.size() == 0) System.out.println("\nThere are no adjacent rooms. Looks like you are surrounded!");
        else System.out.println("\nFrom this room you can enter the room " + Util.humanList(directions) + ".");

        if (isAlive && room.autoPickUpItems()) {
            System.out.println();
            pickUpItems(location);
        }
    }
    
    /**
     * Allows the player to enter a room
     * 
     * @param location 
     */
    @Override
    public void enterRoom(Location location) {
        Room room = getRoom(location);

        room.enter(this, location, true);

        look(location);

        if (roomActionExists(location)) {
            System.out.println("\nThere are Room-specific commands available in this room. Type \"help\" to view them!");
        }
    }
    
    /**
     * Allows the player to pick up items
     * 
     * @param location 
     */
    public void pickUpItems(Location location) {
        System.out.print("You find ");
        List<Item> items = getRoom(location).inventory.getItems();
        if (items.size() > 0) {
            for (int i=0; i<items.size(); i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(items.get(i).quantity + " " + items.get(i).getName());
            }
        } else System.out.print("nothing");
        System.out.println(" in the room.");
        inventory.expand(getRoom(location).inventory);
    }
    
    /**
     * Prints the status of the player, such as its health
     */
    public void printStatus() {
        System.out.println("===== Status =====");
        System.out.println("Health: " + getHealth() + "%");
        System.out.println("Position: Currently in " + getPosition().getLocation() + " at X=" + getPosition().getRoomX() + " Y=" + getPosition().getRoomY() + ".");
        System.out.println("\n===== Inventory =====");
        if (inventory.size() > 0) {
            for (Item item:inventory.getItems()) {
                System.out.println(item.quantity + " " + item.getName());
            }
        } else System.out.println("There is nothing in your inventory. :(");
    }
    
    /**
     * Prints a map of the location the player is currently in
     * 
     * @param location 
     */
    public void printMap(Location location) {
        location.map(getPosition().getRoomX(), getPosition().getRoomY());
    }
    
    /**
     * Checks whether there are room specific actions
     * 
     * @param location
     * @return 
     */
    public boolean roomActionExists(Location location) {
        return getRoom(location).getActions().size() > 0;
    }
    
    /**
     * Prints room specific commands
     * 
     * @param location 
     */
    public void printRoomHelp(Location location) {
        if (roomActionExists(location)) {
            System.out.println("===== Room specific commands =====");
            for (Action action:getRoom(location).getActions()) {
                System.out.println(action.getCommand() + ": " + action.getDescription());
            }
        }
    }
    
    /**
     * Executes room specific actions
     * 
     * @param location
     * @param command
     * @return 
     */
    public boolean executeAction(Location location, String command) {
        for (Action action:getRoom(location).getActions()) {
            if (action.getCommand().equals(command)) {
                action.perform(this, location);
            }
        }
        return false;
    }
    
    /**
     * Fetches asynchronous actions
     * 
     * @param location
     * @return 
     */
    public List<Asynchronous> fetchAsynchronous(Location location) {
        return getRoom(location).registerAsynchronous();
    }
    
}
