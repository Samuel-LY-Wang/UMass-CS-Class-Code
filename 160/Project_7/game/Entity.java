package game;

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

    protected int speedBase;
    protected double speedMult; // multiplier (from items and upgrades)
    protected double speedMod; // multiplier (from buffs and debuffs)
    protected double speed;

    protected double position;

    protected boolean isAlive;

    public Entity(String name, int maxHealth, int baseATK, int baseDefense, int speedBase, double position) {
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

        this.speedBase = speedBase;
        this.speedMult = 1.0;
        this.speedMod = 1.0;
        this.speed = speedBase * this.speedMult * this.speedMod;

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

    private void recalculateAttack() {
        this.attackPower = this.baseATK * this.atkMult;
    }

    public final void updateBaseAttack(double amount) {
        this.baseATK += amount;
        this.recalculateAttack();
    }

    public final void multiplyAttack(double factor) {
        this.atkMult *= factor;
        this.recalculateAttack();
    }

    private void recalculateMaxHealth() {
        this.maxHealth = this.baseMaxHealth * this.maxHealthMult;
    }

    public final void updateBaseMaxHealth(double amount) {
        this.baseMaxHealth += amount;
        this.recalculateMaxHealth();
        if (this.curHealth > this.maxHealth) {
            this.curHealth = this.maxHealth;
        }
        this.isAlive = (this.curHealth > 0);
    }

    public final void multiplyMaxHealth(double factor) {
        this.maxHealthMult *= factor;
        this.recalculateMaxHealth();
        if (this.curHealth > this.maxHealth) {
            this.curHealth = this.maxHealth;
        }
        this.isAlive = (this.curHealth > 0);
    }

    private void recalculateDefense() {
        this.defense = this.baseDefense * this.defenseMult;
    }

    public final void updateBaseDefense(double amount) {
        this.baseDefense += amount;
        this.recalculateDefense();
    }

    public final void multiplyDefense(double factor) {
        this.defenseMult *= factor;
        this.recalculateDefense();
    }

    public final void recalculateSpeed() {
        this.speed = this.speedBase * this.speedMult * this.speedMod;
    }

    public final void updateBaseSpeed(int amount) {
        // updates base speed
        this.speedBase += amount;
        this.recalculateSpeed();
    }

    public final void updateSpeedMult(double factor) {
        // multiplies speed multiplier
        this.speedMult = this.speedMult * factor;
        this.recalculateSpeed();
    }

    public final void setSpeedMult(double newMult) {
        // sets speed multiplier
        this.speedMult = newMult;
        this.recalculateSpeed();
    }

    public final void updateSpeedMod(double factor) {
        // multiplies speed modifier
        this.speedMod = this.speedMod * factor;
        this.recalculateSpeed();
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