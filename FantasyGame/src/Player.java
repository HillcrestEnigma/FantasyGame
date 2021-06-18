import java.util.*;
/**
 * This class represents the sole character of the game.
 *
 * @version 06-16-2021
 * @author Jing Sun & Paul Lee
 */
public class Player extends Entity {
    public Player(String n, Position p) {
        super(n, p);
    }

    public void look(Location location) {
        getRoom(location).look();

        boolean beforePrintFirstDirection = true;
        System.out.print("\nFrom this room you can enter the room ");
        if (canMoveLeft(location)) {
            System.out.print("to the left");
            beforePrintFirstDirection = false;
        }
        if (canMoveRight(location)) {
            if (!beforePrintFirstDirection) System.out.print(", ");
            System.out.print("to the right");
            beforePrintFirstDirection = false;
        }
        if (canMoveForward(location)) {
            if (!beforePrintFirstDirection) System.out.print(", ");
            System.out.print("forward");
            beforePrintFirstDirection = false;
        }
        if (canMoveBehind(location)) {
            if (!beforePrintFirstDirection) System.out.print(", ");
            System.out.print("behind");
            beforePrintFirstDirection = false;
        }
        System.out.println(".\n");

        pickUpItems(location);
    }

    public void enterRoom(Location location) {
        Room room = getRoom(location);
        if (room instanceof DarkRoom) {
            DarkRoom darkRoom = (DarkRoom) room;
            if (darkRoom.isRadioactive()) {
                takeDamage(5);
                System.out.println("You feel the radioactivity in the room. You take 5% health damage. Your new health level is " + getHealth() + "%.\n");
            }
        }

        look(location);
    }

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
    
    public void printMap(Location location) {
        location.map(getPosition().getRoomX(), getPosition().getRoomY());
    }
    
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
    
    public void stockPotion() {
        inventory.setItemQuantity("Potion", 5);
    }
}
