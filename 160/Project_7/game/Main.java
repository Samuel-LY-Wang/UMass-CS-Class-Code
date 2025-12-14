package game;
import game.Stats.PlayerStats;
import game.Stats.RNG;
import game.Combat.Enemies.Enemy;
import game.Combat.Enemies.EnemySpawner;
import game.Combat.Enemies.EnemyController;
import game.Combat.Player.Player;
import game.Combat.Player.Wizard;
import game.Combat.Player.ActionMapper;
import game.GameHistory.GameStack;
import game.GameHistory.GameState;
import game.Items.Item;
import game.Items.ItemSpawner;

public class Main {
    public static void main(String[] args) {
        int wave = 0;
        boolean waveInProgress;
        String playerClass = null;
        final GameStack gameStack = new GameStack();
        System.out.println("Welcome to the game! Please choose your class:");
        for (String className : PlayerStats.classNames) {
            System.out.println(className + ": " + PlayerStats.classDescriptions.get(className));
        }
        while (playerClass == null) {
            String input = InputHandler.getInput();
            switch (input) {
                case "Human", "Elf", "Giant", "Brawler", "Rogue", "Merchant" -> {
                    System.out.println("You have chosen the " + input + " class!");
                    playerClass = input;
                    // Initialize player with chosen class stats here
                }
                case "Wizard" -> {
                    System.out.println("You have chosen the Wizard class!");
                    playerClass = input;
                    // Initialize wizard player here
                }
                default -> System.out.println("Invalid class choice. Please try again.");
            }
        }
        System.out.println("Enter a name for your " + playerClass + ":");
        String input = InputHandler.getInput();
        Player p;
        if (playerClass.equals("Wizard")) {
            p = new Wizard(input, PlayerStats.START_POS);
        }
        else {
            p = new Player(playerClass, input, PlayerStats.START_POS);
        }
        final Player[] players = new Player[] {p};
        final ActionMapper[] actionMappers = new ActionMapper[] {new ActionMapper(p)};
        Enemy[] enemies;
        while (true) {
            wave++;
            waveInProgress = true;
            System.out.println("Starting wave " + wave + "...");
            System.out.println("Prepare for battle, " + input + " the " + playerClass + "!");
            // spawns enemies
            enemies = EnemySpawner.spawnEnemies(wave);
            while (waveInProgress) {
                for (Player player : players) {
                    System.out.println(Utils.getPlayerStatus(player));
                }
                for (Enemy enemy : enemies) {
                    System.out.println(Utils.getEnemyStatus(enemy));
                }
                for (int i=0; i<players.length; i++) {
                    actionMappers[i].updateValidActions(false);
                }
                for (int i=0; i<players.length; i++) {
                    ActionMapper actionMapper = actionMappers[i];
                    String[] validActions = actionMapper.validActions();
                    boolean hasChosenAction = false;
                    // asks for and processes player action
                    while (!hasChosenAction) {
                        System.out.println("Player " + players[i].getName() + ", choose your action:");
                        for (String action : validActions) {
                            System.out.println("- " + action);
                        }
                        int choice = actionMappers[i].getAction(InputHandler.getInput());
                        if (choice == -1) {
                            System.out.println("Invalid action. Please choose again:");
                            continue;
                        }
                        if (!actionMappers[i].isValid[choice]) {
                            System.out.println("Invalid action. Please choose again:");
                            continue;
                        }
                        // ask for more info if the action is a move or attack type (more info required)
                        hasChosenAction = true;
                        if (ActionMapper.requiresExtraInfo[choice]) {
                            if (choice == 0) { // move
                                System.out.println("Enter distance to move:");
                                String distanceStr = InputHandler.getInput();
                                double distance;
                                try {
                                    distance = Double.parseDouble(distanceStr);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid distance. Action cancelled.");
                                    hasChosenAction = false;
                                    continue;
                                }
                                System.out.println("Moving " + distance + " units.");
                                boolean success = actionMapper.takeAction(choice, null, distance);
                                if (!success) {
                                    System.out.println("Move action failed. Please choose again:");
                                    hasChosenAction = false;
                                }
                                continue;
                            }
                            if (ActionMapper.isAttack(choice)) {
                                // player chose to attack
                                System.out.println("Choose your target:");
                                for (int j=0; j<enemies.length; j++) {
                                    System.out.println(j + ": " + Utils.getEnemyStatus(enemies[j]));
                                }
                                String targetStr = InputHandler.getInput();
                                int targetIndex;
                                try {
                                    targetIndex = Integer.parseInt(targetStr);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid target. Action cancelled.");
                                    hasChosenAction = false;
                                    continue;
                                }
                                Enemy target = enemies[targetIndex];
                                System.out.println("Attacking " + target.getName() + " at position "+String.format("%.2f", target.getStat("position"))+".");
                                boolean success = actionMapper.takeAction(choice, target);
                                if (!success) {
                                    System.out.println("Attack failed!"); // no continue, as the attack was valid but failed
                                }
                                else {
                                    System.out.println("Attack successful!");
                                }
                            }
                        }
                        else {
                            System.out.println("Action '" + ActionMapper.actions[choice] + "' selected.");
                            actionMapper.takeAction(choice);
                        }
                    }
                    // now the player has acted
                    // enemy actions
                    for (Enemy enemy : enemies) {
                        if (enemy.isAlive()) {
                            // chooses random player to target on
                            int targetPlayerIndex = RNG.rng.nextInt(players.length);
                            Player targetPlayer = players[targetPlayerIndex];
                            EnemyController.enemyAction(enemy, targetPlayer);
                        }
                    }
                    GameState cur_state = new GameState(players, enemies);
                    gameStack.push_new(cur_state);
                    if (Utils.isWaveOver(players, enemies)) {
                        waveInProgress = false;
                    }
                }
            }
            // Wave over actions
            // if Player died, end game
            if (Utils.isPlayerDead(players)) {
                break;
            }
            // otherwise, go to shop and prepare for next wave
            System.out.println("Wave " + wave + " completed!");
            System.out.println("Visiting shop...");
            boolean inShop = true;
            Item[] shopItems = ItemSpawner.SpawnItems(5);
            while (inShop) {
                boolean hasChosen = false;
                System.out.println("Available items for purchase:");
                for (int i = 0; i < shopItems.length; i++) {
                    Item item = shopItems[i];
                    System.out.println(i + ": " + item.getName() + " - " + item.getDescription() + " (Cost: " + item.getPrice() + " gold)");
                }
                System.out.println("You have " + p.getStat("gold") + " gold.");
                while (!hasChosen) {
                    System.out.println("Your options are:");
                    System.out.println("Enter purchase to purchase an item");
                    System.out.println("Enter exit to leave the shop");
                    String shopInput = InputHandler.getInput();
                    switch (shopInput) {
                        case "purchase" -> {
                            System.out.println("Enter the item number you wish to purchase:");
                            String itemInput = InputHandler.getInput();
                            int itemIndex;
                            try {
                                itemIndex = Integer.parseInt(itemInput);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid item number. Please choose again.");
                                continue;
                            }
                            // try to purchase the item
                            if (itemIndex < 0 || itemIndex >= shopItems.length) {
                                System.out.println("Invalid item number. Please choose again.");
                                continue;
                            }
                            Item itemToPurchase = shopItems[itemIndex];
                            int itemPrice = itemToPurchase.getPrice();
                            boolean successful = p.spendGold(itemPrice);
                            if (successful) {
                                System.out.println("You have purchased " + itemToPurchase.getName() + " for " + itemPrice + " gold.");
                                itemToPurchase.apply(p);
                            }
                            else {
                                System.out.println("You do not have enough gold to purchase this item.");
                            }
                        }
                        case "exit" -> {
                            inShop = false;
                            hasChosen = true;
                            System.out.println("Exiting shop...");
                        }
                        default -> {
                            System.out.println("Invalid option. Please choose again.");
                            continue;
                        }
                    }
                }
            }
            // Game over message
            System.out.println("Game over. You reached wave " + wave + ".");
        }
    }
}
