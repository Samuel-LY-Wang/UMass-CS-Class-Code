package game.Combat.Player;
import game.Combat.Entities.Entity;
import game.Stats.PlayerStats;
import java.util.ArrayList;

public class ActionMapper {
    public static final String[] actions = {"move", "melee", "ranged", "fireball", "ice spike", "heal", "undo", "retreat"};
    public static final boolean[] isCombatAction = {true, true, true, true, true, true, true, true};
    public static final boolean[] requiresExtraInfo = {true, true, true, true, true, false, false, false};
    public boolean[] isValid = new boolean[actions.length];
    public final int num_actions = actions.length;
    public final Player p;
    public ActionMapper(Player p) {
        this.p = p;
        for (int i=0; i<num_actions; i++) {
            isValid[i] = true;
        }
        if (!p.isMagicUser()) {
            isValid[3] = false; // fireball
            isValid[4] = false; // ice spike
        }
    }

    public void updateValidActions(boolean isInShop) {
        if (isInShop) {
            for (int i = 0; i < num_actions; i++) {
                this.isValid[i] = !ActionMapper.isCombatAction[i];
            }
            return;
        }
        // if we're not in Shop
        this.isValid = ActionMapper.isCombatAction.clone();
        if (!p.isMagicUser()) {
            this.isValid[3] = false; // fireball
            this.isValid[4] = false; // ice spike
        }
        this.isValid[6] = (p.getNumUndos() > 0); // undo
    }

    public String[] validActions() {
        ArrayList<String> validList = new ArrayList<>();
        for (int i = 0; i < actions.length; i++) {
            if (isValid[i]) {
                validList.add(actions[i]);
            }
        }
        return validList.toArray(new String[validList.size()]);
    }

    public int getAction(String in) {
        in = in.toLowerCase().trim();
        for (int i = 0; i < actions.length; i++) {
            if (in.equals(actions[i])) {
                return i;
            }
        }
        return -1; // Return -1 if no valid action is found
    }

    public static boolean isAttack(int actionIndex) {
        return actionIndex == 1 || actionIndex == 2 || actionIndex == 3 || actionIndex == 4;
    }

    public static boolean isMove(int actionIndex) {
        return actionIndex == 0;
    }

    public static boolean isHeal(int actionIndex) {
        return actionIndex == 5;
    }

    public static boolean isMagic(int actionIndex) {
        return actionIndex == 3 || actionIndex == 4;
    }

    public boolean takeAction(int actionIndex) {
        return takeAction(actionIndex, null, 0);
    }

    public boolean takeAction(int actionIndex, Entity target) {
        return takeAction(actionIndex, target, 0);
    }

    public boolean takeAction(int actionIndex, double moveDistance) {
        return takeAction(actionIndex, null, moveDistance);
    }

    public boolean takeAction(int actionIndex, Entity target, double moveDistance) {
        // ALWAYS RETURNS: is action successful?
        if (target == null && isAttack(actionIndex)) {
            throw new IllegalArgumentException("Attacks must have a valid target!");
        }
        if (!this.isValid[actionIndex]) {
            return false; // Action is not valid
        }
        switch (actionIndex) {
            case 0 -> {return p.move(moveDistance);}
            case 1 -> {return p.meleeAttack(target);}
            case 2 -> {return p.rangedAttack(target);}
            case 3 -> {
                if (p instanceof Wizard w) {
                    return w.fireBall(target);
                }
                return false;
            }
            case 4 -> {
                if (p instanceof Wizard w) {
                    return w.iceSpike(target);
                }
                return false;
            }
            case 5 -> {
                p.Heal(PlayerStats.BASE_HEAL_AMOUNT);
                return true;
            }
            case 6 -> {return p.Undo();}
            case 7 -> {p.retreat(); return true;}
            default -> {return false;}
        }
    }
}
