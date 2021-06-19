import java.util.*;
/**
 * The action of restocking potions
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class PotionRestockAction extends Action {
    
    /**
     * Constructor for restocking potions
     */
    public PotionRestockAction() {
        super("restock potions", "Restock on potions");
    }
    
    /**
     * Performs the action
     * 
     * @param player
     * @param location 
     */
    @Override
    public void perform(Player player, Location location) {
        
        //Checks if the player has the max amount of potions and if not,
        //restocks it
        if (player.inventory.getItem("Potion") != null && player.inventory.getItem("Potion").quantity > 4) {
            System.out.println("Not restocking potions: You already have 5 or more!");
        } else {
            if (!player.inventory.setItemQuantity("Potion", 5)) {
                player.inventory.addItem(new Item("Potion", 5));
            }
            System.out.println("Restocked on potion!");
        };
    }
}
