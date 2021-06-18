import java.util.ArrayList;
import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm
class DarkRoom extends Room {
    boolean radioactive;
	
    /**
     * Constructs a DarkRoom
     * @param x The x co-ordinate of the room
     * @param y The y co-ordinate of the room
     * @param goldAmt The amount of the gold.
     * @param radioactive If radioactivity is present
     */
    public DarkRoom(int x, int y, int goldAmt, boolean radioactive) {
        super("Castle", x, y);
        this.position = new Position (name, x, y);
	this.radioactive = radioactive;
	this.inventory = new Inventory();
        inventory.addItem(new Item("Gold", goldAmt));
    }

    public DarkRoom(int x, int y, long seed) {
        Random rng = new Random(seed);
        int goldAmt = rng.nextInt(5) + 5;
        boolean radioactive = rng.nextInt(100) < 10;
        this(x, y, goldAmt, radioactive);
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

    public void look() {
        System.out.println("The room is very dark, and you make out only a few details.");
    }
}
