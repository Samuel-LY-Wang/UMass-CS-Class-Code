package game.Stats;
import java.util.HashMap;
import java.util.Map;

public final class PlayerStats {
    private PlayerStats() {
        throw new AssertionError("Class cannot be instantiated, instances prohibited.");
    }
    public static final String[] classNames = {
        "Human",
        "Wizard",
        "Elf",
        "Giant",
        "Brawler",
        "Rogue",
        "Merchant"
    };
    public static final Map<String, String> classDescriptions = new HashMap<>() {{
        put("Human", "Balanced stats across the board.");
        put("Wizard", "Gain access to powerful magic attacks.");
        put("Elf", "Ranged attacks are more accurate, but lower health.");
        put("Giant", "High health and defense but low speed.");
        put("Brawler", "Melee attacks deal more damage.");
        put("Rogue", "Move very quickly, but hit less hard.");
        put("Merchant", "Start the game with extra gold for the shop.");
    }}; // descriptions for each class
    public static final Map<String, int[]> baseStats = new HashMap<>() {{
        put("Human", new int[]{125, 12, 0, 30, 10});
        put("Wizard", new int[]{100, 10, 0, 25, 10});
        put("Elf", new int[]{80, 10, 0, 25, 10});
        put("Giant", new int[]{200, 20, 2, 5, 10});
        put("Brawler", new int[]{100, 10, 0, 25, 10});
        put("Rogue", new int[]{100, 8, 0, 45, 10});
        put("Merchant", new int[]{100, 10, 0, 25, 30});
    }}; // maxHealth, attack, defense, speed, starting gold (in that order)
    public static final Map<String, Double> meleeAtkModifier = new HashMap<>() {{
        put("Human", 1.0);
        put("Wizard", 1.0);
        put("Elf", 1.0);
        put("Giant", 1.0);
        put("Brawler", 1.5);
        put("Rogue", 1.0);
        put("Merchant", 1.0);
    }}; // melee attack damage modifier for each class
    public static final Map<String, Double> rangedAtkModifier = new HashMap<>() {{
        put("Human", 1.0);
        put("Wizard", 1.0);
        put("Elf", 1.5);
        put("Giant", 1.0);
        put("Brawler", 1.0);
        put("Rogue", 1.0);
        put("Merchant", 1.0);
    }}; // melee attack damage modifier for each class
    public static final Map<String, Boolean> canUseMagic = new HashMap<>() {{
        put("Human", false);
        put("Wizard", true);
        put("Elf", false);
        put("Giant", false);
        put("Brawler", false);
        put("Rogue", false);
        put("Merchant", false);
    }}; // melee attack damage modifier for each class
    public static final Map<String, Double> baseRangedAcc = new HashMap<>() {{
        put("Human", 10.0);
        put("Wizard", 10.0);
        put("Elf", 20.0);
        put("Giant", 10.0);
        put("Brawler", 10.0);
        put("Rogue", 10.0);
        put("Merchant", 10.0);
    }}; // base ranged attack accuracy for each class
    public static final int START_POS = 0;
    public static final int BASE_HEAL_AMOUNT = 20;
}
