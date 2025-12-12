package game.Combat.Player;
import java.util.Scanner;

public class ActionMapper {
    public final String[] actions = {"move", "melee", "ranged", "fireball", "iceball", "poison", "heal", "undo", "retreat"};
    public final int num_actions = actions.length;
    public final Scanner s;
    public final Player p;
    public ActionMapper(Scanner s, Player p) {
        this.s = s;
        this.p = p;
    }

    public int getAction() {
        String in = s.nextLine().toLowerCase().trim();
        for (int i = 0; i < actions.length; i++) {
            if (in.equals(actions[i])) {
                return i;
            }
        }
        return -1; // Return -1 if no valid action is found
    }
}
