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
    public boolean enter(Elf elf) {
        if(elf.getPos().getLocation().equals("Nowhere") 
                && !position.getLocation().equals("Entrance")) {
            return false;
        }
        else if (elf.getPos().getLocation().equals("Nowhere") 
                && position.getLocation().equals("Entrance")) {
            elf.getPos().setLocation("Entrance");
            return true;
        }
        else if (Math.abs(elf.getPos().getRoomX() - position.getRoomX()) < 2
                && Math.abs(elf.getPos().getRoomY() - position.getRoomY()) < 2) 
        {
            elf.getPos().setLocation("Room");
            elf.getPos().setRoomX(position.getRoomX());
            elf.getPos().setRoomY(position.getRoomY());
            return true;
        }
        else return false;
    }

    public void look() {
        System.out.println("You look around... and see nothing.");
    }
}
