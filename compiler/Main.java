public class Main {
    public static void main(String[] args) {
        String input = "add(5, mul(3, sub(10, pow(6, 4))))";

        Parser parser = new Parser(input);
        Expression result = parser.parse();

        System.out.println(result.getContent());
    }
}
