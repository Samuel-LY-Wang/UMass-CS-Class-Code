package game.Stats;

public final class ScalingFuncs {
    private ScalingFuncs() {
        throw new AssertionError("Class cannot be instantiated, instances prohibited.");
    }

    public static final double getMarginalHPATKMMult(int wave) {
        // Returns the MARGINAL MULTIPLIER (health and attack only)
        if (wave < 0) {
            throw new IllegalArgumentException("Wave cannot be negative: " + wave);
        }
        if (wave < 6) {
            return 1.0;
        }
        else if (wave < 11) {
            return 1.1;
        }
        else if (wave < 21) {
            return 1.2;
        }
        else if (wave < 41) {
            return 1.25;
        }
        else {
            return 1.33;
        }
    }

    public static final double getHPATKMult(int wave) {
        if (wave<6) return 1.0;
        else if (wave < 11) return Math.pow(1.1, (wave-5));
        else if (wave < 20) return Math.pow(1.1, 5)*Math.pow(1.2, (wave-10));
        else if (wave < 40) return Math.pow(1.1, 5)*Math.pow(1.2, 10)*Math.pow(1.25, (wave-20));
        else return Math.pow(1.1, 5)*Math.pow(1.2, 10)*Math.pow(1.25, 20)*Math.pow(1.33, (wave-40));
    }

    public static final double getSpeedMult(int wave) {
        // Returns the CUMULATIVE MULTIPLIER (speed only)
        if (wave < 0) {
            throw new IllegalArgumentException("Wave cannot be negative: " + wave);
        }
        if (wave < 6) {
            return 1.0;
        }
        else {
            return 1.0+0.05*(wave-5); // constant 5% faster per wave
        }
    }

    private static double getMarginalDefMod(int wave, int defScaleTimer, double defScaleAmount) {
        // Returns the MARGINAL ADDITIVE modifier (defense only)
        if (wave < 0) {
            throw new IllegalArgumentException("Wave cannot be negative: " + wave);
        }
        else {
            return Math.floor(wave/defScaleTimer)*defScaleAmount; // constant +0.5 defense per wave
        }
    }

    public static final double getDefMod(int wave, int defScaleTimer, double defScaleAmount) {
        // Returns the CUMULATIVE ADDITIVE modifier (defense only)
        if (wave < 0) {
            throw new IllegalArgumentException("Wave cannot be negative: " + wave);
        }
        double totalDef = 0.0;
        for (int i = 0; i <= wave; i++) {
            totalDef += getMarginalDefMod(i, defScaleTimer, defScaleAmount);
        }
        return totalDef;
    }

    public static final double getScaledHP(int wave, String enemyType) {
        int baseHP = EnemyStats.baseStats.get(enemyType)[0];
        double mult = getHPATKMult(wave);
        return baseHP * mult;
    }

    public static final double getScaledATK(int wave, String enemyType) {
        int baseATK = EnemyStats.baseStats.get(enemyType)[1];
        double mult = getHPATKMult(wave);
        return baseATK * mult;
    }

    public static final double getScaledSpeed(int wave, String enemyType) {
        int baseSpeed = EnemyStats.baseStats.get(enemyType)[3];
        double mult = getSpeedMult(wave);
        return baseSpeed * mult;
    }

    public static final double getScaledDef(int wave, String enemyType) {
        int baseDef = EnemyStats.baseStats.get(enemyType)[2];
        double mod = getDefMod(wave, EnemyStats.defScaleTimers.get(enemyType), EnemyStats.defScaleAmounts.get(enemyType));
        return baseDef + mod; // DEFENSE IS ADDITIVE RATHER THAN MULTIPLICATIVE (balancing probably, as defense blocks attacks)
    }
}