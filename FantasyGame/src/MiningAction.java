import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 * The action of mining
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class MiningAction extends Action {
    
    /**
     * Constructor for mining
     */
    public MiningAction() {
        super("mine", "mines in mining rooms");
    }
    
    /**
     * Allows the player to mine
     */
    @Override
    public void perform(Player player, Location location) {
        Random rng = new Random();
        System.out.print("You survey the area for valuables, and there is ");
        List<Item> items = new ArrayList<Item>();
        int quantity = 0;
        int chunkQuantity = 0;
        boolean empty = true;
        for (Item item:player.getRoom(location).inventory.getItems()) {
            if (item.getName().equals("Gold") ||
                    item.getName().equals("Gold Chunk")) items.add(item);
        }
        if (items.size() > 0) {
            for (int i=0; i<items.size(); i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(items.get(i).quantity + " " + items.get(i).getName());
                quantity += items.get(i).quantity;
                if (items.get(i).getName().equals("Gold Chunk")) 
                    chunkQuantity = items.get(i).quantity;
                if (items.get(i).quantity != 0) empty = false;
            }
        } else System.out.print("nothing");
        System.out.println(" in the room.");
        if (items.size() != 0) {
            System.out.print("You start mining...");
            int ore = rng.nextInt(quantity);
            if (ore < chunkQuantity) {
                try {
                    Thread.sleep(5000);
                    player.inventory.addItem(new Item("Gold Chunk"));
                    player.getRoom(location).inventory.updateItemQuantity("Gold Chunk", -1);
                    System.out.println("And find a gold chunk!");
                }
                catch (Exception e) {}
            }
            else {
                try {
                    Thread.sleep(1000);
                    player.inventory.addItem(new Item("Gold"));
                    player.getRoom(location).inventory.updateItemQuantity("Gold", -1);
                    System.out.println("and get a piece of gold.");
                }
                catch (Exception e) {}
            }
        }
        if (empty) player.getRoom(location).inventory.clear();
    }
}
