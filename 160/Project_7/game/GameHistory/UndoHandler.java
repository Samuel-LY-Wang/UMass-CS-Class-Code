package game.GameHistory;
import game.Combat.Enemies.Enemy;
import game.Combat.Player.Player;

public class UndoHandler {
    private static GameStack gameStack;
    private static Enemy[] enemies;
    private static Player[] players;
    public UndoHandler(GameStack stack, Enemy[] enemies, Player[] players) {
        UndoHandler.gameStack = stack;
        UndoHandler.enemies = enemies;
        UndoHandler.players = players;
    }
    public static void handleUndo() {
        if (!gameStack.canUndo()) {
            return;
        }
        // Get previous state
        GameState prevState = gameStack.pop();
        // Restore enemies from stats
        for (int i=0; i<enemies.length; i++) {
            restoreEnemyFromStat(prevState, i);
        }
        // Restore players from stats
        for (int j=0; j<players.length; j++) {
            restorePlayerFromStat(prevState, j);
        }
    }
    public static void restoreEnemyFromStat(GameState state, int enemyIndex) {
        Enemy e = UndoHandler.enemies[enemyIndex];
        e.setSpeedMod(state.getEnemySpeedMod()[enemyIndex]);
        e.move(state.getEnemyPositions()[enemyIndex]-e.getStat("position"));
        e.Heal(state.getEnemyHealth()[enemyIndex]-e.getStat("curHealth"));
        e.setAliveness(state.getEnemyAliveStatus()[enemyIndex]);
    }
    public static void restorePlayerFromStat(GameState state, int playerIndex) {
        Player p = UndoHandler.players[playerIndex];
        p.move(state.getPlayerPositions()[playerIndex]-p.getStat("position"));
        p.Heal(state.getPlayerHealth()[playerIndex]-p.getStat("curHealth"));
        p.setAliveness(state.getPlayerAliveStatus()[playerIndex]);
    }
}
