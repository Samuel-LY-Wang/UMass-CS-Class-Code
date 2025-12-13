package game.Combat.Attacks;
import game.Combat.Entities.Entity;

public class SlowingAttack extends Attack {
    protected double slowAmount; // The amount to slow speed by
    public SlowingAttack(double range, double slowAmount) {
        super(range);
        this.slowAmount = slowAmount;
    }
    public SlowingAttack(double range, double damageMod, double slowAmount) {
        super(range, damageMod);
        this.slowAmount = slowAmount;
    }
    public SlowingAttack(double range, double damageMod, double slowAmount, double minDamage, double maxDamage) {
        super(range, damageMod, minDamage, maxDamage);
        this.slowAmount = slowAmount;
    }
    
    @Override
    public boolean apply(Entity source, Entity target) {
        // Returns: Was attack successful?
        if (source.distanceTo(target) > this.range) {
            return false;
        } else {
            this.successfulAttack(source, target);
            target.setSpeedMod(1.0 - this.slowAmount);
            return true;
        }
    }
}
