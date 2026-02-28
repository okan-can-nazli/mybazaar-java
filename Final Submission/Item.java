public abstract class Item {
    protected static int nextID = 1;
    protected int itemID;
    protected double price;

    public Item(double price) {
        this.itemID = nextID++;
        this.price = price;
    }

    public abstract String displayInfo();

    public String getTypeName() {
        return this.getClass().getSimpleName();
    }

    public int getItemID() {
        return itemID;
    }

    public double getPrice() {
        return price;
    }

    public static void setNextID(int id) {
        nextID = id;
    }
}