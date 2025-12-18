package game.Combat.Attacks;
import game.Combat.Entities.Entity;
import game.Stats.RNG;

public class RangedAttack extends Attack {
    protected double accuracy; // distance at which the attack has a 1/2 chance of hitting
    public RangedAttack(double accuracy) {
        super(Double.MAX_VALUE);
        this.accuracy = accuracy;
    }
    public RangedAttack(double damageMod, double accuracy) {
        super(Double.MAX_VALUE, damageMod);
        this.accuracy = accuracy;
    }
    public RangedAttack(double damageMod, double accuracy, double minDamage, double maxDamage) {
        super(Double.MAX_VALUE, damageMod, minDamage, maxDamage);
        this.accuracy = accuracy;
    }

    public double getSuccessRate(double dist) {
        return Math.exp(-(Math.pow(dist/this.accuracy, 2)) * Math.log(2)); // Gaussian success rate
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void multipyAccuracy(double factor) {
        this.accuracy *= factor;
    }

    @Override
    public boolean apply(Entity source, Entity target) {
        double dist = source.distanceTo(target);
        double successRate = getSuccessRate(dist);
        boolean success = RNG.chance(successRate);
        if (success) {
            this.successfulAttack(source, target);
        }
        return success;
    }
}
