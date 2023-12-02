public class Main {
    public static void main(String[] args) {
        String input = "add(5, mul(3, sub(10, pow(6, 4))))";

        Parser parser = new Parser(input);

        System.out.println("The input expression " +input + " is equivalent to " + parser.parse());

    }
}
