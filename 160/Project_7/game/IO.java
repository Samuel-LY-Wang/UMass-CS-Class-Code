package game;
import java.util.Scanner;

public final class IO {
    private IO() {throw new AssertionError("Class cannot be instantiated, instances prohibited.");} // Prevent instantiation
    public static final Scanner s = new Scanner(System.in);
    public static final String getInput() {
        return s.nextLine();
    }
    public static final void output(String output) {
        System.out.println(output);
    }
}
