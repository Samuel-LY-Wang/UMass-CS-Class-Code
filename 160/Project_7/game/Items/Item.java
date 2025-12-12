package game.Items;

public class Item {
    protected final int id;
    protected final String name;
    protected final String description;
    protected final int frequency; // relative frequency
    protected final int price; // I'll just set as gold probably, also an int
    public Item(int id, String name, String description, int price, int frequency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.frequency = frequency;
    }
}
