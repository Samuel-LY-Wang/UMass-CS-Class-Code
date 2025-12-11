package game;

public class ScalingFuncs {
    private static double getOtherStatMult(int wave) {
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

    public static double getHPandATKmult(int wave) {
        // Returns the CUMULATIVE MULTIPLIER (health and attack only)
        if (wave < 0) {
            throw new IllegalArgumentException("Wave cannot be negative: " + wave);
        }
        double mult = 1.0;
        for (int i = 0; i <= wave; i++) {
            mult *= getOtherStatMult(i);
        }
        return mult;
    }

    public static double getSpeedMult(int wave) {
        // Returns the CUMULATIVE MULTIPLIER (speed only)
        if (wave < 0) {
            throw new IllegalArgumentException("Wave cannot be negative: " + wave);
        }
        if (wave < 6) {
            return 1.0;
        }
        else {
            return 1.0+0.05*(wave-6); // constant 5% faster per wave
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

    public static double getDefMod(int wave, int defScaleTimer, double defScaleAmount) {
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
}
