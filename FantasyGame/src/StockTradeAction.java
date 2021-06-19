import java.util.*;

public class StockTradeAction extends Action {
    private List<Stock> stocks;

    public StockTradeAction(List<Stock> stocks) {
        super("trade", "Trade stocks");
        this.stocks = stocks;
    }

    public void perform(Player player, Location location) {
        System.out.println("Welcome to the Stock Exchange!");
        System.out.println("Please view a list of commands available in this exchange by typing \"help\".\n");
        
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("\n[StockExchange]> ");
            command = scanner.nextLine();
            System.out.println();

            if (command.equals("help")) {
                System.out.println("===== Help =====");
                System.out.println("help: View this very helpful help message");
                System.out.println("exit: Exit the stock exchange");
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Wrong command. Type \"help\" to view a list of commands.");
            }
        }

        System.out.println("Bye!");
    }
}
