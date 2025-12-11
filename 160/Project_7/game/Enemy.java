package game;

public class Enemy extends Entity {
    public static double getClassHealthMult(String className) {
        if (!ClassMultMap.containsKey(className)) {
            throw new IllegalArgumentException("Invalid enemy class name: " + className);
        }
        return ClassMultMap.get(className)[0];
    }

    public static double getClassAttackMult(String className) {
        if (!ClassMultMap.containsKey(className)) {
            throw new IllegalArgumentException("Invalid enemy class name: " + className);
        }
        return ClassMultMap.get(className)[1];
    }

    public static double getClassSpeedMult(String className) {
        if (!ClassMultMap.containsKey(className)) {
            throw new IllegalArgumentException("Invalid enemy class name: " + className);
        }
        return ClassMultMap.get(className)[2];
    }

    private final double defScaleMod;
    private final int defScaleTimer;
    private final double defScale; // ADDS defense
    private final double spdScale; // MULTIPLIES speed
    private final double otherStatScale; // MULTIPLIES other stats
    public Enemy(int wave, int baseHealth, int baseAttack, int baseDefense, int baseSpeed, double position, int defScaleTimer, double defScaleMod) {
        super("Enemy",
              baseHealth,
              baseAttack,
              baseDefense,
              baseSpeed,
              position);
        this.spdScale = ScalingFuncs.getSpeedMult(wave);
        this.defScaleTimer = defScaleTimer;
        this.defScaleMod = defScaleMod;
        this.defScale = ScalingFuncs.getDefMod(wave, this.defScaleTimer, this.defScaleMod);
        this.otherStatScale = ScalingFuncs.getHPandATKmult(wave);
        this.multiplyAttack(this.otherStatScale);
        this.multiplyMaxHealth(this.otherStatScale);
        this.setSpeedMult(this.spdScale);
        this.updateBaseDefense(this.defScale);
    }

    public String getClassName() {
        return this.className;
    }

    public double getDefScaleMod() {
        return this.defScaleMod;
    }

    public int getDefScaleTimer() {
        return this.defScaleTimer;
    }

}