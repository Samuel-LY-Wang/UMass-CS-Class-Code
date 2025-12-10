package game;

public class Entity {
    protected final String name;
    protected double curHealth;
    protected double maxHealth;
    protected double attackPower;
    protected double defense;
    protected int speedBase;
    protected double speedMod; // multiplier
    protected double speed;
    protected double position;
    protected boolean isAlive;
    public Entity(String name, double maxHealth, double attackPower, double defense, int speedBase, double position) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.curHealth = maxHealth;
        this.attackPower = attackPower;
        this.defense = defense;
        this.speedBase = speedBase;
        this.speedMod = 1.0;
        this.speed = speedBase * this.speedMod;
        this.position = position;
        this.isAlive = (this.curHealth > 0);
    }

    public String getName() {
        return this.name;
    }

    public double getAttackPower() {
        return this.attackPower;
    }

    public double getCurHealth() {
        return this.curHealth;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double updateAttack(double amount) {
        this.attackPower += amount;
        return this.attackPower;
    }

    public double multiplyAttack(double factor) {
        this.attackPower = this.attackPower * factor;
        return this.attackPower;
    }

    public double updateMaxHealth(double amount) {
        this.maxHealth += amount;
        if (this.curHealth > this.maxHealth) {
            this.curHealth = this.maxHealth;
        }
        this.isAlive = (this.curHealth > 0);
        return this.maxHealth;
    }

    public double multiplyMaxHealth(double factor) {
        this.maxHealth = this.maxHealth * factor;
        if (this.curHealth > this.maxHealth) {
            this.curHealth = this.maxHealth;
        }
        this.isAlive = (this.curHealth > 0);
        return this.maxHealth;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public double updateHealth(double amount) {
        // Returns the change in health (positive or negative)
        double prevHealth = this.curHealth;
        this.curHealth += amount;
        if (this.curHealth > this.maxHealth) {
            this.curHealth = this.maxHealth;
        }
        if (this.curHealth <= 0) {
            this.curHealth = 0;
            this.isAlive = false;
        }
        return this.curHealth - prevHealth;
    }

    public boolean move(double distance) {
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
