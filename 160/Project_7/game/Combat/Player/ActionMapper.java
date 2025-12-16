package game.Combat.Player;
import game.Combat.Enemies.Enemy;
import game.Stats.PlayerStats;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public final class ActionMapper {
    public static final String[] actions = {"move", "melee", "ranged", "fireball", "ice spike", "heal", "undo", "retreat"};
    private static final Map<String, String[]> actionExtraInfoPrompts = new HashMap<>() {{
        put("move", new String[]{"Enter distance to move (positive for forward, negative for backward):"});
        put("melee", new String[]{"Enter the target:"});
        put("ranged", new String[]{"Enter the target:"});
        put("fireball", new String[]{"Enter the target:"});
        put("ice spike", new String[]{"Enter the target:"});
    }};
    private static final Map<String, Set<String>> actionCharacteristics = new HashMap<>() {{
        put("move", Set.of("move"));
        put("melee", Set.of("attack"));
        put("ranged", Set.of("attack"));
        put("fireball", Set.of("attack", "magic"));
        put("ice spike", Set.of("attack", "magic"));
        put("heal", Set.of("healing"));
        put("undo", Set.of());
        put("retreat", Set.of());
    }};
    private final Map<String, Boolean> isValid = new HashMap<>() {{
        for (String action : actions) {
            put(action, true);
        }
    }};
    public final int num_actions = actions.length;
    public final Player p;
    public ActionMapper(Player p) {
        this.p = p;
        if (!p.isMagicUser()) {
            this.isValid.put("fireball", false);
            this.isValid.put("ice spike", false);
        }
        this.isValid.put("undo", (p.getNumUndos() > 0));
    }

    public void updateValidActions(boolean isInShop) {
        this.isValid.put("undo", (p.getNumUndos() > 0)); // undo
    }

    public String[] validActions() {
        ArrayList<String> validList = new ArrayList<>();
        for (String action : actions) {
            if (isValid.get(action)) {
                validList.add(action);
            }
        }
        return validList.toArray(new String[validList.size()]);
    }

    public static boolean isAttack(String action) {
        return actionCharacteristics.getOrDefault(action, Set.of()).contains("attack");
    }

    public static boolean isMove(String action) {
        return actionCharacteristics.getOrDefault(action, Set.of()).contains("move");
    }

    public static boolean isHeal(String action) {
        return actionCharacteristics.getOrDefault(action, Set.of()).contains("healing");
    }

    public static boolean isMagic(String action) {
        return actionCharacteristics.getOrDefault(action, Set.of()).contains("magic");
    }

    public boolean takeAction(String action, Enemy target, double moveDistance) {
        // ALWAYS RETURNS: is action successful?
        if (target == null && isAttack(action)) {
            throw new IllegalArgumentException("Attacks must have a valid target!");
        }
        if (!this.isValid.get(action)) {
            return false; // Action is not valid
        }
        switch (action) {
            case "move" -> {return p.move(moveDistance);}
            case "melee" -> {return p.meleeAttack(target);}
            case "ranged" -> {return p.rangedAttack(target);}
            case "fireball" -> {
                if (p instanceof Wizard w) {
                    return w.fireBall(target);
                }
                return false;
            }
            case "ice spike" -> {
                if (p instanceof Wizard w) {
                    return w.iceSpike(target);
                }
                return false;
            }
            case "heal" -> {
                p.Heal(PlayerStats.BASE_HEAL_AMOUNT);
                return true;
            }
            case "undo" -> {return p.Undo();}
            case "retreat" -> {p.retreat(); return true;}
            default -> {return false;}
        }
    }

    public String[] getExtraInfo(String action) {
        String[] prompts = actionExtraInfoPrompts.get(action);
        if (prompts == null) {
            return null;
        }
        return prompts;
    }
}
