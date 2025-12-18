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
        final GameStack gameStack = new GameStack();
        IO.output("Welcome to the game! Here are the classes:");
        for (String className : PlayerStats.classNames) {
            IO.output(className + ": " + PlayerStats.classDescriptions.get(className));
        }
        String playerClass = InputHandler.getInput("Choose your class:",
            PlayerStats.classNames,
            "Invalid class choice. Please try again."
        );
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
                    String prompt = "Player " + players[i].getName() + ", choose your action:";
                    for (String action: validActions) {
                        prompt = prompt + "\n- " + action;
                    }
                    String playerChoice = InputHandler.getInput(
                        prompt,
                        validActions,
                        "Invalid action choice. Please try again."
                    );
                    String[] extraInfo = actionMapper.getExtraInfo(playerChoice);
                    Enemy target = null;
                    double moveDistance = 0;
                    if (extraInfo != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int j=0; j<enemies.length; j++) {
                            Enemy e = enemies[j];
                            if (e.isAlive()) {
                                sb.append(j).append(": ").append(e.getName()).append(" (HP: ").append(e.getStat("curHealth")).append(")").append(". Located at: ").append(e.getStat("position")).append("\n");
                            }
                        }
                        String enemyInfo = sb.toString().trim();
                        for (String infoPrompt : extraInfo) {
                            if (infoPrompt.toLowerCase().contains("target")) {
                                int targetIndex = InputHandler.getIntInput(
                                    infoPrompt + "\n" + enemyInfo,
                                    0,
                                    enemies.length - 1,
                                    "Invalid target choice. Please try again."
                                );
                                target = enemies[targetIndex];
                            }
                            else if (infoPrompt.toLowerCase().contains("distance")) {
                                moveDistance = InputHandler.getDoubleInput(
                                    infoPrompt,
                                    -1*p.getStat("speed"),
                                    p.getStat("speed"),
                                    "Cannot move that distance! Your speed is: "+ p.getStat("speed")
                                );
                            }
                        }
                    }
                    boolean successful = actionMapper.takeAction(playerChoice, target, moveDistance);
                    IO.output("Action " + (successful ? "succeeded!" : "failed."));
                }
                // all players have acted
                for (Enemy enemy : enemies) {
                    if (enemy.isAlive()) {
                        // chooses random player to target on
                        int targetPlayerIndex = RNG.randomIntInRange(0, players.length - 1);
                        Player targetPlayer = players[targetPlayerIndex];
                        EnemyController.enemyAction(enemy, targetPlayer);
                    }
                }
                // all enemies have acted
                GameState cur_state = new GameState(players, enemies);
                gameStack.push_new(cur_state);
                if (Utils.isWaveOver(players, enemies)) {
                    waveInProgress = false;
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
                StringBuilder sb = new StringBuilder();
                sb.append("Available items for purchase:");
                for (int i = 0; i < shopItems.length; i++) {
                    Item item = shopItems[i];
                    sb.append("\n" + i + ": " + item.getName() + " - " + item.getDescription() + " (Cost: " + item.getPrice() + " gold)");
                }
                sb.append("\n You have " + p.getStat("gold") + " gold.\n");
                sb.append("Your options are:\nEnter purchase to purchase an item\nEnter exit to leave the shop");
                String shopPrompt = sb.toString();
                String shopChoice = InputHandler.getInput(
                    shopPrompt,
                    new String[]{"purchase", "exit"},
                    "Invalid option. Please choose again."
                );
                if (shopChoice.equals("exit")) {
                    inShop = false;
                    IO.output("Exiting shop...");
                }
                else if (shopChoice.equals("purchase")) {
                    int numItems = shopItems.length;
                    int indexChosen = InputHandler.getIntInput(
                        "Enter the item number you wish to purchase (0 to " + (numItems - 1) + "):",
                        0,
                        numItems - 1,
                        "Invalid item number. Please choose again."
                    );
                    Item itemToPurchase = shopItems[indexChosen];
                    int itemPrice = itemToPurchase.getPrice();
                    boolean successful = p.spendGold(itemPrice);
                    if (successful) {
                        IO.output("You have purchased " + itemToPurchase.getName() + " for " + itemPrice + " gold.");
                        itemToPurchase.apply(p);
                    }
                    else {
                        IO.output("You cannot afford this item.");
                    }
                }
            }
        }
        // Game over message
        IO.output("Game over. You reached wave " + wave + ".");
    }
}
