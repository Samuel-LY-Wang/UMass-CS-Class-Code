package game.Combat.Enemies;
import game.Combat.Player.Player;
import game.Stats.RNG;

public class EnemyController {
    public static void move(Enemy e, Player p) {
        // Simple movement logic: Move enemy random amount between current position and closest it can get to player
        double distance = p.getStat("position") - e.getStat("position");
        if (distance == 0) {
            return;
        }
        else if (distance > 0) {
            // enemy is left of player
            double moveAmount = RNG.rng.nextDouble() * Math.min(e.getStat("speed"), distance);
            e.move(moveAmount);
        }
        else if (distance < 0) {
            // enemy is right of player
            double moveAmount = -RNG.rng.nextDouble() * Math.min(e.getStat("speed"), -distance);
            e.move(moveAmount);
        }
    }
}
