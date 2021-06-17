import java.util.ArrayList;
class DarkRoom extends Room {
    boolean radioactive;
	
    /**
     * Constructs a DarkRoom
     * @param name The name of the room.
     * @param x The x co-ordinate of the room
     * @param y The y co-ordinate of the room
     * @param goldAmt The amount of the gold.
     * @param radioactive If radioactivity is present
     */
    public DarkRoom(String name, int x, int y, boolean radioactive) {
        super(name, x, y);
        this.position = new Position (name, x, y);
	this.radioactive = radioactive;
	this.inventory = new Inventory();
        inventory.addItem(new Item("Gold"));
        inventory.setItemQuantity("Gold", goldAmt);
    }
	
    /**
     * Have Elf interact with DarkRoom.
     *
     * @param elf
     */
    public boolean enter(Elf elf) {
        if(elf.getPos().getLocation().equals("Nowhere") 
                && !pos.getLocation().equals("Entrance")) {
            return false;
        }
        else if (elf.getPos().getLocation().equals("Nowhere") 
                && pos.getLocation().equals("Entrance")) {
            elf.getPos().setLocation("Entrance");
		elves.add(elf);
            return true;
        }
        else if (Math.abs(elf.getPos().getRoomX() - pos.getRoomX()) < 2
                && Math.abs(elf.getPos().getRoomY() - pos.getRoomY()) < 2) {
            elf.getPos().setRoomX(pos.getRoomX());
            elf.getPos().setRoomY(pos.getRoomY());
            return true;
        }
        else return false;
    }
}
