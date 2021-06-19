import java.util.*;
/**
 * The action of trading stock
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class StockTradeAction extends Action {
    //Instance variables
    private List<Stock> stocks;
    
    /**
     * StockTradeAction constructor
     * @param stocks 
     */
    public StockTradeAction(List<Stock> stocks) {
        super("trade", "Trade stocks");
        this.stocks = new ArrayList<Stock>();
        for (Stock stock:stocks) this.stocks.add(stock);
    }
    
    /**
     * Performs the action
     * 
     * @param player
     * @param location 
     */
    @Override
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
            } else if (command.equals("portfolio")) {
                int numStockTypesOwned = 0;
                int total = 0;
                System.out.println("Ticker\tQuantity\tValue");
                System.out.println("=============================");
                for (Stock stock:stocks) {
                    Item share = player.inventory.getItem(stock.getTicker() + " Share");
                    if (share != null) {
                        numStockTypesOwned++;
                        System.out.println(stock.getTicker() + "\t" + share.quantity + "\t\t" + stock.getPrice() * share.quantity);
                        total += stock.getPrice() * share.quantity;
                    }
                }
                if (numStockTypesOwned > 0) System.out.println("\nTotal: " + total + " Gold");
                else System.out.println("You do not own any shares at this time.");
                System.out.println("\nAll prices are in gold.");
            } else if (command.equals("prices")) {
                System.out.println("Ticker: Price");
                System.out.println("=============");
                for (Stock stock:stocks) {
                    System.out.println(stock.getTicker() + ": " + stock.getPrice());
                }
                System.out.println("\nAll prices are in gold.");
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
            } else if (command.equals("sell")) {
                System.out.print("Please enter the ticker of the stock you wish to sell: ");
                ticker = scanner.nextLine();
                for (int i=0; i<stocks.size()+1; i++) {
                    if (i == stocks.size()) {
                        System.out.println("\nTicker not found!");
                    } else if (stocks.get(i).getTicker().equals(ticker)) {
                        System.out.print("\nSelling one share of " + ticker + "... ");
                        boolean successful = player.inventory.updateItemQuantity(ticker + " Share", -1);
                        if (successful) {
                            if (!player.inventory.updateItemQuantity("Gold", stocks.get(i).getPrice())) {
                                player.inventory.addItem(new Item("Gold", stocks.get(i).getPrice()));
                            }
                            System.out.println("Successful!");
                        } else System.out.println("You do not own a share of " + ticker + ".");
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
