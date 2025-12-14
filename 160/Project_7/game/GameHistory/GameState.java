package game.GameHistory;
import game.Combat.Enemies.Enemy;
import game.Combat.Player.Player;
import java.util.Objects;

public class GameState {
    // we're passing in Players and Enemies as indices in the array used to store them
    private final double[] playerPositions;
    private final double[] enemyPositions;
    private final boolean[] playerAliveStatus;
    private final boolean[] enemyAliveStatus;
    private final double[] playerHealth;
    private final double[] enemyHealth;
    private final double[] enemySpeedMod;
    public GameState(Player[] players, Enemy[] enemies) {
        Objects.requireNonNull(players, "Must provide non-null players array");
        Objects.requireNonNull(enemies, "Must provide non-null enemies array");
        this.playerPositions = new double[players.length];
        this.enemyPositions = new double[enemies.length];
        this.playerAliveStatus = new boolean[players.length];
        this.enemyAliveStatus = new boolean[enemies.length];
        this.playerHealth = new double[players.length];
        this.enemyHealth = new double[enemies.length];
        this.enemySpeedMod = new double[enemies.length];
        for (int i = 0; i < players.length; i++) {
            Player p = players[i];
            if (p == null) {
                throw new IllegalArgumentException("Player at index " + i + " is null");
            }
            this.playerPositions[i] = p.getStat("position");
            this.playerAliveStatus[i] = p.isAlive();
            this.playerHealth[i] = p.getStat("health");
        }
        for (int j = 0; j < enemies.length; j++) {
            Enemy e = enemies[j];
            if (e == null) {
                throw new IllegalArgumentException("Enemy at index " + j + " is null");
            }
            this.enemyPositions[j] = e.getStat("position");
            this.enemyAliveStatus[j] = e.isAlive();
            this.enemyHealth[j] = e.getStat("health");
            this.enemySpeedMod[j] = e.getStat("speedMod");
        }
    }

    public double[] getPlayerPositions() {
        return this.playerPositions.clone();
    }

    public double[] getEnemyPositions() {
        return this.enemyPositions.clone();
    }

    public boolean[] getPlayerAliveStatus() {
        return this.playerAliveStatus.clone();
    }

    public boolean[] getEnemyAliveStatus() {
        return this.enemyAliveStatus.clone();
    }

    public double[] getPlayerHealth() {
        return this.playerHealth.clone();
    }

    public double[] getEnemyHealth() {
        return this.enemyHealth.clone();
    }

    public double[] getEnemySpeedMod() {
        return this.enemySpeedMod.clone();
    }
}
