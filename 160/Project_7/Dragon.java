import java.util.Random;

public class Dragon extends CombatEntity implements AIControlled {
    private int healAmount;
    public Dragon(String name) {
        super(name, 150, 20);
        this.healAmount = 20;
    }

    @Override
    public String attack(CombatEntity target) {
        return target.takeHit(this.attackPower, this.name);
    }

    @Override
    public String takeTurn(CombatEntity target) {
        Random rng = new Random();
        int d20Roll = rng.nextInt(20)+1; // simulate d20 roll
        switch (d20Roll) {
            case 16, 17, 18, 19:
                target.takeHit(this.attackPower*2, "");
                // {Name} scorched {target name} with searing fire, dealing {amount} damage
                return this.name + " scorched " + target.name + " with searing fire, dealing " + (this.attackPower*2) + " damage";
            case 20:
                target.takeHit(this.attackPower*3, this.name);
                this.recover(this.healAmount, "");
                // {Name} took a bite out of {target} dealing {amount} damage and recovering {healing amount} health!
                return this.name + " took a bite out of " + target.name + " dealing " + (this.attackPower*3) + " damage and recovering " + this.healAmount + " health!";
            default:
                return this.attack(target);
        }
    }
}
