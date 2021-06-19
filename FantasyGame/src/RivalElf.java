import java.util.*;
/**
 * This class represents the sole character of the game.
 *
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class RivalElf extends Entity implements Asynchronous {
    Random rng;
    boolean introducedItself = false;
    /**
     * Player constructor
     * 
     * @param n
     * @param p 
     */
    public RivalElf(Position p, long seed) {
        super("Rival Elf", p);
        rng = new Random(seed);
        inventory.addItem(new Item("Potion"));
    }

    public String tick(Player player, Location location) {
        if (!introducedItself && player.getPosition().equals(this.getPosition())) {
            introducedItself = true;
            return "As you look around, you see another elf looking for gold.\n"
                + "The elf notices your presence and looks up at you.\n"
                + "A sinister smile appears on his face.\n"
                + "\"Oh hey!\", the elf says, \"Looks like you are late. I've just looted this room,\n"
                + "and I am about to loot the entire castle before you do.\"\n"
                + "Before you can say anything, the elf runs into the next room,\n"
                + "and you can hear a sinister laughter in the distance.";
        }
        if (rng.nextInt(5) == 0) {
            int direction = rng.nextInt(4);
            if (direction == 0) moveForward(location);
            else if (direction == 1) moveBehind(location);
            else if (direction == 2) moveRight(location);
            else moveLeft(location);
        }

        if (rng.nextInt(100) == 0) {
            return "You hear a sinister laughter in the distance, somewhere in the castle.";
        }
        return null;
    }
    
    /**
     * Allows the player to enter a room
     * 
     * @param location 
     */
    @Override
    public void enterRoom(Location location) {
        Room room = getRoom(location);
        room.enter(this, location, false);

        pickUpItems(location);
        if (room instanceof DarkRoom) {
            DarkRoom darkroom = (DarkRoom) room;
            darkroom.lootAsRivalElf();
        }
    }
    
    /**
     * Allows the player to pick up items
     * 
     * @param location 
     */
    public void pickUpItems(Location location) {
        List<Item> items = getRoom(location).inventory.getItems();
        inventory.expand(getRoom(location).inventory);
    }
    
    /**
     * Allows the player to drink potions
     * 
     * @param amount
     * @return 
     */
    public boolean drinkPotion(int amount) {
        if (getHealth() == 100) return false;
        else {
            int newHealth = getHealth() + amount * 10;
            if (newHealth > 100) setHealth(100);
            else setHealth(newHealth);
            inventory.updateItemQuantity("Potion", -1 * amount);
            return true;
        }
    }
}
