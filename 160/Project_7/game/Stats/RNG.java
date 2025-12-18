package game.Stats;
import java.util.Random;

public class RNG {
    // Exists so we use a single RNG instance
    private RNG() {
        throw new AssertionError("Class cannot be instantiated, instances prohibited.");
    }
    private static final Random rng = new Random();
    public static final double randomDoubleInRange(double min, double max) {
        return min + (rng.nextDouble() * (max - min));
    }
    public static final int randomIntInRange(int min, int max) {
        return min + rng.nextInt(max - min + 1);
    }
    public static final double getRandPos(double mean, double stdev) {
        return mean + (rng.nextGaussian() * stdev);
    }
    public static final boolean chance(double probability) {
        return rng.nextDouble() < probability;
    }
}
