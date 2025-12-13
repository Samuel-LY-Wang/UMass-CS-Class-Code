package game.Combat.Enemies;
import game.Combat.Attacks.RangedAttack;
import game.Combat.RangedEntity;
import game.Combat.Entity;
import game.Stats.*;

public class RangedEnemy extends Enemy implements RangedEntity {
    protected static final String className = "Ranged";
    protected static final int[] baseStats = EnemyStats.baseStats.get(className);
    protected static final int goldReward = EnemyStats.goldRewards.get(className);
    protected static final int defScaleTimer = EnemyStats.defScaleTimers.get(className);
    protected static final double defScaleAmount = EnemyStats.defScaleAmounts.get(className);
    protected RangedAttack rangedAttack;
    public RangedEnemy(int wave, double position) {
        super(baseStats[0], baseStats[1], baseStats[2], baseStats[3], position, "Ranged");
        this.multiplyStat("maxHealth", ScalingFuncs.getHPATKMult(wave));
        this.multiplyStat("attack", ScalingFuncs.getHPATKMult(wave));
        this.updateStat("defense", ScalingFuncs.getDefMod(wave, defScaleTimer, defScaleAmount));
        this.multiplyStat("speed", ScalingFuncs.getSpeedMult(wave));
        this.rangedAttack = new RangedAttack(1.0, OtherStats.BASE_RANGED_ACC);
    }

    @Override
    public boolean rangedAttack(Entity target) {
        return this.rangedAttack.apply(this, target);
    }
}
