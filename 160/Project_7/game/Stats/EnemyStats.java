package game.Stats;
import java.util.HashMap;
import java.util.Map;

public final class EnemyStats {
    private EnemyStats() {
        throw new AssertionError("Class cannot be instantiated, instances prohibited.");
    }
    public static final String[] enemyTypes = {"Weak", "Basic", "Speedy", "Ranged", "Large"};
    public static final int numEnemyTypes = enemyTypes.length;
    public static final Map<String, int[]> baseStats = new HashMap<>() {{
        put("Weak", new int[]{10, 5, 0, 5});
        put("Basic", new int[]{40, 10, 0, 10});
        put("Speedy", new int[]{40, 10, 0, 20});
        put("Ranged", new int[]{40, 10, 0, 10});
        put("Large", new int[]{100, 10, 0, 10});
    }}; // maxHealth, attack, defense, base speed (in that order)
    public static final Map<String, Integer> goldRewards = new HashMap<>() {{
        put("Weak", 1);
        put("Basic", 2);
        put("Speedy", 4);
        put("Ranged", 4);
        put("Large", 8);
    }}; // gold rewards for each enemy type
    public static final Map<String, Integer> defScaleTimers = new HashMap<>() {{
        put("Weak", 10);
        put("Basic", 5);
        put("Speedy", 5);
        put("Ranged", 5);
        put("Large", 5);
    }}; // defense scale timers for each enemy type
    public static final Map<String, Double> defScaleAmounts = new HashMap<>() {{
        put("Weak", 0.5);
        put("Basic", 0.5);
        put("Speedy", 0.5);
        put("Ranged", 0.5);
        put("Large", 1.0);
    }}; // defense scale amounts for each enemy type
    public static final double STDEV_POS = 15.0; // standard deviation for enemy spawn positions
}
