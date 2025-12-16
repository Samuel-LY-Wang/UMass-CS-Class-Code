package game;
import game.Combat.Enemies.Enemy;
import game.Combat.Enemies.EnemyController;
import game.Combat.Enemies.EnemySpawner;
import game.Combat.Player.ActionMapper;
import game.Combat.Player.Player;
import game.GameHistory.GameStack;
import game.GameHistory.GameState;
import game.Items.Item;
import game.Items.ItemSpawner;
import game.Stats.PlayerStats;
import game.Stats.RNG;

public class Main {
    public static void main(String[] args) {
        int wave = 0;
        boolean waveInProgress;
        String playerClass = null;
        final GameStack gameStack = new GameStack();
        IO.output("Welcome to the game! Please choose your class:");
        for (String className : PlayerStats.classNames) {
            IO.output(className + ": " + PlayerStats.classDescriptions.get(className));
        }
        while (playerClass == null) {
            String input = IO.getInput();
            if (Utils.contains(input, PlayerStats.classNames)) {
                IO.output("You have chosen the " + input + " class!");
                playerClass = input;
            }
            else {
                IO.output("Invalid class choice. Please try again.");
            }
        }
        IO.output("Enter a name for your " + playerClass + ":");
        String input = IO.getInput();
        Player p = PlayerFactory.spawnPlayer(playerClass, input);
        final Player[] players = new Player[] {p};
        final ActionMapper[] actionMappers = new ActionMapper[] {new ActionMapper(p)};
        Enemy[] enemies;
        while (true) {
            wave++;
            waveInProgress = true;
            IO.output("Starting wave " + wave + "...");
            IO.output("Prepare for battle, " + input + " the " + playerClass + "!");
            // spawns enemies
            enemies = EnemySpawner.spawnEnemies(wave);
            while (waveInProgress) {
                for (Player player : players) {
                    IO.output(Utils.getPlayerStatus(player));
                }
                for (Enemy enemy : enemies) {
                    IO.output(Utils.getEnemyStatus(enemy));
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
                        IO.output("Player " + players[i].getName() + ", choose your action:");
                        for (String action : validActions) {
                            IO.output("- " + action);
                        }
                        int choice = actionMappers[i].getAction(IO.getInput());
                        if (choice == -1) {
                            IO.output("Invalid action. Please choose again:");
                            continue;
                        }
                        if (!actionMappers[i].isValid[choice]) {
                            IO.output("Invalid action. Please choose again:");
                            continue;
                        }
                        // ask for more info if the action is a move or attack type (more info required)
                        hasChosenAction = true;
                        if (ActionMapper.requiresExtraInfo[choice]) {
                            if (choice == 0) { // move
                                IO.output("Enter distance to move:");
                                String distanceStr = IO.getInput();
                                double distance;
                                try {
                                    distance = Double.parseDouble(distanceStr);
                                } catch (NumberFormatException e) {
                                    IO.output("Invalid distance. Action cancelled.");
                                    hasChosenAction = false;
                                    continue;
                                }
                                IO.output("Moving " + distance + " units.");
                                boolean success = actionMapper.takeAction(choice, null, distance);
                                if (!success) {
                                    IO.output("Move action failed. Please choose again:");
                                    hasChosenAction = false;
                                }
                                continue;
                            }
                            if (ActionMapper.isAttack(choice)) {
                                // player chose to attack
                                IO.output("Choose your target:");
                                for (int j=0; j<enemies.length; j++) {
                                    IO.output(j + ": " + Utils.getEnemyStatus(enemies[j]));
                                }
                                String targetStr = IO.getInput();
                                int targetIndex;
                                try {
                                    targetIndex = Integer.parseInt(targetStr);
                                } catch (NumberFormatException e) {
                                    IO.output("Invalid target. Action cancelled.");
                                    hasChosenAction = false;
                                    continue;
                                }
                                Enemy target = enemies[targetIndex];
                                IO.output("Attacking " + target.getName() + " at position "+String.format("%.2f", target.getStat("position"))+".");
                                boolean success = actionMapper.takeAction(choice, target);
                                if (!success) {
                                    IO.output("Attack failed!"); // no continue, as the attack was valid but failed
                                }
                                else {
                                    IO.output("Attack successful!");
                                }
                            }
                        }
                        else {
                            IO.output("Action '" + ActionMapper.actions[choice] + "' selected.");
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
            IO.output("Wave " + wave + " completed!");
            IO.output("Visiting shop...");
            boolean inShop = true;
            Item[] shopItems = ItemSpawner.SpawnItems(5);
            while (inShop) {
                boolean hasChosen = false;
                IO.output("Available items for purchase:");
                for (int i = 0; i < shopItems.length; i++) {
                    Item item = shopItems[i];
                    IO.output(i + ": " + item.getName() + " - " + item.getDescription() + " (Cost: " + item.getPrice() + " gold)");
                }
                IO.output("You have " + p.getStat("gold") + " gold.");
                while (!hasChosen) {
                    IO.output("Your options are:");
                    IO.output("Enter purchase to purchase an item");
                    IO.output("Enter exit to leave the shop");
                    String shopInput = IO.getInput();
                    switch (shopInput) {
                        case "purchase" -> {
                            IO.output("Enter the item number you wish to purchase:");
                            String itemInput = IO.getInput();
                            int itemIndex;
                            try {
                                itemIndex = Integer.parseInt(itemInput);
                            } catch (NumberFormatException e) {
                                IO.output("Invalid item number. Please choose again.");
                                continue;
                            }
                            // try to purchase the item
                            if (itemIndex < 0 || itemIndex >= shopItems.length) {
                                IO.output("Invalid item number. Please choose again.");
                                continue;
                            }
                            Item itemToPurchase = shopItems[itemIndex];
                            int itemPrice = itemToPurchase.getPrice();
                            boolean successful = p.spendGold(itemPrice);
                            if (successful) {
                                IO.output("You have purchased " + itemToPurchase.getName() + " for " + itemPrice + " gold.");
                                itemToPurchase.apply(p);
                            }
                            else {
                                IO.output("You do not have enough gold to purchase this item.");
                            }
                        }
                        case "exit" -> {
                            inShop = false;
                            hasChosen = true;
                            IO.output("Exiting shop...");
                        }
                        default -> {
                            IO.output("Invalid option. Please choose again.");
                        }
                    }
                }
            }
        }
        // Game over message
        IO.output("Game over. You reached wave " + wave + ".");
    }
}
