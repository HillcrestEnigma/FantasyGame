import java.util.*;
/**
 * Allows for stocks
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Stock implements Asynchronous {
    //Instance variables
    private String ticker;
    private int price;
    private Random rng;
    private int streak = 0;
    private boolean isBull = true;
    
    /**
     * Stock constructor
     * 
     * @param seed 
     */
    public Stock(long seed) {
        rng = new Random(seed);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx;
        ticker = "";
        for (int i=0; i<4; i++) {
            idx = rng.nextInt(26);
            ticker += alphabet.substring(idx, idx+1);
        }
        price = rng.nextInt(190) + 10;
    }
    
    /**
     * Ticks for stocks
     * 
     * @param player
     * @param location
     * @return 
     */
    public String tick(Player player, Location location) {
        if (streak == 0) streak = rng.nextInt(240) + 60;
        streak--;
        if (isBull) price += rng.nextInt(2);
        else price -= rng.nextInt(2);
        return null;
    }
    
    /**
     * Returns the stock ticker
     * 
     * @return 
     */
    public String getTicker() {
        return ticker;
    }
    
    /**
     * Returns the stock price
     * 
     * @return 
     */
    public int getPrice() {
        return price;
    }

    public String getAsynchronousLocationContext() {
        return "stockexchange";
    }
}
