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
        if (canMoveUp(location)) {
            if (!beforePrintFirstDirection) System.out.print(", ");
            System.out.print("forward");
            beforePrintFirstDirection = false;
        }
        if (canMoveDown(location)) {
            if (!beforePrintFirstDirection) System.out.print(", ");
            System.out.print("behind");
            beforePrintFirstDirection = false;
        }
        System.out.println(".\n");

        pickUpItems(location);
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
}
