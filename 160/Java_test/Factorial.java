public static void main(String[] args) {
    int num = 5;
    long result = 1;
    // While loop fact
    int i = 1;
    while (i <= num) {
        result *= i;
        i++;
    }
    System.out.println("Factorial of " + num + " is: " + result);
    // For loop fact
    result = 1;
    for (int j = 1; j <= num; j++) {
        result *= j;
    }
    System.out.println("Factorial of " + num + " is: " + result);
}