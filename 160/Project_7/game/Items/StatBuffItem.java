package game.Items;

import game.Combat.Player.Player;

public class StatBuffItem extends Item {
    protected final int numBuffs;
    protected final StatModifier[] mods;

    public StatBuffItem(int id, String name, String description, int price, StatModifier[] mods, int frequency) {
        super(id, name, description, price, frequency);
        this.mods = mods;
        this.numBuffs = mods.length;
    }

    @Override
    public void apply (Player c) {
        for (int i = 0; i < this.numBuffs; i++) {
            this.mods[i].applyModifier(c);
        }
    }
}