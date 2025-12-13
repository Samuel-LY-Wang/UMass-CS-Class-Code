package game.Combat.Player;
import game.Combat.Attacks.RangedAttack;
import game.Combat.Attacks.SlowingAttack;
import game.Combat.Entity;
import game.Stats.*;

public class Rogue extends Player {
    public static final String className = "Rogue";
    public static final int[] baseStats = PlayerStats.baseStats.get(className);
    public static final double meleeModifier = PlayerStats.meleeAtkModifier.get(className);
    public static final double rangedModifier = PlayerStats.rangedAtkModifier.get(className);
    public static final boolean magicUser = PlayerStats.canUseMagic.get(className);
    public final RangedAttack fireBall;
    public final SlowingAttack iceSpike;
    public Rogue(String name, double position) {
        super(className, name, baseStats[0], baseStats[1], baseStats[2], baseStats[3], position, baseStats[4]);
        this.rangedAttack.setDamageMod(rangedModifier);
        this.basicAttack.setDamageMod(meleeModifier);
        if (magicUser) {
            this.fireBall = new RangedAttack(1.0, OtherStats.BASE_FIREBALL_ACC);
            this.iceSpike = new SlowingAttack(1.0, OtherStats.BASE_ICE_RANGE, OtherStats.BASE_ICE_SLOW);
        }
        else {
            this.fireBall = null;
            this.iceSpike = null;
        }
    }
    protected boolean isMagicUser() {
        return magicUser;
    }
    public boolean fireBall(Entity target) {
        if (!isMagicUser()) {
            return false;
        }
        return this.fireBall.apply(this, target);
    }
    public boolean iceSpike(Entity target) {
        if (!isMagicUser()) {
            return false;
        }
        return this.iceSpike.apply(this, target);
    }
}