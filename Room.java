/**
 * A room superclass for the different rooms
 * 
 * @version 06-14-2021
 * @author JingKun
 */
public class Room {
    private String id;
    private int x;
    private int y;
    public Room(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
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
                && !id.equals("Entrance")) {
            return false;
        }
        else if (elf.getPos().getLocation().equals("Nowhere") 
                && id.equals("Entrance")) {
            elf.getPos().setLocation("Entrance");
            return true;
        }
        else if (Math.abs(elf.getPos().getRoomX() - x) < 2
                && Math.abs(elf.getPos().getRoomY() - y) < 2) {
            elf.getPos().setRoomX(x);
            elf.getPos().setRoomY(y);
            return true;
        }
        else return false;
    }
    /**
     * Allows the elf to exit the room
     * 
     * @param elf 
     * @return 
     */
    public boolean exit(Elf elf) {
        return elf.getHealth() >= 25;
    }
}
