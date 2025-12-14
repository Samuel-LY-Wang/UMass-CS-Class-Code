package tests;
import game.Items.ItemSpawner;

public class testItemSpawn {
    public static void main(String[] args) {
        System.out.println("Generating 10000 random items:");
        for (int i = 0; i < 10000; i++) {
            ItemSpawner.SpawnItem(); // Shouldn't throw any errors, and it doesn't
        }
    }
}
