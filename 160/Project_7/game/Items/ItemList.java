package game.Items;
import java.util.Map;
import java.util.HashMap;

public class ItemList {
    public static final Item[] items = {

        // BASIC ITEMS

        new StatBuffItem(0, "Vitality Potion", "A hearty potion. +20 base max HP",
        5, new StatModifier[] {new IncreaseStat("maxHealth", 20)}, 100),

        new StatBuffItem(1, "Strength Potion", "This potion makes you stronger, +5 base attack power",
        5, new StatModifier[] {new IncreaseStat("attackPower", 5)}, 100),

        new StatBuffItem(2, "Speed Potion", "This potion makes you walk a bit faster, +10 base speed",
        5, new StatModifier[] {new IncreaseStat("speed", 10)}, 100),

        new StatBuffItem(3, "Defense Potion", "This potion makes you more resilient, +2 base defense",
        5, new StatModifier[] {new IncreaseStat("defense", 2)}, 100),

        new StatBuffItem(4, "Pause", "Allows you to undo your last action (3 uses)",
        5, new StatModifier[] {new IncreaseStat("undos", 3)}, 100),

        new StatBuffItem(5, "Small Gold coin", "All enemy kills give 10% more gold",
        5, new StatModifier[] {new MultiplyStat("goldMult", 1.10)}, 100),

        new StatBuffItem(6, "Jack of All Trades", "Gives you a little bit of everything",
            8, new StatModifier[] {
                new IncreaseStat("maxHealth", 10),
                new IncreaseStat("attackPower", 5),
                new IncreaseStat("speed", 3),
                new IncreaseStat("defense", 1),
                new IncreaseStat("undos", 1),
                new MultiplyStat("goldMult", 1.05)
            }, 80),
        
        new StatBuffItem(10, "Enhanced Vitality Potion", "A heartier potion. +50 base max HP",
        10, new StatModifier[] {new IncreaseStat("maxHealth", 50)}, 50),

        new StatBuffItem(11, "Enhanced Strength Potion", "This potion makes you even stronger, +15 base attack power",
        10, new StatModifier[] {new IncreaseStat("attackPower", 15)}, 50),

        new StatBuffItem(12, "Enhanced Speed Potion", "This potion makes you walk faster, +25 base speed",
        10, new StatModifier[] {new IncreaseStat("speed", 25)}, 50),

        new StatBuffItem(13, "Enhanced Defense Potion", "This potion makes you more resilient, +5 base defense",
        10, new StatModifier[] {new IncreaseStat("defense", 5)}, 50),

        new StatBuffItem(14, "Rewind", "Allows you to undo your last action (8 uses)",
        10, new StatModifier[] {new IncreaseStat("undos", 8)}, 50),

        new StatBuffItem(15, "Gold coin", "All enemy kills give 25% more gold",
        10, new StatModifier[] {new MultiplyStat("goldMult", 1.25)}, 50),

        new StatBuffItem(16, "King of All Trades", "Gives you a lot of everything",
            15, new StatModifier[] {
                new IncreaseStat("maxHealth", 30),
                new IncreaseStat("attackPower", 10),
                new IncreaseStat("speed", 10),
                new IncreaseStat("defense", 3),
                new IncreaseStat("undos", 3),
                new MultiplyStat("goldMult", 1.10)
            }, 30),
        
        new StatBuffItem(20, "Growth Potion", "Increases your max HP by 25%",
        15, new StatModifier[] {new MultiplyStat("maxHealth", 1.25)}, 20),

        new StatBuffItem(21, "Enhanced Growth Potion", "Increases your max HP by 50%",
        25, new StatModifier[] {new MultiplyStat("maxHealth", 1.50)}, 10),

        new StatBuffItem(22, "Buff Potion", "Increases your Attack Power by 25%",
        15, new StatModifier[] {new MultiplyStat("attackPower", 1.25)}, 20),

        new StatBuffItem(23, "Enhanced Buff Potion", "Increases your Attack Power by 50%",
        25, new StatModifier[] {new MultiplyStat("attackPower", 1.50)}, 10),

        new StatBuffItem(24, "Sturdy Potion", "Increases your Defense by 10%",
        15, new StatModifier[] {new MultiplyStat("defense", 1.10)}, 20),

        new StatBuffItem(25, "Enhanced Sturdy Potion", "Increases your Defense by 25%",
        25, new StatModifier[] {new MultiplyStat("defense", 1.25)}, 10),

        new StatBuffItem(26, "Speedy Potion", "Increases your Speed by 25%",
        15, new StatModifier[] {new MultiplyStat("speed", 1.25)}, 20),

        new StatBuffItem(27, "Enhanced Speedy Potion", "Increases your Speed by 50%",
        25, new StatModifier[] {new MultiplyStat("speed", 1.50)}, 10),

        new StatBuffItem(28, "Master of all Trades", "Gives you a lot of everything",
            50, new StatModifier[] {
                new IncreaseStat("maxHealth", 50),
                new MultiplyStat("maxHealth", 1.20),
                new IncreaseStat("attackPower", 15),
                new MultiplyStat("attackPower", 1.20),
                new IncreaseStat("speed", 20),
                new MultiplyStat("speed", 1.20),
                new IncreaseStat("defense", 5),
                new MultiplyStat("defense", 1.08),
                new IncreaseStat("undos", 5),
                new MultiplyStat("goldMult", 1.20)
            }
        , 5),

        new StatBuffItem(30, "d6", "Your attacks sometimes do more and sometimes do less damage",
            15, new StatModifier[] {
                new MultiplyStat("minAtkMult", 0.8),
                new MultiplyStat("maxAtkMult", 1.25)
            }
        , 10),

        new StatBuffItem(31, "d12", "Attacks become less predictable",
            20, new StatModifier[] {
                new MultiplyStat("minAtkMult", 0.75),
                new MultiplyStat("maxAtkMult", 1.5)
            }
        , 10),
        
        // END OF BASIC ITEMS, BEGINNING OF TRADEOFF ITEMS

        new StatBuffItem(100, "Statue", "Grants much defense and HP, but renders you completely immobile",
        20, new StatModifier[] {
            new IncreaseStat("maxHealth", 100),
            new MultiplyStat("maxHealth", 1.5),
            new IncreaseStat("defense", 10),
            new MultiplyStat("defense", 1.5),
            new MultiplyStat("speed", 0.0)
        }, 15),

        new StatBuffItem(101, "Armor", "grants much defense, but reduces your speed",
        10, new StatModifier[] {
            new IncreaseStat("defense", 5),
            new MultiplyStat("defense", 1.3),
            new MultiplyStat("speed", 0.75)
        }, 15),

        new StatBuffItem(102, "Glass Cannon", "Do massive damage, but one hit and you shatter.",
        10, new StatModifier[] {
            new IncreaseStat("attackPower", 10),
            new MultiplyStat("attackPower", 1.5),
            new MultiplyStat("defense", 0.0)
        }, 15),

        new StatBuffItem(103, "Speed Demon", "Move incredibly fast, but lose HP and defense",
        10, new StatModifier[] {
            new IncreaseStat("speed", 10),
            new MultiplyStat("speed", 2.0),
            new MultiplyStat("maxHealth", 0.8),
            new MultiplyStat("defense", 0.8)
        }, 15),

        new StatBuffItem(104, "Sacrifice", "Lose a lot of HP, but gain a lot of attack power",
        10, new StatModifier[] {
            new MultiplyStat("attackPower", 1.5),
            new IncreaseStat("attackPower", 20),
            new MultiplyStat("maxHealth", 0.5)
        }, 15),

        new StatBuffItem(105, "Frenzy", "Gain speed and attack, at the cost of defense and HP",
        10, new StatModifier[] {
            new MultiplyStat("speed", 1.5),
            new IncreaseStat("speed", 10),
            new MultiplyStat("attackPower", 1.5),
            new IncreaseStat("attackPower", 10),
            new MultiplyStat("defense", 0.5),
            new MultiplyStat("maxHealth", 0.8)
        }, 15),

        new StatBuffItem(106, "Billionaire", "Reduce all stats, but all enemies give double gold",
        10, new StatModifier[] {
            new MultiplyStat("speed", 0.75),
            new MultiplyStat("attackPower", 0.75),
            new MultiplyStat("defense", 0.75),
            new MultiplyStat("maxHealth", 0.75),
            new MultiplyStat("goldMult", 2.0)
        }, 15),

        new StatBuffItem(107, "Frugality", "Enemies give less gold, but all stats are buffed",
        10, new StatModifier[] {
            new MultiplyStat("goldMult", 0.5),
            new MultiplyStat("speed", 1.25),
            new IncreaseStat("speed", 5),
            new MultiplyStat("attackPower", 1.25),
            new IncreaseStat("attackPower", 5),
            new MultiplyStat("defense", 1.25),
            new IncreaseStat("defense", 2),
            new MultiplyStat("maxHealth", 1.25),
            new IncreaseStat("maxHealth", 10)
        }, 15),

        new StatBuffItem(108, "Giant Potion", "Greatly increases HP at the cost of speed.",
        15, new StatModifier[] {
            new IncreaseStat("maxHealth", 50),
            new MultiplyStat("maxHealth", 2.0),
            new MultiplyStat("speed", 0.5)
        }, 15),

        // END OF TRADEOFF ITEMS, BEGINNING OF HEALS

        new HealItem(200, "Small health potion", "Restores 10% of your max HP", 5, 0.10, 40),
        new HealItem(201, "Small health potion", "Restores 15 HP", 5, 15, 40),
        new HealItem(202, "Medium health potion", "Restores 20% of your max HP", 10, 0.20, 25),
        new HealItem(203, "Medium health potion", "Restores 25 HP", 10, 25, 25),
        new HealItem(204, "Large health potion", "Restores 30% of your max HP", 15, 0.30, 15),
        new HealItem(205, "Large health potion", "Restores 40 HP", 15, 40, 15),
        new HealItem(206, "Giant health potion", "Restores 50% of your max HP", 20, 0.50, 10),
        new HealItem(207, "Giant health potion", "Restores 80 HP", 20, 80, 10),
        new HealItem(208, "Full health potion", "Restores 100% of your max HP", 30, 1.00, 2),
        new HealItem(209, "Huge health potion", "Restores 150 HP", 30, 150, 5),

        // END OF HEALS, BEGINNING OF OTHER/MISC ITEMS

        new Item(1000, "Banana", "It's just a banana.", 1, 1)
    };

    public static final Map<Integer, Item> itemMap = new HashMap<>() {{
        for (Item item : items) {
            put(item.id, item);
        }
    }}; // Stores all items by ID

    public static final Map<Integer, Integer> itemFrequencies = new HashMap<>() {{
        for (Item item : items) {
            put(item.id, item.frequency);
        }
    }}; // Stores the RELATIVE frequency of each item (easy to modify due to automatic normalization after loading into ItemSpawner)

    public static final int itemCount = items.length; // Stores the total number of items in the game

    public static final int itemFrequencySum = itemFrequencies.values().stream().mapToInt(Integer::intValue).sum(); // Stores the sum of all item frequencies (used for normalization)

}
