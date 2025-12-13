package game.Combat.Enemies;
import game.Stats.*;

public class LargeEnemy extends Enemy {
    protected static final String className = "Large";
    protected static final int[] baseStats = EnemyStats.baseStats.get(className);
    protected static final int goldReward = EnemyStats.goldRewards.get(className);
    protected static final int defScaleTimer = EnemyStats.defScaleTimers.get(className);
    protected static final double defScaleAmount = EnemyStats.defScaleAmounts.get(className);
    public LargeEnemy(int wave, double position) {
        super(baseStats[0], baseStats[1], baseStats[2], baseStats[3], position, "Large");
        this.multiplyStat("maxHealth", ScalingFuncs.getHPATKMult(wave));
        this.multiplyStat("attack", ScalingFuncs.getHPATKMult(wave));
        this.updateStat("defense", ScalingFuncs.getDefMod(wave, defScaleTimer, defScaleAmount));
        this.multiplyStat("speed", ScalingFuncs.getSpeedMult(wave));
    }
}
