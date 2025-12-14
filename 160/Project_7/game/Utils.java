package game;
import game.Combat.Player.Player;
import game.Combat.Enemies.Enemy;

public final class Utils {
    private Utils() {throw new AssertionError("Class cannot be instantiated, instances prohibited.");} // Prevent instantiation
    public static final boolean isWaveOver(Player[] players, Enemy[] enemies) {
        boolean allPlayersDead = true;
        for (Player p : players) {
            if (p.isAlive()) {
                allPlayersDead = false;
                break;
            }
        }
        boolean allEnemiesDead = true;
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                allEnemiesDead = false;
                break;
            }
        }
        return allPlayersDead || allEnemiesDead;
    }

    public static final boolean isPlayerDead(Player[] players) {
        for (Player p : players) {
            if (p.isAlive()) {
                return false;
            }
        }
        return true;
    }

    public static final String getPlayerStatus(Player p) {
        return p.getName() + " the " + p.getClassName() + " - HP: " + p.getStat("curHealth") + "/" + p.getStat("maxHealth") + ", Located at: " + String.format("%.2f", p.getStat("position"));
    }

    public static final String getEnemyStatus(Enemy e) {
        if (e.isAlive()) {
            return e.getName() + " - HP: " + e.getStat("curHealth") + "/" + e.getStat("maxHealth") + ", Located at: " + String.format("%.2f", e.getStat("position"));
        }
        else {
            return e.getName() + " - [DEFEATED]";
        }
    }
}
