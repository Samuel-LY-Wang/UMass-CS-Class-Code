package game.Combat.Attacks;
import game.Combat.Entities.Entity;

public class MeleeAttack extends Attack {
    public MeleeAttack(double range) {
        super(range);
    }
    public MeleeAttack(double range, double damageMod) {
        super(range, damageMod);
    }
    public MeleeAttack(double range, double damageMod, double minDamage, double maxDamage) {
        super(range, damageMod, minDamage, maxDamage);
    }

    @Override
    public boolean apply(Entity source, Entity target) {
        // Returns: Was attack successful?
        if (source.distanceTo(target) > this.range) {
            return false;
        } else {
            this.successfulAttack(source, target);
            return true;
        }
    }
}
