import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm

/**
 * This class is responsible for constructing levels and coordinating interaction of
 * Elves with Darkrooms.
 *
 *
 */
public class Game {
    private long seed;
    private Random rng;
    private Castle castle;
    private Player player;

    public Game(String seed) {
        // https://www.tutorialspoint.com/java/java_string_hashcode.htm
        this.seed = (long) seed.hashCode();
        this.rng = new Random(this.seed);

        this.castle = new Castle(this.rng.nextLong());
        this.player = new Player("Player", new Position("castle", 0, 0));
    }

    public void play() {
    }
}
