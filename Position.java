public class Position {
    private String location;
    private int roomX;
    private int roomY;

    public Position(String loc, int x, int y) {
        location = loc;
        roomX = x;
        roomY = y;
    }

    public Position(String loc) {
        this(loc, 0, 0);
    }

    public String getLocation() {
        return location;
    }

    public int getRoomX() {
        return roomX;
    }

    public int getRoomY() {
        return roomY;
    }

    public void setLocation(String loc) {
        location = loc;
    }

    public void setRoomX(int x) {
        roomX = x;
    }

    public void setRoomY(int y) {
        roomY = y;
    }

    public void moveLeft() {
        roomX--;
    }

    public void moveRight() {
        roomX++;
    }

    public void moveUp() {
        roomY--;
    }

    public void moveDown() {
        roomY++;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Position)) return false;
        Position that = (Position) other;
        return this.location.equals(that.getLocation()) && this.roomX == that.getRoomX() && this.roomY == that.getRoomY();
    }
}
