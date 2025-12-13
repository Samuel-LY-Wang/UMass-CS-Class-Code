package game.Combat.Enemies;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import game.Stats.EnemyStats;
import game.Stats.RNG;

public class EnemySpawner {
    public static final Map<String, Integer> enemiesPerGroup = new HashMap<>() {{
        put("Basic", 2);
        put("Weak", 5);
        put("Speedy", 1);
        put("Ranged", 1);
        put("Large", 1);
    }};
    public static final int numGroupTypes = enemiesPerGroup.size();
    public static Enemy[] spawnEnemyGroup(String enemyType, int count, int wave, double meanPos) {
        Enemy[] enemies = new Enemy[count];
        for (int i = 0; i < count; i++) {
            double pos = meanPos + RNG.getRandPos(0.0, EnemyStats.STDEV_POS);
            switch (enemyType) {
                case "Ranged" -> enemies[i] = new RangedEnemy("Ranged", wave, pos);
                case "Basic" -> enemies[i] = new Enemy("Basic", wave, pos);
                case "Weak" -> enemies[i] = new Enemy("Weak", wave, pos);
                case "Speedy" -> enemies[i] = new Enemy("Speedy", wave, pos);
                case "Large" -> enemies[i] = new Enemy("Large", wave, pos);
                // Additional enemy types can be added here
                default -> throw new IllegalArgumentException("Unknown enemy type: " + enemyType);
            }
        }
        return enemies;
    }
    public static Enemy[] spawnEnemies(int wave) {
        int numGroups = 1+Math.floorDiv(wave, 10); // +1 group per 10 waves
        ArrayList<Enemy> enemyList = new ArrayList<>();
        for (int i=0; i < numGroups; i++) {
            double groupMeanPos = RNG.randomDoubleInRange(-50.0, 50.0); // clusters spawn somewhere between -50 and 50
            int groupToSpawn = RNG.rng.nextInt(numGroupTypes);;
            String enemyType = (String) enemiesPerGroup.keySet().toArray()[groupToSpawn];
            int count = enemiesPerGroup.get(enemyType);
            Enemy[] newEnemies = spawnEnemyGroup(enemyType, count, wave, groupMeanPos);
            for (Enemy e : newEnemies) {
                enemyList.add(e);
            }
        }
        return enemyList.toArray(new Enemy[0]);
    }
}
