/**
 * StockExchange location
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class StockExchange extends Location {
    
    /**
     * StockExchange constructor
     */
    public StockExchange(long seed) {
        super("stockexchange");
        addRoom(new TradingFloor(0, 0, seed));
    }
}
