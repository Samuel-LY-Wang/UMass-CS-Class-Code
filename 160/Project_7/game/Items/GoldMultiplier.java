package game.Items;

import game.CombatEntities.Entity;
import game.CombatEntities.Player.Player;

public class GoldMultiplier implements StatModifier {
    private final double factor;
    public GoldMultiplier(double factor) {
        this.factor = factor;
    }

    @Override
    public void applyModifier(Entity entity) {
        if (!(entity instanceof Player)) {
            throw new IllegalArgumentException("GoldGiver can only be applied to Player entities.");
        }
        ((Player) entity).updateGoldMult(factor);
    }
}
