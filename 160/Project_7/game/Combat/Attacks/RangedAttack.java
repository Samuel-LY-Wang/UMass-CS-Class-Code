package game.Combat.Attacks;
import game.Combat.Entity;
import java.util.Random;

public class RangedAttack extends Attack {
    protected double accuracy; // distance at which the attack has a 1/e chance of hitting (~37%)
    protected Random rng;
    public RangedAttack(double damageMod, double accuracy) {
        // this.range is unused, as ranged attacks have **theoretically** infinite range
        this.range = Double.MAX_VALUE;
        this.damageMod = damageMod;
        this.accuracy = accuracy;
        this.rng = new Random();
    }

    public double getSuccessRate(double dist) {
        return Math.exp(-(Math.pow(dist/this.accuracy, 2))); // Gaussian success rate
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
        double rng_res = this.rng.nextDouble();
        if (rng_res < successRate) {
            double damage = this.damageMod * source.getAttackPower();
            target.takeDamage(damage);
            return true; // attack hit
        } else {
            return false; // attack missed
        }
    }
}
