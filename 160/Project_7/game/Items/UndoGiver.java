package game.Items;

import game.CombatEntities.Entity;
import game.CombatEntities.Player.Player;

public class UndoGiver implements StatModifier {
    private final int amount;
    public UndoGiver(int amount) {
        this.amount = amount;
    }

    @Override
    public void applyModifier(Entity entity) {
        if (!(entity instanceof Player)) {
            throw new IllegalArgumentException("UndoGiver can only be applied to Player entities.");
        }
        ((Player) entity).giveUndos(amount);
    }
}
