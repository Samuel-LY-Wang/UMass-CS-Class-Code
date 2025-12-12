package game.Items;

import game.Combat.Player.Player;

public interface StatModifier {
    void applyModifier(Player entity);
}