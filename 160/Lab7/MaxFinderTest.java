import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MaxFinderTest {
    @Test
    public void testFindMaximum() {
        double[] arr = {100, 0, 9, 22, -4, 6, 999};
        double expectedMax = 999;
        assertEquals(expectedMax, MaxFinder.findMaximum(arr), 0.01);
    }

    @Test
    public void testFindMaxSingleEl() {
        double[] arr = {17};
        double expectedMax = 17;
        assertEquals(expectedMax, MaxFinder.findMaximum(arr), 0.01);
    }

    @Test
    public void testFindMaxFromStart() {
        double[] arr = {5, 1, 2, 3, 4};
        double expectedMax = 5;
        assertEquals(expectedMax, MaxFinder.findMaximum(arr), 0.01);
    }
}