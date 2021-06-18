public class Item {
    private String name;
    public int quantity;

    public Item(String n, int q) {
        name = n;
        quantity = q;
    }

    public Item(String n) {
        this(n, 1);
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Item)) return false;
        Item that = (Item) other;
        return this.name.equals(that.name);
    }
}
