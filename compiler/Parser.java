import java.util.Stack;

class Parser {
    private final Lexer lexer;
    private Token currentToken;
    private final Stack<String> output;
    private final Stack<String> operators;

    public Parser(String input) {
        lexer = new Lexer(input);
        currentToken = lexer.getNextToken();
        output = new Stack<>();
        operators = new Stack<>();
    }

    public String parse() {

        while (!currentToken.getType().equals("EOF")) {


            if (currentToken.getType().equals("OPERATOR")) {
                operators.push(currentToken.getValue());
            } else {
                output.push(currentToken.getValue());

                if (currentToken.getType().equals("NUMBER") && !operators.isEmpty()) {

                    output.push(operators.pop());

                }
            }

            currentToken = lexer.getNextToken();
        }

//        convertExpression(this.output);
        return String.join(" ", this.output);

    }


    public static void convertExpression(Stack<String> input) {
        Stack<String> bracket = input;
        Stack<String> result = new Stack<>();

        while (!bracket.isEmpty() && precedence(bracket.peek()) <= precedence(bracket.peek())) {
            result.push(bracket.pop());
        }

        System.out.println(result);
    }

    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}