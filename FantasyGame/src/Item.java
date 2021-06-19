/**
 * An Item class for things like health potions
 * 
 * @version 06-18-2021
 * @author Jing Sun @ Paul Lee
 */
public class Item {
    //Instance variables
    private String name;
    public int quantity;
    /**
     * Item constructor
     * 
     * @param n
     * @param q 
     */
    public Item(String n, int q) {
        name = n;
        quantity = q;
    }
    
    /**
     * Overloaded Item constructor
     * 
     * @param n 
     */
    public Item(String n) {
        this(n, 1);
    }
    
    /**
     * Returns the name of an item
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Checks whether an item is equivalent to a different item
     * 
     * @param other
     * @return 
     */
    public boolean equals(Object other) {
        if (!(other instanceof Item)) return false;
        Item that = (Item) other;
        return this.name.equals(that.name);
    }
}
