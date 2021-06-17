/**
 * A room superclass for the different rooms
 * 
 * @version 06-14-2021
 * @author JingKun
 */
public class Room {
    private int gold;
    private boolean radioactive;
    private String name;
    private int x;
    private int y;
    public Room(String name, int x, int y, int gold, boolean radioactive) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.gold = gold,
        this.radioactive = radioactive;
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
