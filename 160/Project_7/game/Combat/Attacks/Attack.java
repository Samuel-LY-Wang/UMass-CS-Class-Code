package game.Combat.Attacks;
import game.Combat.Entity;

public abstract class Attack {
    protected double range;
    protected double damageMod;
    public abstract boolean apply(Entity source, Entity target);
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
}
