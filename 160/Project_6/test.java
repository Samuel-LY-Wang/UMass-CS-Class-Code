import java.util.Arrays;

public static void main(String[] args) {
    // testing how Array built in toString works
    int[] arr = {1, 2, 3, 4, 5};
    String s = Arrays.toString(arr);
    StringBuilder sb = new StringBuilder(s);
    sb.setCharAt(0, '{');
    sb.setCharAt(sb.length() - 1, '}');
    System.out.println(sb.toString());
}