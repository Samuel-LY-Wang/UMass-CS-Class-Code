public abstract class CombatEntity {
    protected String name;
    protected int curHealth;
    protected int maxHealth;
    protected int attackPower;

    public CombatEntity(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.curHealth = maxHealth; // Start with full health
        this.attackPower = attackPower;
    }

    public boolean hasLost() {
        return this.curHealth <= 0;
    
    }

    public String takeHit(int damage, String source) {
        this.curHealth -= damage;
        return "" + this.name + " takes a hit for " + damage + " damage from " + source;
    }

    public String recover(int amount, String source) {
        int prevHealth = this.curHealth;
        this.curHealth = Math.min(this.curHealth + amount, this.maxHealth);
        return "" + this.name + " recovers " + (this.curHealth - prevHealth) + " health from " + source;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.curHealth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.curHealth = health;
    }

    public abstract String attack(CombatEntity target);
}
