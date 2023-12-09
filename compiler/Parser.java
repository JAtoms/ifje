import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Parser {
    private final Lexer lexer;
    private Token currentToken;
    private final Stack<String> output;
    private final Stack<String> operators;
    boolean isParen = false;

    public Parser(String input) {
        lexer = new Lexer(input);
        currentToken = lexer.getNextToken();
        output = new Stack<>();
        operators = new Stack<>();
    }


    public String parse() {

        /**
         * The parser will use the lexer to get tokens
         * and will use recursive descent to parse the input
         *
         * EOF = end of file is also the same as when $ is read
         */

        while (!currentToken.getType().equals("EOF")) {
            if (!currentToken.getType().equals("PREN")) {
                if (currentToken.getType().equals("OPERATOR")) {
                    operators.push(currentToken.getValue());

                } else {
                    output.push(currentToken.getValue());
                    if (isParen) {
                        output.push(")");
                        isParen = false;
                    }
                    if (currentToken.getType().equals("NUMBER") && !operators.isEmpty()) {
                        if (operators.peek().equals("^")) {
                            String temp = output.pop();
                            output.push("(");
                            output.push(temp);
                            isParen = true;
                        }
                        output.push(" " + operators.pop() + " ");
                    }
                }
            }
            currentToken = lexer.getNextToken();
        }
        convertExpression(output);
//        System.out.println(String.join("", output));
        return String.join("", output);
    }


    public static void convertExpression(Stack<String> input) {
        List<String> result = new ArrayList<>();
        System.out.println(String.join("", input));
    }

    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}