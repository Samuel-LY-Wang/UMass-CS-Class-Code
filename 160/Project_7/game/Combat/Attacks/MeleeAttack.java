package game.Combat.Attacks;
import game.Combat.Entity;

public class MeleeAttack extends Attack {
    public MeleeAttack(double range, double damageMod) {
        this.range = range;
        this.damageMod = damageMod;
    }

    @Override
    public boolean apply(Entity source, Entity target) {
        // Returns: Was attack successful?
        if (source.distanceTo(target) > this.range) {
            return false;
        } else {
            target.takeDamage(source.getAttackPower() * this.damageMod);
            return true;
        }
    }
}
