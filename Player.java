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
        location.getRoom(getPosition().getRoomX(), getPosition().getRoomY()).look();

        boolean beforePrintFirstDirection = true;
        System.out.print("\nFrom this room you can enter the room ");
        if (canMoveLeft(location)) {
            System.out.print("to the left");
        }
        if (canMoveRight(location)) {
            if (!beforePrintFirstDirection) {
                System.out.print(", ");
                beforePrintFirstDirection = false;
            }
            System.out.print("to the right");
        }
        if (canMoveUp(location)) {
            if (!beforePrintFirstDirection) {
                System.out.print(", ");
                beforePrintFirstDirection = false;
            }
            System.out.print("forward");
        }
        if (canMoveDown(location)) {
            if (!beforePrintFirstDirection) {
                System.out.print(", ");
                beforePrintFirstDirection = false;
            }
            System.out.print("behind");
        }
        System.out.println(".");
    }
}
