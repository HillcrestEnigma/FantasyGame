import java.util.*;
/**
 * A subclass of Room with extra properties as StockExchange rooms
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
class TradingFloor extends Room {
    
    //Instance variables
    Random rng;
    List<Stock> stocks;
    
    /**
     * Constructs a TradingFloor
     * 
     * @param x The x co-ordinate of the room
     * @param y The y co-ordinate of the room
     * @param seed
     */
    public TradingFloor(int x, int y, long seed) {
        super("stockexchange", x, y);
        Random rng = new Random(seed);

        stocks = new ArrayList<Stock>();
        for (int i=0; i<20; i++) {
            stocks.add(new Stock(rng.nextLong()));
        }

        addAction(new StockTradeAction(stocks));
    }

    /**
     * Have Elf interact with DarkRoom.
     *
     * @param elf
     */
    @Override
    public boolean enter(Entity entity, Location location, boolean verbose) {
        return true;
    }
    
    /**
     * Allows the Elf to look around the dark room
     */
    @Override
    public void look() {
        System.out.println("A lot is going on this place with lots of computers—yet not one other Elf in sight!");
    }
    
    /**
     * Registers asynchronous actions
     * 
     * @return 
     */
    @Override
    public List<Asynchronous> registerAsynchronous() {
        ArrayList<Asynchronous> result = new ArrayList<Asynchronous>();
        for (int i=stocks.size()-1; i>=0; i--) {
            result.add(stocks.remove(i));
        }
        return result;
    }
}
