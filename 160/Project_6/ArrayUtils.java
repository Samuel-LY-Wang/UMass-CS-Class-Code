class ArrayUtils {
    // copy first n elements from source arr to dest arr
    public static void copyArray(int[] source, int[] dest, int n) {
        for (int i = 0; i < n; i++) {
            dest[i] = source[i];
        }
    }

    public static int[] slice(int[] source, int start, int end) {
        // As per tradition, start is inclusive and end is exclusive
        int[] result = new int[end - start];
        for (int i = start; i < end; i++) {
            result[i - start] = source[i];
        }
        return result;
    }
}