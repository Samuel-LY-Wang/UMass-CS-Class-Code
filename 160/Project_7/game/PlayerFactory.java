package game;
import game.Combat.Player.Player;
import game.Combat.Player.Wizard;
import game.Stats.PlayerStats;

public final class PlayerFactory {
    private PlayerFactory() {throw new AssertionError("Class cannot be instantiated, instances prohibited.");} // Prevent instantiation

    public static final Player spawnPlayer(String className, String name) {
        return switch (className) {
            case "Wizard" -> new Wizard(name, PlayerStats.START_POS);
            default -> new Player(className, name, PlayerStats.START_POS);
        };
    }
}
