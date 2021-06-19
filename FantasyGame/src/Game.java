// https://www.tutorialspoint.com/java/util/java_util_random.htm
// https://stackoverflow.com/questions/732034/getting-unixtime-in-java
import java.util.*;

/**
 * This class is responsible for constructing levels and coordinating interaction of
 * Elves with Darkrooms.  
 *
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Game {
    //Instance variables
    private long seed;
    private Random rng;
    private Castle castle;
    private Home home;
    private StockExchange stockexchange;
    private Player player;
    private List<Asynchronous> asyncList;
    private long asyncLastUpdate;
    
    /**
     * Game constructor
     * 
     * @param seed 
     */
    public Game(String seed) {
        // https://www.tutorialspoint.com/java/java_string_hashcode.htm
        this.seed = (long) seed.hashCode();
        this.rng = new Random(this.seed);

        this.castle = new Castle(this.rng.nextLong());
        this.home = new Home();
        this.stockexchange = new StockExchange(this.rng.nextLong());
        this.player = new Player(new Position("castle", 0, 0));
        this.asyncList = new ArrayList<Asynchronous>();
        this.asyncLastUpdate = System.currentTimeMillis();

        this.asyncList.add(new RivalElf(new Position("castle", 0, 0), this.rng.nextLong()));
    }
    
    /**
     * Returns location when given name
     * 
     * @param name
     * @return 
     */
    private Location getLocationByName(String name) {
        if (name.equals("castle")) {
            return this.castle;
        } else if (name.equals("home")) {
            return this.home;
        } else if (name.equals("stockexchange")) {
            return this.stockexchange;
        } else {
            return null;
        }
    }
    
    /**
     * Plays the game out in the console
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        String command, result;
        List<Asynchronous> newAsync;
        long timeDeltaSinceAsyncLastUpdate;
        Location location = getLocationByName(player.getPosition().getLocation());

        System.out.println("Welcome to Fantasy!");
        System.out.println("Type \"help\" to view a list of commands.\n");

        player.enterRoom(location);
        // player.look(location);

        while (true) {
            //Checks if the player won
            if (player.inventory.getItem("Gold") != null && player.inventory.getItem("Gold").quantity >= 1000) {
                System.out.println("You beat the game!");
                return;
            }
            
            if (!player.isAlive) {
                System.out.println("Your health reached zero and you died!");
                return;
            }

            location = getLocationByName(player.getPosition().getLocation());
            
            newAsync = player.fetchAsynchronous(location);
            if (newAsync != null) {
                for (Asynchronous async:newAsync) {
                    asyncList.add(async);
                }
            }

            timeDeltaSinceAsyncLastUpdate = System.currentTimeMillis() - asyncLastUpdate;
            asyncLastUpdate = System.currentTimeMillis();
            for (int i=0; i < timeDeltaSinceAsyncLastUpdate / 500L; i++) {
                for (Asynchronous async:asyncList) {
                    result = async.tick(player, location);
                    if (result != null) System.out.println("\n" + result);
                }
            }

            System.out.print("\n> ");
            command = scanner.nextLine();
            System.out.println();
            //Commands
            if (command.equals("help")) {
                System.out.println("===== Help =====");
                System.out.println("help: View this very helpful help message");
                System.out.println("exit: Exit the game");
                System.out.println("look: Observe the room you are in");
                System.out.println("map: Displays a map of the castle.");
                System.out.println("status: Check your health, inventory, position, etc.");
                System.out.println("move left: Enter the room to the left");
                System.out.println("move right: Enter the room to the right");
                System.out.println("move forward: Enter the room forward");
                System.out.println("move behind: Enter the room behind");
                System.out.println("goto home: Teleports you to your home");
                System.out.println("goto castle: Teleports you to the castle");
                System.out.println("goto stockexchange: Teleports you to the stock exchange");

                if (player.roomActionExists(location)) {
                    System.out.println();
                    player.printRoomHelp(location);
                }
            } else if (command.equals("exit")) {
                break;
            } else if (command.equals("look")) {
                player.look(location);
            } else if (command.equals("map")) {
                player.printMap(location);
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
            } else if (command.equals("goto home")) {
                player.teleport(getLocationByName("home"));
            } else if (command.equals("goto castle")) {
                player.teleport(getLocationByName("castle"));
            } else if (command.equals("goto stockexchange")) {
                player.teleport(getLocationByName("stockexchange"));
            } else {
                if (!player.executeAction(location, command)) System.out.println("Wrong command. Type \"help\" to view a list of commands.");
            }
        }

        System.out.println("Bye!");
    }
}
