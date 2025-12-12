package game.Combat;

public class Entity {
    protected final String name;

    protected double curHealth;
    protected int baseMaxHealth;
    protected double maxHealthMult; // multiplier (items and upgrades)
    protected double maxHealth;

    protected int baseATK;
    protected double atkMult; // multiplier (items and upgrades)
    protected double attackPower;

    protected int baseDefense;
    protected double defenseMult; // multiplier (items and upgrades)
    protected double defense;

    protected int baseSpeed;
    protected double speedMult; // multiplier (from items and upgrades)
    protected double speedMod; // multiplier (from buffs and debuffs)
    protected double speed;

    protected double position;

    protected boolean isAlive;

    public Entity(String name, int maxHealth, int baseATK, int baseDefense, int baseSpeed, double position) {
        this.name = name;

        this.baseMaxHealth = maxHealth;
        this.maxHealthMult = 1.0;
        this.maxHealth = this.baseMaxHealth * this.maxHealthMult;
        this.curHealth = this.maxHealth;

        this.baseATK = baseATK;
        this.atkMult = 1.0;
        this.attackPower = baseATK * this.atkMult;

        this.baseDefense = baseDefense;
        this.defenseMult = 1.0;
        this.defense = baseDefense * this.defenseMult;

        this.baseSpeed = baseSpeed;
        this.speedMult = 1.0;
        this.speedMod = 1.0;
        this.speed = this.baseSpeed * this.speedMult * this.speedMod;

        this.position = position;

        this.isAlive = (this.curHealth > 0);
    }

    public final String getName() {
        return this.name;
    }

    public final double getAttackPower() {
        return this.attackPower;
    }

    public final double getCurHealth() {
        return this.curHealth;
    }

    public final double getMaxHealth() {
        return this.maxHealth;
    }

    public final double getDefense() {
        return this.defense;
    }

    public final double getSpeed() {
        return this.speed;
    }

    protected final void recalculateStats() {
        this.attackPower = this.baseATK * this.atkMult;
        this.maxHealth = this.baseMaxHealth * this.maxHealthMult;
        this.defense = this.baseDefense * this.defenseMult;
        this.speed = this.baseSpeed * this.speedMult * this.speedMod;
    }

    public void updateStat(String statName, double amount) {
        switch (statName) {
            case "attack" -> this.baseATK += (int) amount;
            case "maxHealth" -> this.baseMaxHealth += (int) amount;
            case "defense" -> this.baseDefense += (int) amount;
            case "speed" -> this.baseSpeed += (int) amount;
            default -> throw new IllegalArgumentException("Invalid stat name");
        }
        this.recalculateStats();
    }

    public void multiplyStat(String statName, double factor) {
        switch (statName) {
            case "attack" -> this.atkMult *= factor;
            case "maxHealth" -> this.maxHealthMult *= factor;
            case "defense" -> this.defenseMult *= factor;
            case "speed" -> this.speedMult *= factor;
            default -> throw new IllegalArgumentException("Invalid stat name");
        }
        this.recalculateStats();
    }

    public final void setSpeedMod(double speedMod) {
        this.speedMod = speedMod;
        this.recalculateStats();
    }

    public final boolean isAlive() {
        return this.isAlive;
    }

    public final double Heal(double amount) {
        // Returns the amount healed
        double prevHealth = this.curHealth;
        this.curHealth += amount;
        if (this.curHealth > this.maxHealth) {
            this.curHealth = this.maxHealth;
        }
        return this.curHealth - prevHealth;
    }

    public final double takeDamage(double amount) {
        // Returns the amount of damage taken
        double prevHealth = this.curHealth;
        double trueDamage = Math.max(0, amount - this.defense);
        this.curHealth -= trueDamage;
        if (this.curHealth <= 0) {
            this.curHealth = 0;
            this.isAlive = false;
        }
        return prevHealth - this.curHealth;
    }

    public final double getPosition() {
        return this.position;
    }

    public final double distanceTo(Entity other) {
        return Math.abs(this.position - other.position);
    }

    public final boolean move(double distance) {
        // Returns: was move successful?
        if (!this.isAlive) {
            return false;
        }
        if (Math.abs(distance) > this.speed) {
            return false;
        }
        this.position += distance;
        return true;
    }
}

