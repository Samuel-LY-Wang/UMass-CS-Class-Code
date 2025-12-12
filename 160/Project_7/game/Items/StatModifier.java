package game.Items;

import game.CombatEntities.Entity;

public interface StatModifier {
    void applyModifier(Entity entity);
}