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

    public boolean canMoveLeft(Location loc) {
        return loc.getRoom(position.getRoomX()-1, position.getRoomY()) != null;
    }

    public boolean moveLeft(Location loc) {
        if (canMoveLeft(loc)) {
            position.moveLeft();
            return true;
        } else return false;
    }

    public boolean canMoveRight(Location loc) {
        return loc.getRoom(position.getRoomX()+1, position.getRoomY()) != null;
    }

    public boolean moveRight(Location loc) {
        if (canMoveRight(loc)) {
            position.moveRight();
            return true;
        } else return false;
    }

    public boolean canMoveUp(Location loc) {
        return loc.getRoom(position.getRoomX(), position.getRoomY()-1) != null;
    }

    public boolean moveUp(Location loc) {
        if (canMoveUp(loc)) {
            position.moveUp();
            return true;
        } else return false;
    }

    public boolean canMoveDown(Location loc) {
        return loc.getRoom(position.getRoomX(), position.getRoomY()+1) != null;
    }

    public boolean moveDown(Location loc) {
        if (canMoveDown(loc)) {
            position.moveDown();
            return true;
        } else return false;
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
