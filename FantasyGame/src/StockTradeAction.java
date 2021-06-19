import java.util.*;

public class StockTradeAction extends Action {
    private List<Stock> stocks;

    public StockTradeAction(List<Stock> stocks) {
        super("trade", "Trade stocks");
        this.stocks = new ArrayList<Stock>();
        for (Stock stock:stocks) this.stocks.add(stock);
    }

    public void perform(Player player, Location location) {
        System.out.println("Loading the automated stock broker...\n");
        System.out.println("Welcome to the Stock Exchange!");
        System.out.println("Please view a list of commands available for this broker by typing \"help\".");
        
        Scanner scanner = new Scanner(System.in);
        String command, ticker;

        while (true) {
            System.out.print("\n[StockBroker]> ");
            command = scanner.nextLine();
            System.out.println();

            if (command.equals("help")) {
                System.out.println("===== Help =====");
                System.out.println("help: View this very helpful help message");
                System.out.println("exit: Exit the stock broker and return to the main game");
                System.out.println("portfolio: View your stock portfolio");
                System.out.println("prices: View stock prices");
                System.out.println("buy: Buy a stock");
                System.out.println("sell: Sell a stock");
            } else if (command.equals("exit")) {
                break;
            } else if (command.equals("prices")) {
                System.out.println("Ticker: Price (Gold)");
                System.out.println("====================");
                for (Stock stock:stocks) {
                    System.out.println(stock.getTicker() + ": " + stock.getPrice());
                }
                System.out.println("\nNote: Prices are not updated when the automated stock broker is in use.");
            } else if (command.equals("buy")) {
                System.out.print("Please enter the ticker of the stock you wish to purchase: ");
                ticker = scanner.nextLine();
                for (int i=0; i<stocks.size()+1; i++) {
                    if (i == stocks.size()) {
                        System.out.println("\nTicker not found!");
                    } else if (stocks.get(i).getTicker().equals(ticker)) {
                        System.out.print("\nBuying one share of " + ticker + "... ");
                        boolean successful = player.inventory.updateItemQuantity("Gold", -stocks.get(i).getPrice());
                        if (successful) {
                            player.inventory.addItem(new Item(ticker + " Share"));
                            System.out.println("Successful!");
                        } else System.out.println("Not enough gold.");
                        break;
                    }
                }
            } else {
                System.out.println("Wrong command. Type \"help\" to view a list of commands for this broker.");
            }
        }

        System.out.println("Bye!\n\nExiting the automated stock broker...");
    }
}
