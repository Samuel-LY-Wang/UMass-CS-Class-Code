package game.Combat.Player;
import game.Combat.Entity;
import game.Combat.RangedEntity;
import game.Combat.Attacks.RangedAttack;
import game.Stats.OtherStats;

public abstract class Player extends Entity implements RangedEntity{
    protected final String className;
    protected int num_undos;
    protected int gold;
    protected double goldMult;
    protected double minAtkMult;
    protected double maxAtkMult;
    protected RangedAttack rangedAttack;
    public Player(String className, String name, int maxHealth, int baseATK, int baseDefense, int speedBase, double position, int startingGold) {
        super(name, maxHealth, baseATK, baseDefense, speedBase, position);
        this.className = className;
        this.num_undos = 0; // undos only given by items
        this.goldMult = 1.0; // Gold multiplier from items
        this.gold = startingGold;
        this.minAtkMult = 1.0; // Minimum attack multiplier from items
        this.maxAtkMult = 1.0; // Maximum attack multiplier from items
        this.rangedAttack = new RangedAttack(1.0, OtherStats.BASE_RANGED_ACC);
    }

    public int getGold() {
        return this.gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void updateGoldMult(double amount) {
        this.goldMult *= amount;
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

    public int getNumUndos() {
        return this.num_undos;
    }

    public boolean Undo() {
        // return: Was undo successful?
        if (this.num_undos <= 0) {
            return false;
        }
        this.num_undos -= 1;
        return true;
    }

    @Override
    public void multiplyStat(String stat, double amount) {
        switch (stat) {
            case "goldMult" -> this.goldMult *= amount;
            case "minAtkMult" -> this.minAtkMult *= amount;
            case "maxAtkMult" -> this.maxAtkMult *= amount;
            default -> super.multiplyStat(stat, amount);
        }
    }

    @Override
    public void updateStat(String stat, double amount) {
        switch (stat) {
            case "undos" -> this.num_undos += (int) amount;
            default -> super.updateStat(stat, amount);
        }
    }

    @Override
    public boolean rangedAttack(Entity target) {
        return this.rangedAttack.apply(this, target);
    }

    public String getClassName() {
        return this.className;
    }
}
