package game.Combat.Attacks;
import game.Combat.Entity;

public class SlowingAttack extends Attack {
    protected double slowAmount; // The amount to slow speed by
    public SlowingAttack(double range, double damageMod, double slowAmount) {
        super(range, damageMod);
        this.slowAmount = slowAmount;
    }
    public boolean apply(Entity source, Entity target) {
        // Returns: Was attack successful?
        if (source.distanceTo(target) > this.range) {
            return false;
        } else {
            target.takeDamage(source.getStat("attackPower") * this.damageMod);
            target.setSpeedMod(1.0 - this.slowAmount);
            return true;
        }
    }
}
