import java.util.List;
import java.util.ArrayList;
public class Inventory {
    private List<Item> items = new ArrayList<Item>();

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

    public boolean updateItemQuantity(String itemName, int delta) {
        Item item = getItem(itemName);
        if (item == null) return false;
        int targetQuantity = item.quantity + delta;
        return setItemQuantity(itemName, targetQuantity);
    }

    public void addItem(Item item) {
        if (!updateItemQuantity(item.getName(), item.quantity)) {
            items.add(item);
        }
    }

    public void removeItem(String itemName) {
        setItemQuantity(itemName, 0);
    }

    public void expand(Inventory otherInv) {
        for (Item item:otherInv.getItems()) {
            addItem(item);
        }
        otherInv.clear();
    }

    public boolean give(Inventory otherInv, String itemName, int quantity) {
        if (updateItemQuantity(itemName, -quantity)) {
            otherInv.addItem(new Item(itemName, quantity));
            return true;
        } else return false;
    }

    public void clear() {
        items.clear();
    }

    public List<Item> getItems() {
        return items;
    }

    public int size() {
        return items.size();
    }

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
