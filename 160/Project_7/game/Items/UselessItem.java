package game.Items;
import game.Combat.Player.Player;

public class UselessItem extends Item {
    public UselessItem(int id, String name, String description, int price, int frequency) {
        super(id, name, description, price, frequency);
    }
    @Override
    public void apply(Player p) { /* does nothing */ }
}
