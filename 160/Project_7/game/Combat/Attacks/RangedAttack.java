package game.Combat.Attacks;
import game.Combat.Entity;
import game.Stats.RNG;

public class RangedAttack extends Attack {
    protected double accuracy; // distance at which the attack has a 1/e chance of hitting (~37%)
    public RangedAttack(double damageMod, double accuracy) {
        super(Double.MAX_VALUE, damageMod);
        this.accuracy = accuracy;
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
        double rng_res = RNG.rng.nextDouble();
        if (rng_res < successRate) {
            double damage = this.damageMod * source.getStat("attackPower");
            target.takeDamage(damage);
            return true; // attack hit
        } else {
            return false; // attack missed
        }
    }
}
