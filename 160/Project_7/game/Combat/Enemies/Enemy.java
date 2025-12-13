package game.Combat.Enemies;

import game.Combat.Entity;

public class Enemy extends Entity {

    protected final String className;
    public Enemy(int baseHealth, int baseAttack, int baseDefense, int baseSpeed, double position, String className) {
        super("Enemy",
              baseHealth,
              baseAttack,
              baseDefense,
              baseSpeed,
              position);
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

}