import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Lexer {
    private final String input;
    private int pos;

    public Lexer(String input) {
        this.input = input.replaceAll("/\\*.*?\\*/", "");
        this.pos = 0;
    }

    public Token getNextToken() {
        if (pos >= input.length()) {
            return new Token("EOF", "");
        }

        // Define regular expressions for various tokens
        String numberPattern = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?";
        String whitespacePattern = "\\s+";
        String operatorPattern = "add|sub|mul|pow";
        String bracketPattern = "[-+*/%()]";

        Pattern tokenPattern = Pattern.compile(
                numberPattern + "|" + operatorPattern + "|" + whitespacePattern + "|" + bracketPattern
        );

        Matcher matcher = tokenPattern.matcher(input.substring(pos));


        if (matcher.find()) {


            String token = matcher.group().trim();
            String tokenType = "";
            pos += matcher.end();

            // Skip whitespace
            if (token.isEmpty()) {
                return getNextToken();
            }


            else if (token.matches(numberPattern)) {
                tokenType = "NUMBER";
            }

            else if (token.matches(bracketPattern)) {
                tokenType = token;
            }


            if (token.matches(operatorPattern)) {
                switch (token) {
                    case "add" -> token = "+";
                    case "sub" -> token = "-";
                    case "mul" -> token = "*";
                    case "pow" -> token = "^";
                }
            }

            return new Token(tokenType, token);
        }

        return new Token("INVALID", "");
    }

}
