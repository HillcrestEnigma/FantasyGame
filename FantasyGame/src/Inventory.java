import java.util.List;
import java.util.ArrayList;
/**
 * Inventory class for the various objects and entities
 * 
 * @version 06-18-2021
 * @author Jing Sun & Paul Lee
 */
public class Inventory {
    //Instance variable
    private List<Item> items = new ArrayList<Item>();
    
    /**
     * Sets the item quantity of an item in the inventory
     * 
     * @param itemName
     * @param quantity
     * @return 
     */
    public boolean setItemQuantity(String itemName, int quantity) {
        if (quantity < 0) return false;
        for (int i=0; i<items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                items.get(i).quantity = quantity;
                if (quantity == 0) items.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Updates the item quantity of an item in the inventory
     * 
     * @param itemName
     * @param delta
     * @return 
     */
    public boolean updateItemQuantity(String itemName, int delta) {
        Item item = getItem(itemName);
        if (item == null) return false;
        int targetQuantity = item.quantity + delta;
        return setItemQuantity(itemName, targetQuantity);
    }
    
    /**
     * Adds an item to the inventory
     * 
     * @param item 
     */
    public void addItem(Item item) {
        if (!updateItemQuantity(item.getName(), item.quantity)) {
            items.add(item);
        }
    }
    
    /**
     * Removes an item from the inventory
     * 
     * @param itemName 
     */
    public void removeItem(String itemName) {
        setItemQuantity(itemName, 0);
    }
    
    /**
     * Expands the inventory
     * 
     * @param otherInv 
     */
    public void expand(Inventory otherInv) {
        for (Item item:otherInv.getItems()) {
            addItem(item);
        }
        otherInv.clear();
    }
    
    /**
     * Gives an item to a different inventory
     * 
     * @param otherInv
     * @param itemName
     * @param quantity
     * @return 
     */
    public boolean give(Inventory otherInv, String itemName, int quantity) {
        if (updateItemQuantity(itemName, -quantity)) {
            otherInv.addItem(new Item(itemName, quantity));
            return true;
        } else return false;
    }
    
    /**
     * Clears the inventory
     */
    public void clear() {
        items.clear();
    }
    
    /**
     * Fetches the inventory
     * 
     * @return 
     */
    public List<Item> getItems() {
        return items;
    }
    
    /**
     * Returns the size of the inventory
     * 
     * @return 
     */
    public int size() {
        return items.size();
    }
    
    /**
     * Fetches an item from the inventory
     * 
     * @param itemName
     * @return 
     */
    public Item getItem(String itemName) {
        Item item = null;
        for (int i=0; i<items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                item = items.get(i);
            }
        }
        return item;
    }
}
