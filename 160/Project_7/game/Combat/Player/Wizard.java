package game.Combat.Player;
import game.Combat.Attacks.RangedAttack;
import game.Combat.Attacks.SlowingAttack;
import game.Combat.Entities.Entity;
import game.Stats.*;

public class Wizard extends Player {
    public final RangedAttack fireBall;
    public final SlowingAttack iceSpike;
    public Wizard(String name, double position) {
        super("Wizard", name, position);
        if (!this.magicUser) {
            throw new IllegalArgumentException("Class " + className + " cannot be a MagicPlayer.");
        }
        this.fireBall = new RangedAttack(OtherStats.BASE_FIREBALL_ACC);
        this.iceSpike = new SlowingAttack(OtherStats.BASE_ICE_RANGE, OtherStats.BASE_ICE_SLOW);
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