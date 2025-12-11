package game;

public class StatBuffItem extends Item {
    protected final int numBuffs;
    protected final String[] stats; // "attack", "defense", "maxHealth", "speed", "undos"
    protected final String[] characteristics; // "add", "subtract", "multiply", "divide"
    protected final double[] amounts;

    public StatBuffItem(int id, String name, String description, int price, String[] statsStrings, String[] characteristicsStrings, double[] amountsValues) {
        super(id, name, description, price);
        this.stats = statsStrings;
        this.characteristics = characteristicsStrings;
        this.amounts = amountsValues;
        this.numBuffs = statsStrings.length;
        if (this.numBuffs != characteristicsStrings.length || this.numBuffs != amountsValues.length) {
            throw new IllegalArgumentException("Stats, characteristics, and amounts arrays must have the same length.");
        }
    }

    public String[] getBuff(int index) {
        if (index < 0 || index >= this.numBuffs) {
            throw new IndexOutOfBoundsException("Invalid buff index: " + index);
        }
        return new String[] {this.stats[index], this.characteristics[index], Double.toString(this.amounts[index])};
    }
}