/**
 * A class that represents a co-ordinate in the game
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Position {
    //Instance variables
    private String location;
    private int roomX;
    private int roomY;
    
    /**
     * Position constructor
     * 
     * @param loc
     * @param x
     * @param y 
     */
    public Position(String loc, int x, int y) {
        location = loc;
        roomX = x;
        roomY = y;
    }
    
    /**
     * Overloaded Position constructor
     * 
     * @param loc 
     */
    public Position(String loc) {
        this(loc, 0, 0);
    }
    
    /**
     * Retrieves the location of a position
     * 
     * @return 
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Retrieves the x value of a room
     * 
     * @return 
     */
    public int getRoomX() {
        return roomX;
    }
    
    /**
     * Retrieves the y value of a room
     * 
     * @return 
     */
    public int getRoomY() {
        return roomY;
    }
    
    /**
     * Sets the location of a position
     * 
     * @param loc 
     */
    public void setLocation(String loc) {
        location = loc;
    }
    
    /**
     * Sets the x value of a room
     * 
     * @param x 
     */
    public void setRoomX(int x) {
        roomX = x;
    }
    
    /**
     * Sets the y value of a room
     * 
     * @param y 
     */
    public void setRoomY(int y) {
        roomY = y;
    }
    
    /**
     * Moves an entity left
     */
    public void moveLeft() {
        roomX--;
    }
    
    /**
     * Moves an entity right
     */
    public void moveRight() {
        roomX++;
    }
    
    /**
     * Moves an entity forward
     */
    public void moveForward() {
        roomY--;
    }
    
    /**
     * Moves an entity behind
     */
    public void moveBehind() {
        roomY++;
    }
    
    /**
     * Checks whether one position is equivalent to another position
     * 
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) return false;
        Position that = (Position) other;
        return this.location.equals(that.getLocation()) && this.roomX == that.getRoomX() && this.roomY == that.getRoomY();
    }
}
