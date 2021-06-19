import java.util.Random;
/**
 * The mine location
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Mine extends Location {
    public Mine (long seed) {
        super("mine");
        Random rng = new Random(seed);
        int x = 0;
        int y = 0;
        int direction;
        addRoom(new DarkRoom(0, 0, rng.nextLong()));
        while (getSize() < 25) {
            direction = rng.nextInt(4);
            if (direction == 0) x++;
            else if (direction == 1) x--;
            else if (direction == 2) y++;
            else y--;
            addRoom(new DarkRoom(x, y, rng.nextLong()));
        }
    }
}
