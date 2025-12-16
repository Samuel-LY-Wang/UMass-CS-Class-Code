package game;

public final class InputHandler {
    private InputHandler() { throw new AssertionError("Class cannot be instantiated, instances prohibited.");}
    public static final String getInput(String prompt, String[] validChoices, String failureMessage) {
        while (true) {
            IO.output(prompt);
            String input = IO.getInput();
            for (String choice : validChoices) {
                if (input.equalsIgnoreCase(choice)) {
                    return input;
                }
            }
            IO.output(failureMessage);
        }
    }
    public static final int getIntInput(String prompt, int min, int max, String failureMessage) {
        while (true) {
            IO.output(prompt);
            String input = IO.getInput();
            try {
                int intInput = Integer.parseInt(input);
                if (intInput >= min && intInput <= max) {
                    return intInput;
                } else {
                    IO.output(failureMessage);
                }
            } catch (NumberFormatException e) {
                IO.output(failureMessage);
            }
        }
    }
    public static final double getDoubleInput(String prompt, double min, double max, String failureMessage) {
        while (true) {
            IO.output(prompt);
            String input = IO.getInput();
            try {
                double doubleInput = Double.parseDouble(input);
                if (doubleInput >= min && doubleInput <= max) {
                    return doubleInput;
                } else {
                    IO.output(failureMessage);
                }
            } catch (NumberFormatException e) {
                IO.output(failureMessage);
            }
        }
    }
}
