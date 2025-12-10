import java.util.Scanner;

public class Wizard extends CombatEntity implements PlayerControlled {
    private static final int HEAL_AMOUNT = 20;
    public Wizard(String name) {
        super(name, 100, 15);
    }

    @Override
    public String attack(CombatEntity target) {
        return target.takeHit(this.attackPower, this.name + "'s magic");
    }

    @Override
    public String chooseAction(Scanner input, CombatEntity target) {
        while (true) {
            System.out.println("Choose action for " + this.name + ":");
            System.out.println("0. Retreat");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Undo");
            int choice = input.nextInt();
            switch (choice) {
                case 0:
                    return "RETREAT";
                case 1:
                    return this.attack(target);
                case 2:
                    return this.recover(HEAL_AMOUNT, this.name + "'s spell");
                case 3:
                    return "UNDO";
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
