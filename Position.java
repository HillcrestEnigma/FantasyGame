public class Position {
    private String location;
    private int roomX;
    private int roomY;

    public Position(String loc, int x, int y) {
        location = loc;
        roomX = x;
        roomY = y;
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
}
