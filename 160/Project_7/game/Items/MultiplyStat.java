package game.Items;

import game.CombatEntities.Entity;

public class MultiplyStat implements StatModifier {
    private final String stat;
    private final double factor;
    public MultiplyStat(String stat, double factor) {
        this.stat = stat;
        this.factor = factor;
    }

    @Override
    public void applyModifier(Entity entity) {
        entity.multiplyStat(this.stat, this.factor);
    }
}
