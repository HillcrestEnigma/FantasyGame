import java.util.*;

public class Stock {
    private String ticker;
    private float price;
    private Random rng;
    private int streak = 0;
    private boolean isBull = true;

    public Stock(long seed) {
        rng = new Random(seed);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int idx;
        for (int i=0; i<4; i++) {
            idx = rng.nextInt(26);
            ticker += alphabet.substring(idx, idx+1);
        }
        price = rng.nextInt(190) + 10;
    }

    public void tick() {
        if (streak == 0) streak = rng.nextInt(240) + 60;
        streak--;
        if (isBull) price += rng.nextInt(2);
        else price -= rng.nextInt(2);
    }
}
