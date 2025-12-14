package game;
import java.util.Scanner;

public final class InputHandler {
    private InputHandler() {throw new AssertionError("Class cannot be instantiated, instances prohibited.");} // Prevent instantiation
    public static final Scanner s = new Scanner(System.in);
    public static final String getInputLine() {
        return s.nextLine();
    }
}
