import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionParser {

    public static String transformToMathExpression(String composition) {
        // Remove block comments
        composition = composition.replaceAll("/\\*.*?\\*/", "");

        // Tokenize the composition
        String[] tokens = composition.split("\\s*,\\s*"); // Split by commas and trim spaces

        // Use stacks for operators and output
        Stack<String> operatorStack = new Stack<>();
        Stack<String> outputStack = new Stack<>();

        // Define regular expressions for matching operands and operators
        Pattern operandPattern = Pattern.compile("^([-+]?\\d*\\.?\\d+(?:[eE][-+]?\\d+)?)$");
        Pattern operatorPattern = Pattern.compile("^[+\\-*/%^()]$");

        // Process each token
        for (String token : tokens) {
            Matcher operandMatcher = operandPattern.matcher(token);
            Matcher operatorMatcher = operatorPattern.matcher(token);

            if (operandMatcher.matches()) {
                // Operand, push to the output stack
                outputStack.push(token);
            } else if (operatorMatcher.matches()) {
                // Operator
                processOperators(operatorStack, outputStack, token);
            } else if (token.matches("\\w+\\(.+")) {
                // Function call, process recursively
                processFunctionCall(operatorStack, outputStack, token);
            }
        }

        // Pop remaining operators to the output stack
        while (!operatorStack.isEmpty()) {
            outputStack.push(operatorStack.pop());
        }

        // Build the final expression
        StringBuilder result = new StringBuilder();
        while (!outputStack.isEmpty()) {
            result.insert(0, outputStack.pop() + " ");
        }

        return result.toString().trim();
    }

    private static void processOperators(Stack<String> operatorStack, Stack<String> outputStack, String token) {
        while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), token)) {
            outputStack.push(operatorStack.pop());
        }
        operatorStack.push(token);
    }

    private static void processFunctionCall(Stack<String> operatorStack, Stack<String> outputStack, String token) {
        String functionName = token.substring(0, token.indexOf('('));
        operatorStack.push(functionName);
    }

    private static boolean hasHigherPrecedence(String op1, String op2) {
        int precedenceOp1 = getPrecedence(op1);
        int precedenceOp2 = getPrecedence(op2);

        if (precedenceOp1 == precedenceOp2) {
            // If operators have the same precedence, check associativity
            return isRightAssociative(op1);
        }

        return precedenceOp1 > precedenceOp2;
    }

    private static int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
            case "^":
                return 3;
            default:
                return 0; // Default precedence for non-operators
        }
    }

    private static boolean isRightAssociative(String operator) {
        return "^".equals(operator);
    }

    public static void main(String[] args) {
        String composition = "add(5, mul(3, sub(10, pow(6, 4))))";
        String mathExpression = transformToMathExpression(composition);
        System.out.println(mathExpression);
    }
}
