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
            enterRoom(loc);
            return true;
        } else return false;
    }

    public boolean canMoveRight(Location loc) {
        return loc.getRoom(position.getRoomX()+1, position.getRoomY()) != null;
    }

    public boolean moveRight(Location loc) {
        if (canMoveRight(loc)) {
            position.moveRight();
            enterRoom(loc);
            return true;
        } else return false;
    }

    public boolean canMoveForward(Location loc) {
        return loc.getRoom(position.getRoomX(), position.getRoomY()-1) != null;
    }

    public boolean moveForward(Location loc) {
        if (canMoveForward(loc)) {
            position.moveUp();
            enterRoom(loc);
            return true;
        } else return false;
    }

    public boolean canMoveBehind(Location loc) {
        return loc.getRoom(position.getRoomX(), position.getRoomY()+1) != null;
    }

    public boolean moveBehind(Location loc) {
        if (canMoveBehind(loc)) {
            position.moveDown();
            enterRoom(loc);
            return true;
        } else return false;
    }

    public void enterRoom(Location loc) {
    }

    public int getHealth() {
        return health;
    }

    public boolean setHealth(int health) {
        if (0 <= health && health <= 100) {
            this.health = health;
            return true;
        } else return false;
    }

    public boolean takeDamage(int damage) {
        return setHealth(health - damage);
    }

    public void teleport(String location) {
        position.setLocation(location);
        position.setRoomX(0);
        position.setRoomY(0);
    }
    
    public boolean stockPotion() { 
        if (position.getLocation().equals("Home")) {
            inventory.setItemQuantity("Potion", 5);
            return true;
        }
        else return false;
    }
}
