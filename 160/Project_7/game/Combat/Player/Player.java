package game.Combat.Player;
import game.Combat.Attacks.RangedAttack;
import game.Combat.Entities.Entity;
import game.Combat.Entities.RangedEntity;
import game.Stats.PlayerStats;

public class Player extends Entity implements RangedEntity{
    protected final String className;
    protected final int[] baseStats;
    protected final double meleeModifier;
    protected final double rangedModifier;
    protected final boolean magicUser;
    protected final double baseRangedAcc;
    protected int num_undos;
    protected int gold;
    protected double goldMult;
    protected double minAtkMult;
    protected double maxAtkMult;
    protected RangedAttack rangedAttack;

    public Player(String className, String name, double position) {
        int[] baseStats = PlayerStats.baseStats.get(className);
        super(name, baseStats[0], baseStats[1], baseStats[2], baseStats[3], position);
        this.className = className;
        this.baseStats = baseStats;
        this.meleeModifier = PlayerStats.meleeAtkModifier.get(className);
        this.rangedModifier = PlayerStats.rangedAtkModifier.get(className);
        this.magicUser = PlayerStats.canUseMagic.get(className);
        this.baseRangedAcc = PlayerStats.baseRangedAcc.get(className);
        this.num_undos = 0; // undos only given by items
        this.goldMult = 1.0; // Gold multiplier from items
        this.gold = baseStats[4];
        this.minAtkMult = 1.0; // Minimum attack multiplier from items
        this.maxAtkMult = 1.0; // Maximum attack multiplier from items
        this.basicAttack.setDamageMod(this.meleeModifier);
        this.rangedAttack = new RangedAttack(this.baseRangedAcc);
        this.rangedAttack.setDamageMod(this.rangedModifier);
    }

    public void retreat() {
        this.isAlive = false; // Mark player as dead to retreat and immediately end the game
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
        // HandleUndo class handles the process of rolling back positions and states
        return true;
    }

    public boolean isMagicUser() {
        return this.magicUser;
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
            case "gold" -> this.gold += (int) amount;
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

    @Override
    public double getStat(String statName) {
        return switch (statName) {
            case "className" -> throw new IllegalArgumentException("className is not a numeric stat. Use getClassName() instead.");
            case "gold" -> this.gold;
            default -> super.getStat(statName);
        };
    }
}
