package game;

public class Player extends Entity {
    private int num_undos;
    private int gold;
    public Player(String name, int maxHealth, int baseATK, int baseDefense, int speedBase, double position, int startingGold) {
        super(name, maxHealth, baseATK, baseDefense, speedBase, position);
        this.num_undos = 0; // undos only given by items
        this.gold = startingGold;
    }

    public int getGold() {
        return this.gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public boolean spendGold(int amount) {
        // Returns: Did you spend the gold successfully?
        if (amount > this.gold) {
            return false;
        }
        this.gold -= amount;
        return true;
    }

    public void giveUndos(int amount) {
        this.num_undos += amount;
    }
}
