import java.util.ArrayList;
import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm

class Castle extends Location {
    public Castle(long seed) {
        Random rng = new Random(seed);
        int goldAmt = rng.nextInt(5) + 5;
        int x = 0;
        int y = 0;
        int direction;
        addRoom(new DarkRoom(0, 0, rng.nextLong()));
        while (getSize() < 100) {
            direction = rng.nextInt(4);
            if (direction == 0) x++;
            else if (direction == 1) x--;
            else if (direction == 2) y++;
            else y--;
            addRoom(new DarkRoom(x, y, rng.nextLong()));
        }

    }
}
