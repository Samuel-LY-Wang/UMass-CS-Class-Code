import java.util.Scanner;

public interface PlayerControlled {
    String chooseAction(Scanner input, CombatEntity target);
}