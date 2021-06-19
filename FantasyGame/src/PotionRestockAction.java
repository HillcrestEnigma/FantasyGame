import java.util.*;

public class PotionRestockAction extends Action {
    public PotionRestockAction() {
        super("restock potions", "Restock on potions");
    }

    public void perform(Player player, Location location) {
        if (player.inventory.getItem("Potion").quantity > 4) {
            System.out.println("Not restocking potions: You already have 5 or more!");
        } else {
            player.inventory.setItemQuantity("Potion", 5);
            System.out.println("Restocked on potion!");
        };
    }
}
