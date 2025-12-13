package game.Combat.Enemies;
import game.Combat.Attacks.RangedAttack;
import game.Combat.Entities.Entity;
import game.Combat.Entities.RangedEntity;
import game.Stats.OtherStats;

public class RangedEnemy extends Enemy implements RangedEntity {

    protected RangedAttack rangedAttack;
    public RangedEnemy(String className, int wave, double position) {
        super(className, wave, position);
        this.rangedAttack = new RangedAttack(OtherStats.BASE_RANGED_ACC);
    }

    public RangedAttack getRangedAttack() {
        return this.rangedAttack;
    }

    @Override
    public boolean rangedAttack(Entity target) {
        return this.rangedAttack.apply(this, target);
    }
}
