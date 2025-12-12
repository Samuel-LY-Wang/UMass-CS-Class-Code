package game.Items;

import game.Combat.Player.Player;

public class HealItem extends Item {
    private final int healAmount;
    private final double healPercent;
    public HealItem(int id, String name, String description, int price, int healAmount, int frequency) {
        super(id, name, description, price, frequency);
        this.healAmount = healAmount;
        this.healPercent = 0.0;
    }
    public HealItem(int id, String name, String description, int price, double healPercent, int frequency) {
        super(id, name, description, price, frequency);
        this.healAmount = 0;
        this.healPercent = healPercent;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public double getHealPercent() {
        return healPercent;
    }
    
    public void use(Player player) {
        if (this.healAmount > 0) {
            player.Heal(this.healAmount);
        } else if (this.healPercent > 0.0) {
            player.Heal(player.getMaxHealth() * this.healPercent);
        }
    }
}
