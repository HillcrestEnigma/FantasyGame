import java.util.Scanner;
import java.util.Random; // https://www.tutorialspoint.com/java/util/java_util_random.htm

/**
 * This class is responsible for constructing levels and coordinating interaction of
 * Elves with Darkrooms.  
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

    private Location getLocationByName(String name) {
        if (name.equals("castle")) {
            return this.castle;
        } else {
            return null;
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        String command;
        Location location = getLocationByName(player.getPosition().getLocation());

        System.out.println("Welcome to Fantasy!");
        System.out.println("Type \"help\" to view a list of commands.\n");

        player.enterRoom(location);
        // player.look(location);

        while (true) {
            location = getLocationByName(player.getPosition().getLocation());

            System.out.print("\n> ");
            command = scanner.nextLine();
            System.out.println();

            if (command.equals("help")) {
                System.out.println("===== Help =====");
                System.out.println("help: View this helpful help message");
                System.out.println("exit: Exit the game");
                System.out.println("look: Observe the room you are in");
                System.out.println("status: Check your health, inventory, position, etc.");
                System.out.println("move left: Enter the room to the left");
                System.out.println("move right: Enter the room to the right");
                System.out.println("move forward: Enter the room forward");
                System.out.println("move behind: Enter the room behind");
            } else if (command.equals("exit")) {
                break;
            } else if (command.equals("look")) {
                player.look(location);
            } else if (command.equals("status")) {
                player.printStatus();
            } else if (command.equals("move left")) {
                System.out.println("Moving...\n");
                if (!player.moveLeft(location)) System.out.println("You can't enter the room to the left!");
            } else if (command.equals("move right")) {
                System.out.println("Moving...\n");
                if (!player.moveRight(location)) System.out.println("You can't enter the room to the right!");
            } else if (command.equals("move forward")) {
                System.out.println("Moving...\n");
                if (!player.moveForward(location)) System.out.println("You can't enter the room forward!");
            } else if (command.equals("move behind")) {
                System.out.println("Moving...\n");
                if (!player.moveBehind(location)) System.out.println("You can't enter the room behind!");
            } else {
                System.out.println("Wrong command. Type \"help\" to view a list of commands.");
            }
        }

        System.out.println("Bye!");
    }
}
