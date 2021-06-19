import java.util.ArrayList;
import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm
/**
 * This is a Castle Location that generates rooms based on a seed
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
class Castle extends Location {
    /**
     * Generates the rooms of the castle based on a given seed
     * 
     * @param seed 
     */
    public Castle(long seed) {
        Random rng = new Random(seed);
        int goldAmt = rng.nextInt(10) + 10;
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
