import java.util.Scanner;

public class GameDriver {
    private static GameState currentState;
    public static GameState getCurrentGameState() {
        return currentState;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Dragon dragon = new Dragon("Enemy Dragon");
        Wizard wizard = new Wizard("Your Wizard");
        GameStack history = new GameStack();
        final CombatEntity[] p = {wizard, dragon};
        final int[] pHealth = {wizard.getHealth(), dragon.getHealth()};
        boolean gameRunning = true;
        System.out.println("Welcome to the game!");
        while (gameRunning) {
            System.out.println("Current game state:");
            System.out.println(wizard.getName() + " Health: " + wizard.getHealth());
            System.out.println(dragon.getName() + " Health: " + dragon.getHealth());
            currentState = new GameState(p, pHealth);
            String wizardAction = wizard.chooseAction(s, dragon);
            switch (wizardAction) {
                case "RETREAT":
                    System.out.println(wizard.getName() + " runs away, and lives to fight another day");
                    gameRunning = false;
                    break;
                case "UNDO":
                    GameState previousState = history.pop();
                    if (previousState != null) {
                        pHealth[0] = previousState.getCEHealth(0);
                        pHealth[1] = previousState.getCEHealth(1);
                        System.out.println("Reverted to previous state.");
                    } else {
                        System.out.println("No previous state to revert to.");
                    }
                default:
                    history.push(currentState);
                    System.out.println(wizardAction);
                    if (dragon.getHealth() <= 0) {
                        System.out.println("You have defeated the dragon! Victory!");
                        gameRunning = false;
                        break;
                    }
                    if (gameRunning) {
                        String dragonAction = dragon.takeTurn(wizard);
                        System.out.println(dragonAction);
                        if (wizard.getHealth() <= 0) {
                            System.out.println("You have been defeated by the dragon. Game over.");
                            gameRunning = false;
                            break;
                        }
                    }
            }
        }
    }
}