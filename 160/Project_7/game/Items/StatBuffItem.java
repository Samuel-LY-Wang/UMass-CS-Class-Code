package game.Items;

import game.CombatEntities.Entity;

public class StatBuffItem extends Item {
    protected final int numBuffs;
    protected final StatModifier[] mods;

    public StatBuffItem(int id, String name, String description, int price, StatModifier[] mods) {
        super(id, name, description, price);
        this.mods = mods;
        this.numBuffs = mods.length;
    }

    public boolean canBePurchased (int cur_gold) {
        return cur_gold >= this.price;
    }

    public void applyBuffs (Entity c) {
        for (int i = 0; i < this.numBuffs; i++) {
            this.mods[i].applyModifier(c);
        }
    }
}