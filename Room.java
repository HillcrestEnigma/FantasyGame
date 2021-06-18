/**
 * A room superclass for the different rooms
 * 
 * @version 06-14-2021
 * @author JingKun
 */
public class Room {
    public Inventory inventory;
    public Position position;
    public Room(String location, int x, int y) {
        this.position = new Position(location, x, y);
        this.inventory = new Inventory();
    }
    /**
     * Allows the elf to enter the room and perform actions in it, if it can.
     * If the elf can't, return false
     * 
     * @param elf 
     * @return
     */
    public boolean enter(Entity entity) {
        return true;
    }

    public void look() {
        System.out.println("You look around... and see nothing.");
    }
}
