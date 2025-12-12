package game.Items;

import game.Combat.Player.Player;

public class IncreaseStat implements StatModifier {
    private final String stat;
    private final int amount;
    public IncreaseStat(String stat, int amount) {
        this.stat = stat;
        this.amount = amount;
    }

    @Override
    public void applyModifier(Player entity) {
        entity.updateStat(this.stat, this.amount);
    }
}
