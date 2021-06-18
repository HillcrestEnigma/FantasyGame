import java.util.*;

/**
 * This is an interface for locations in the games
 * 
 * @version 06-16-2021
 * @author JingKun
 */
public class Location {
    private List<Room> rooms;

    public Location() {
        rooms = new ArrayList<Room>();
    }

    public boolean addRoom(Room room) {
        for (int i=0; i<rooms.size(); i++) {
            if (rooms.get(i).position.equals(room.position)) return false;
        }
        rooms.add(room);
        return true;
    }

    public Room getRoom(int x, int y) {
        for (Room room:rooms) {
            if (room.position.getRoomX() == x && room.position.getRoomY() == y) {
                return room;
            }
        }
        return null;
    }

    public int getSize() {
        return rooms.size();
    }
}