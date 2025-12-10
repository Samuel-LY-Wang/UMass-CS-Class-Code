public class MaxFinder {
    public static double findMaximum(double[] arrayOfDoubles){
        // we assume the array has at least one element.
        double max;
        int nextPositionToLookAt = 0;
        max = arrayOfDoubles[nextPositionToLookAt];
        while (nextPositionToLookAt< arrayOfDoubles.length){
            if (arrayOfDoubles[nextPositionToLookAt] > max) {
                max = arrayOfDoubles[nextPositionToLookAt];
            }
            nextPositionToLookAt = nextPositionToLookAt + 1;
        }
        return(max);
    }
    public static void main(String[] s) {
        double[] anArray = {100, 0,9,22, -4, 6};
        System.out.println(findMaximum(anArray));
        double[] anotherArray = {17};
        System.out.println(findMaximum(anotherArray));
    }
}
