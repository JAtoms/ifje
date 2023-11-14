import java.util.Stack;

class Parser {
    private Lexer lexer;
    private Token currentToken;
    private Stack<Expression> output;
    private Stack<Token> operators;

    public Parser(String input) {
        lexer = new Lexer(input);
        currentToken = lexer.getNextToken();
        output = new Stack<>();
        operators = new Stack<>();
    }

    public Expression parse() {
        while (!currentToken.getType().equals("EOF")) {

            switch (currentToken.getType()) {

                case "NUMBER" -> output.push(new Expression(currentToken.getValue()));
                case "(" -> operators.push(currentToken);
                case ")" -> {
                    while (!operators.isEmpty() && !operators.peek().getType().equals("(")) {
                        output.push(new Expression(operators.pop().getValue()));
                    }
                    if (!operators.isEmpty() && operators.peek().getType().equals("(")) {
                        operators.pop();
                    }
                }
                default -> {
                    while (!operators.isEmpty() && getPrecedence(currentToken) <= getPrecedence(operators.peek())) {
                        output.push(new Expression(operators.pop().getValue()));
                    }
                    operators.push(currentToken);
                }
            }
            currentToken = lexer.getNextToken();
        }

        while (!operators.isEmpty()) {
            output.push(new Expression(operators.pop().getValue()));
        }

    for(Token e : operators){
            System.out.println(e);
        }

        return output.pop();
    }

    private int getPrecedence(Token token) {
        return switch (token.getValue()) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }
}
