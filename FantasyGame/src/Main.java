/* 
This is a fantasy game with an elf as a protagonist, trying to get 1000 gold
to buy his dream house and move away from his parents. He starts off in a castle,
where his late grandfather King Lochtus owned, and who left all the gold in the
castle to him. However, there is a rival elf, his former friend who became broke
at the stock exchange. He was thus laid off from the mining company he worked at
when he attempted to steal its gold reserves, as he had no money. His new plan
is to raid the castle before you do and collect all the gold. You must reach 
1000 gold to beat the game, and the castle, the stock exchange, and the mine
will all give you gold. Good luck!
*/
import java.util.Scanner;
/**
 * Runs the game
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Main {
    
    /**
     * Main method
     * 
     * @param args 
     */
    public static void main(String[] args) {
        String seed;
        System.out.print("Enter a seed: ");
        Scanner scanner = new Scanner(System.in);
        seed = scanner.nextLine();

        System.out.println();

        Game game = new Game(seed);
        game.play();
    }
}
