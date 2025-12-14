package game.Items;
import game.Stats.RNG;

public class ItemSpawner {
    private ItemSpawner() {throw new AssertionError("Class cannot be instantiated, instances prohibited.");} // Prevent instantiation
    public static final Item SpawnItem() {
        int spawn = RNG.rng.nextInt(ItemList.itemFrequencySum);
        int cumulativeFrequency = 0;
        for (Item item : ItemList.items) {
            cumulativeFrequency += item.frequency;
            if (spawn < cumulativeFrequency) {
                return item;
            }
        }
        throw new IllegalStateException("Failed to spawn item");
    }
    public static final Item[] SpawnItems(int n) {
        Item[] spawnedItems = new Item[n];
        for (int i = 0; i < n; i++) {
            spawnedItems[i] = SpawnItem();
        }
        return spawnedItems;
    }
}
