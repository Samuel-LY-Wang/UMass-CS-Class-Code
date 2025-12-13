package game.Combat.Enemies;
import game.Stats.*;

public class BasicEnemy extends Enemy {
    protected static final String className = "Basic";
    protected static final int[] baseStats = EnemyStats.baseStats.get(className);
    protected static final int goldReward = EnemyStats.goldRewards.get(className);
    protected static final int defScaleTimer = EnemyStats.defScaleTimers.get(className);
    protected static final double defScaleAmount = EnemyStats.defScaleAmounts.get(className);
    public BasicEnemy(int wave, double position) {
        super(baseStats[0], baseStats[1], baseStats[2], baseStats[3], position, "Basic");
        this.multiplyStat("maxHealth", ScalingFuncs.getHPATKMult(wave));
        this.multiplyStat("attack", ScalingFuncs.getHPATKMult(wave));
        this.updateStat("defense", ScalingFuncs.getDefMod(wave, defScaleTimer, defScaleAmount));
        this.multiplyStat("speed", ScalingFuncs.getSpeedMult(wave));
    }
}
