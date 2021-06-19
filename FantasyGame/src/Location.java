import java.util.List;
import java.util.ArrayList;
/**
 * This is an superclass for the locations in the game
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Location {
    
    //Instance variables
    private List<Room> rooms;
    private String name;
    
    /**
     * Location constructor
     */
    public Location(String n) {
        rooms = new ArrayList<Room>();
        name = n;
    }
    
    /**
     * Adds rooms to the location
     * 
     * @param room
     * @return 
     */
    public boolean addRoom(Room room) {
        for (int i=0; i<rooms.size(); i++) {
            if (rooms.get(i).position.equals(room.position)) return false;
        }
        rooms.add(room);
        return true;
    }
    
    /**
     * Returns a room from the location when given coordinates
     * 
     * @param x
     * @param y
     * @return 
     */
    public Room getRoom(int x, int y) {
        for (Room room:rooms) {
            if (room.position.getRoomX() == x && room.position.getRoomY() == y) {
                return room;
            }
        }
        return null;
    }
    
    /**
     * Returns the amount of rooms in the location
     * 
     * @return 
     */
    public int getSize() {
        return rooms.size();
    }
    
    /**
     * Generates a map of the location
     * 
     * @param x
     * @param y 
     */
    public void map(int x, int y) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Room room:rooms) {
            if (maxX < room.position.getRoomX()) maxX = room.position.getRoomX();
            if (minX > room.position.getRoomX()) minX = room.position.getRoomX();
            if (maxY < room.position.getRoomY()) maxY = room.position.getRoomY();
            if (minY > room.position.getRoomY()) minY = room.position.getRoomY();
        }
        char[][] map = new char[maxY-minY+1][maxX-minX+1];
        for (int i = 0; i < maxY-minY+1; i++) {
            for (int j = 0; j < maxX-minX+1; j++) {
                map[i][j] = ' ';
            }
        }
        for (Room room:rooms) {
            map[room.position.getRoomY()-minY][room.position.getRoomX()-minX] = 'x';
        }
        map[0-minY][0-minX] = 'E';
        map[y-minY][x-minX] = 'O';
        for (int i = 0; i < map[0].length + 2; i++) System.out.print("-");
        System.out.println();
        for(int i = 0; i < map.length; i++) {
            System.out.print('|');
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println('|');
        }
        for (int i = 0; i < map[0].length + 2; i++) System.out.print("-");
        System.out.println("\nx = Rooms\nO = Your Location\nE = Entrance");
    }
    
    /**
     * Returns the name of the location
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns that the entry requirements are met
     * 
     * @param entity
     * @param verbose
     * @return 
     */
    public boolean entryRequirementMet(Entity entity, boolean verbose) {
        return true;
    }
    
    /**
     * Returns that the exit requirements are met
     * 
     * @param entity
     * @param verbose
     * @return 
     */
    public boolean exitRequirementMet(Entity entity, boolean verbose) {
        return true;
    }
}
