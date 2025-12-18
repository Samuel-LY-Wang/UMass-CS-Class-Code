package game.Combat.Enemies;
import game.Combat.Player.Player;
import game.Stats.RNG;

public class EnemyController {
    private static void move(Enemy e, Player p) {
        if (!e.isAlive() || !p.isAlive()) {
            return;
        }
        // Simple movement logic: Move enemy random amount between current position and closest it can get to player
        double distance = p.getStat("position") - e.getStat("position");
        if (distance > 0) {
            // enemy is left of player
            double moveAmount = RNG.randomDoubleInRange(0, Math.min(e.getStat("speed"), distance));
            moveAmount += RNG.randomDoubleInRange(-5, 5); // add a small random factor to avoid predictability
            moveAmount = Math.min(moveAmount, e.getStat("speed"));
            e.move(moveAmount);
        }
        else if (distance < 0) {
            // enemy is right of player
            double moveAmount = -RNG.randomDoubleInRange(0, Math.min(e.getStat("speed"), -distance));
            moveAmount += RNG.randomDoubleInRange(-5, 5); // add a small random factor to avoid predictability
            moveAmount = Math.max(moveAmount, -e.getStat("speed"));
            e.move(moveAmount);
        }
    }
    public static boolean meleeAttack(Enemy e, Player p) {
        if (!e.isAlive() || !p.isAlive()) {
            return false;
        }
        return e.meleeAttack(p);
    }
    public static boolean rangedAttack(RangedEnemy e, Player p) {
        if (!e.isAlive() || !p.isAlive()) {
            return false;
        }
        return e.rangedAttack(p);
    }
    public static void enemyAction(Enemy e, Player p) {
        if (!e.isAlive() || !p.isAlive()) {
            return;
        }
        move(e,p); // ALWAYS move first
        // melee attack if in range, ranged enemies will ranged attack if melee unavailable
        boolean inRange = meleeAttack(e, p);
        if (!inRange && e instanceof RangedEnemy re) {
            rangedAttack(re, p);
        }
    }
}
