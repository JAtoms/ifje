public class Main {
    public static void main(String[] args) {

        // Entry point for the program

        String input = "add(5, mul(3, sub(10, pow(6, 4))))";

        // String input = "add(pow(10, 23), mod(120, add(10, 31)))";

        Parser parser = new Parser(input);
        System.out.println("The expression " + input + " is equivalent to " + parser.parse());

    }
}
