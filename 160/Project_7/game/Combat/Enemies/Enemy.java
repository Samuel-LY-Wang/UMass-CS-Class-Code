package game.Combat.Enemies;

import game.Combat.Entities.Entity;
import game.Combat.Player.Player;
import game.Stats.EnemyStats;
import game.Stats.ScalingFuncs;

public class Enemy extends Entity {

    protected final String className;
    protected final int[] baseStats;
    protected final int goldReward;
    protected final int defScaleTimer;
    protected final double defScaleAmount;
    public Enemy(String className, int wave, double position) {
        int[] baseStats = EnemyStats.baseStats.get(className);
        super(className + " Enemy",
              baseStats[0],
              baseStats[1],
              baseStats[2],
              baseStats[3],
              position);
        this.baseStats = baseStats;
        this.goldReward = EnemyStats.goldRewards.get(className);
        this.defScaleTimer = EnemyStats.defScaleTimers.get(className);
        this.defScaleAmount = EnemyStats.defScaleAmounts.get(className);
        this.multiplyStat("maxHealth", ScalingFuncs.getHPATKMult(wave));
        this.multiplyStat("attackPower", ScalingFuncs.getHPATKMult(wave));
        this.updateStat("defense", ScalingFuncs.getDefMod(wave, defScaleTimer, defScaleAmount));
        this.multiplyStat("speed", ScalingFuncs.getSpeedMult(wave));
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    @Override
    public double getStat(String statName) {
        return switch (statName) {
            case "className" -> throw new IllegalArgumentException("className is not a numeric stat. Use getClassName() instead.");
            case "speedMod" -> this.speedMod;
            default -> super.getStat(statName);
        };
    }

    public void giveGoldReward(Player player) {
        player.giveGoldReward(this.goldReward);
    }

    public void takeDamage(double amount, Player source) {
        super.takeDamage(amount);
        if (!this.isAlive) {
            this.giveGoldReward(source);
        }
    }

}