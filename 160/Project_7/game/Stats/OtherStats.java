package game.Stats;

public final class OtherStats {
    private OtherStats() {
        throw new UnsupportedOperationException("This class only stores stats, do not create instances.");
    }
    public static final int BASE_MELEE_RANGE = 5;
    public static final double BASE_RANGED_ACC = 10;
    public static final double BASE_FIREBALL_ACC = 15;
    public static final double BASE_ICE_RANGE = 10;
    public static final double BASE_ICE_SLOW = 0.2; // yeah the ice is VERY cold lol
}
