package game.Items;
import game.Combat.Player.Player;
import game.Stats.RNG;

public class RandomItem extends Item {
    protected final double chance; // probability of effect occurring
    protected final Item effect; // effect to apply
    public RandomItem(int id, String name, String description, double chance, Item effect, int price, int frequency) {
        super(id, name, description, price, frequency);
        this.chance = chance;
        this.effect = effect;
    }
    @Override
    public void apply(Player p) {
        if (RNG.chance(this.chance)) {
            this.effect.apply(p);
        }
    }
}
