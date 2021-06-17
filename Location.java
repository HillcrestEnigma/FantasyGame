/**
 * This is an interface for locations in the games
 * 
 * @version 06-16-2021
 * @author JingKun
 */
public class Location {
    private List<Room> rooms;

    public Location(int seed) {
        rooms = new ArrayList<Room>();
    }

    public Room getRoom(int x, int y) {
        for (Room room:rooms) {
            if (room.getRoomX() == x && room.getRoomY() == y) {
                return room;
            }
        }
        return null;
    }

    /**
     * Method that checks if the player can enter
     * 
     * @param elf
     * @return 
     */
    public boolean canEnter(Elf elf) {
    }
}
