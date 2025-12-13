package game.Stats;
import java.util.Random;

public class RNG {
    // Exists so we use a single RNG instance
    private RNG() {
        throw new AssertionError("Class cannot be instantiated, instances prohibited.");
    }
    public static final Random rng = new Random();
    public static final double randomDoubleInRange(double min, double max) {
        return min + (rng.nextDouble() * (max - min));
    }
}
