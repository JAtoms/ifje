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
            if (currentToken.getType().equals("NUMBER")) {
                output.push(new Expression(currentToken.getValue()));
            } else if (currentToken.getType().equals("(")) {
                operators.push(currentToken);
            } else if (currentToken.getType().equals(")")) {
                while (!operators.isEmpty() && !operators.peek().getType().equals("(")) {
                    output.push(new Expression(operators.pop().getValue()));
                }
                if (!operators.isEmpty() && operators.peek().getType().equals("(")) {
                    operators.pop();
                }
            } else {
                while (!operators.isEmpty() && getPrecedence(currentToken) <= getPrecedence(operators.peek())) {
                    output.push(new Expression(operators.pop().getValue()));
                }
                operators.push(currentToken);
            }
            currentToken = lexer.getNextToken();
        }

        while (!operators.isEmpty()) {
            output.push(new Expression(operators.pop().getValue()));
        }

        return output.pop();
    }

    private int getPrecedence(Token token) {
        switch (token.getValue()) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            case "^":
                return 3;
        }
        return 0;
    }
}
