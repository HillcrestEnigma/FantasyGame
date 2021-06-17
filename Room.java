/**
 * A room superclass for the different rooms
 * 
 * @version 06-14-2021
 * @author JingKun
 */
public class Room {
    public Inventory inventory;
    private String name;
    public Position pos;
    public Room(String name, String location, int x, int y) {
        this.name = name;
        this.pos = new Position(location, x, y);
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
                && !name.equals("Entrance")) {
            return false;
        }
        else if (elf.getPos().getLocation().equals("Nowhere") 
                && name.equals("Entrance")) {
            elf.getPos().setLocation("Entrance");
            return true;
        }
        else if (Math.abs(elf.getPos().getRoomX() - x) < 2
                && Math.abs(elf.getPos().getRoomY() - y) < 2) {
            elf.getPos().setLocation("Room");
            elf.getPos().setRoomX(x);
            elf.getPos().setRoomY(y);
            return true;
        }
        else return false;
    }
}
