package game.Items;

import game.CombatEntities.Entity;

public class IncreaseStat implements StatModifier {
    private final String stat;
    private final int amount;
    public IncreaseStat(String stat, int amount) {
        this.stat = stat;
        this.amount = amount;
    }

    @Override
    public void applyModifier(Entity entity) {
        entity.updateStat(this.stat, this.amount);
    }
}
