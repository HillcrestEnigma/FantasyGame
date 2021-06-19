import java.util.*;
/**
 * This class represents the player's enemy
 *
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class RivalElf extends Entity implements Asynchronous {
    //Instance variables
    Random rng;
    boolean introducedItself = false;
    boolean sameRoomPlayerNotice = false;
    boolean exitedCastle = false;
    int noGoldStreak = 0;
    
    /**
     * Rival elf constructor
     * 
     * @param n
     * @param p 
     */
    public RivalElf(Position p, long seed) {
        super("Rival Elf", p);
        rng = new Random(seed);
        inventory.addItem(new Item("Potion"));
    }
    
    /**
     * Allows ticks
     * 
     * @param player
     * @param location
     * @return 
     */
    public String tick(Player player, Location location) {
        if (exitedCastle) return null;
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

        if (!sameRoomPlayerNotice && player.getPosition().equals(this.getPosition())) {
            sameRoomPlayerNotice = true;
            return "You look around, you find the elf yet again.\n"
                + "It looks up, and laughs as it runs into another room.";
        }

        if (getHealth() == 25) {
            if (!drinkPotion()) {
                exitedCastle = true;
                return "You find the elf again.\n"
                    + "\"You are quite a lucky one to have me run out of potions.\",\n"
                    + "the elf says, before teleporting out of the castle.";
            }
        }

        if (noGoldStreak > 19) {
            exitedCastle = true;
            return "You find the elf again.\n"
                + "\"I think I've looted everything in this castle, so see you later!\",\n"
                + "the elf says, before teleporting out of the castle.";
        }

        if (rng.nextInt(5) == 0) {
            int direction = rng.nextInt(4);
            boolean moveResult;
            if (direction == 0) moveResult = moveForward(location);
            else if (direction == 1) moveResult = moveBehind(location);
            else if (direction == 2) moveResult = moveRight(location);
            else moveResult = moveLeft(location);
            if (moveResult) {
                sameRoomPlayerNotice = false;
                Room room = getRoom(location);
                if (room.inventory.size() == 0) noGoldStreak++;
            }
        }

        if (rng.nextInt(100) == 0) {
            return "You hear a sinister laughter in the distance, somewhere in the castle.";
        }
        return null;
    }
    
    /**
     * Allows the rival elf to enter a room
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
     * Allows the rival elf to pick up items
     * 
     * @param location 
     */
    public void pickUpItems(Location location) {
        List<Item> items = getRoom(location).inventory.getItems();
        inventory.expand(getRoom(location).inventory);
    }
}
