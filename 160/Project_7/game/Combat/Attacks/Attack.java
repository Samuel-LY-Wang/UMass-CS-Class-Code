package game.Combat.Attacks;
import game.Combat.Entities.Entity;
import game.Stats.RNG;

public abstract class Attack {
    protected double range;
    protected double damageMod;
    protected double minDamage;
    protected double maxDamage;
    public Attack(double range) {
        this.range = range;
        this.damageMod = 1.0;
        this.minDamage = 1.0;
        this.maxDamage = 1.0;
    }
    public Attack(double range, double damageMod) {
        this.range = range;
        this.damageMod = damageMod;
        this.minDamage = 1.0;
        this.maxDamage = 1.0;
    }
    public Attack(double range, double damageMod, double minDamage, double maxDamage) {
        this.range = range;
        this.damageMod = damageMod;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
    public abstract boolean apply(Entity source, Entity target);
    public void successfulAttack(Entity source, Entity target) {
        // Can be overridden for effects on successful attack
        target.takeDamage(source.getStat("attackPower") * this.damageMod * RNG.randomDoubleInRange(this.minDamage, this.maxDamage));
    }
    public final void setDamageMod(double mod) {
        this.damageMod = mod;
    }
    public final double getDamageMod() {
        return this.damageMod;
    }
    public final void setRange(double range) {
        this.range = range;
    }
    public final double getRange() {
        return this.range;
    }
    public final void multMinDamage(double factor) {
        this.minDamage *= factor;
    }
    public final double getMinDamage() {
        return this.minDamage;
    }
    public final void multMaxDamage(double factor) {
        this.maxDamage *= factor;
    }
    public final double getMaxDamage() {
        return this.maxDamage;
    }
}
