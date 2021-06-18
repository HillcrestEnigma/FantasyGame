public class Entity {
    private Position position;
    private String name;
    public Inventory inventory = new Inventory();
    private int health = 100;

    public Entity(String n, Position p) {
        name = n;
        position = p;
    }

    public Position getPosition() {
        return position;
    }

    public Room getRoom(Location location) {
        return location.getRoom(position.getRoomX(), position.getRoomY());
    }

    public boolean canMoveLeft(Location loc) {
        return loc.getRoom(position.getRoomX()-1, position.getRoomY()) != null;
    }

    public boolean moveLeft(Location loc) {
        if (canMoveLeft(loc)) {
            position.moveLeft();
            enterRoom(location);
            return true;
        } else return false;
    }

    public boolean canMoveRight(Location loc) {
        return loc.getRoom(position.getRoomX()+1, position.getRoomY()) != null;
    }

    public boolean moveRight(Location loc) {
        if (canMoveRight(loc)) {
            position.moveRight();
            enterRoom(location);
            return true;
        } else return false;
    }

    public boolean canMoveForward(Location loc) {
        return loc.getRoom(position.getRoomX(), position.getRoomY()-1) != null;
    }

    public boolean moveForward(Location loc) {
        if (canMoveForward(loc)) {
            position.moveUp();
            enterRoom(location);
            return true;
        } else return false;
    }

    public boolean canMoveBehind(Location loc) {
        return loc.getRoom(position.getRoomX(), position.getRoomY()+1) != null;
    }

    public boolean moveBehind(Location loc) {
        if (canMoveDown(loc)) {
            position.moveDown();
            enterRoom(location);
            return true;
        } else return false;
    }

    public void enterRoom() {
    }

    public int getHealth() {
        return health;
    }

    public void teleport(String location) {
        position.setLocation(location);
        position.setRoomX(0);
        position.setRoomY(0);
    }
}
