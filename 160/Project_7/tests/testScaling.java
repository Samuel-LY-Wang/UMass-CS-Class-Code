package tests;
import org.junit.Test;
import static org.junit.Assert.*;
import game.Stats.ScalingFuncs;

public class testScaling {
    @Test
    public void testHPATKScalingFuncs() {
        // Test the scaling functions with various inputs
        for (int i=1; i<50; i++) {
            // test HP and attack scaling
            double exp_ratio = ScalingFuncs.getMarginalHPATKMMult(i);
            double real_ratio= ScalingFuncs.getHPATKMult(i)/ScalingFuncs.getHPATKMult(i-1);
            assertEquals("HP/ATK scaling ratio mismatch", exp_ratio, real_ratio, 1e-9);
        }
    }

    @Test
    public void testSpeedScalingFunc() {
        // Test the speed scaling function with various inputs
        for (int i=1; i<50; i++) {
            // test speed scaling
            double exp_num = Math.max(1.0, 1.0+0.05*(i-5));
            double real_num = ScalingFuncs.getSpeedMult(i);
            assertEquals("Speed scaling ratio mismatch", exp_num, real_num, 1e-9);
        }
    }
}