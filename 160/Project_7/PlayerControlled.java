import java.util.Scanner;

public interface PlayerControlled {
    String ChooseAction(Scanner input, CombatEntity target);
}