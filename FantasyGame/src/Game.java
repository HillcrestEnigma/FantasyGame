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
    private Mine mine;

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
        this.mine = new Mine(this.rng.nextLong());

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
        } else if (name.equals("mine")) {
            return this.mine;
        } else {
            return null;
        }
    }
    
    /**
     * Plays the game out in the console
     */
    public void play() {
        
        //Variables
        Scanner scanner = new Scanner(System.in);
        String command, result;
        List<Asynchronous> newAsync;
        long timeDeltaSinceAsyncLastUpdate;
        Location location = getLocationByName(player.getPosition().getLocation());
        
        //Header
        System.out.println("Welcome to Fantasy! Win by collecting 1000 gold!");
        System.out.println();
        System.out.println("This castle used to be owned by the Elf King");
        System.out.println("Lochtus, who you are the grandson of. In his will,");
        System.out.println("he left all the gold in his castle to you. You need");
        System.out.println("1000 gold to pay for a new home, so you decide to");
        System.out.println("search his castle, and are currently in a dark room.");
        System.out.println("You've heard some rooms are radioactive. That doesn't");
        System.out.println("seem fun. Make sure to keep yourself above 25% health,");
        System.out.println("or else you will not be able to exit the castle!");
        System.out.println();
        
        //The player enters the room
        player.enterRoom(location);
        System.out.println();
        System.out.println("Type \"help\" to view a list of commands.\n");
        
        while (true) {
            
            //Checks if the player won
            if (player.inventory.getItem("Gold") != null 
                    && player.inventory.getItem("Gold").quantity >= 1000) {
                System.out.println("You beat the game! You'll finally get the dream house you wanted.");
                System.out.println("Who's that?");
                System.out.println("Rival Elf: Hey, mind if you spare me some gold?");
                System.out.println("You: Sorry, I need to get a house for myself.");
                System.out.println("Rival Elf: So you're just gonna leave me on the streets?");
                System.out.println("You: I don't know, I guess we can be roommates.");
                System.out.println("Your former friend thinks for a bit.");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {}
                System.out.println("Rival Elf: Sure, I guess that sounds nice.");
                System.out.println("THE END");
                return;
            }
            else if (player.inventory.getItem("Gold") != null 
                    && player.inventory.getItem("Gold Chunk") != null
                    && player.inventory.getItem("Gold").quantity 
                    + player.inventory.getItem("Gold Chunk").quantity * 10 >= 1000) {
                System.out.println("You beat the game! You'll finally get the dream house you wanted.");
                System.out.println("Who's that?");
                System.out.println("Rival Elf: Hey, mind if you spare me some gold?");
                System.out.println("You: Sorry, I need to get a house for myself.");
                System.out.println("Rival Elf: So you're just gonna leave me on the streets?");
                System.out.println("You: I don't know, I guess we can be roommates.");
                System.out.println("Your former friend thinks for a bit.");
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {}
                System.out.println("Rival Elf: Sure, I guess that sounds nice.");
                System.out.println("THE END");
                return;
            }
            
            //Checks if the player died
            if (!player.isAlive) {
                System.out.println("Your health reached zero and you died!");
                return;
            }
            
            //Retrieves location
            location = getLocationByName(player.getPosition().getLocation());
            
            //Adds asynchronous functions
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
                    result = async.tick(player, getLocationByName(async.getAsynchronousLocationContext()));
                    if (result != null) System.out.println("\n" + result);
                }
            }
            
            //Line for the player to type on
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
                System.out.println("goto mine: Teleports you to the mine");
                System.out.println("drink potion: Drink a potion from your inventory");

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
                if (!player.enteredHome) {
                    System.out.println("You return to your parents' home to restock on some potions.");
                    System.out.println("You hope to move out someday, when you can pay the down payment");
                    System.out.println("on your dream house.");
                    player.enteredHome = true;
                }
                else System.out.println("You return to your parents' home.");
                player.teleport(location, getLocationByName("home"), true);
            } else if (command.equals("goto castle")) {
                System.out.println("You go back to the castle. Maybe there's more gold there?");
                player.teleport(location, getLocationByName("castle"), true);
            } else if (command.equals("goto stockexchange")) {
                if (!player.enteredStockExchange) {
                    System.out.println("You go to the stock exchange. Your friend became broke here,");
                    System.out.println("but maybe you can get rich here instead with some smart investments.");
                    player.enteredStockExchange = true;
                }
                else System.out.println("You go to the stock exchange.");
                player.teleport(location, getLocationByName("stockexchange"), true);
            } else if (command.equals("goto mine")) {
                if (!player.enteredMine) {
                    System.out.println("Both your parents and your friend used to work here. Your parents have");
                    System.out.println("retired, and your friend was laid off after he caught stealing. Normally");
                    System.out.println("visitors looking to mine would be denied, but you think you can pay the");
                    System.out.println("security guard off...");
                    player.enteredMine = true;
                }
                else {
                    System.out.println("You go to the mine for some more gold. Looks like the security guard isn't");
                    System.out.println("too happy though. Maybe some more gold will do the trick?");
                }
                player.teleport(location, getLocationByName("mine"), true);
                
            } else if (command.equals("drink potion")) {
                player.drinkPotion(true);
                if (!player.healed) {
                    System.out.println("This is the first time you've drank a health potion. It tastes strange,");
                    System.out.println("but you immediately feel better. You gain 10 health.");
                    player.healed = true;
                }
            } else {
                if (!player.executeAction(location, command)) System.out.println("Wrong command. Type \"help\" to view a list of commands.");
            }
        }

        System.out.println("Bye!");
    }
}
