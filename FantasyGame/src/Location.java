import java.util.List;
import java.util.ArrayList;
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
    
    public void map(int x, int y) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Room room:rooms) {
            if (maxX < room.position.getRoomX()) maxX = room.position.getRoomX();
            else if (minX > room.position.getRoomX()) minX = room.position.getRoomX();
            if (maxY < room.position.getRoomY()) maxY = room.position.getRoomY();
            else if (minY > room.position.getRoomY()) minY = room.position.getRoomY();
        }
        char[][] map = new char[maxY-minY+1][maxX-minX+1];
        for (int i = 0; i < maxY-minY+1; i++) {
            for (int j = 0; j < maxX-minX+1; j++) {
                map[i][j] = '.';
            }
        }
        for (Room room:rooms) {
            map[room.position.getRoomY()-minY][room.position.getRoomX()-minX] = 'x';
        }
        map[0-minY][0-minX] = 'E';
        map[y-minY][x-minX] = 'O';
        System.out.println("===== Map =====");
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("\nx = Rooms\nO = Your Location\nE = Entrance");
    }
}
