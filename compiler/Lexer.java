import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Lexer {
    private String input;
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
        String operatorPattern = "[\\+\\-*/%^()]";
        String whitespacePattern = "\\s+";




        Pattern tokenPattern = Pattern.compile(
            numberPattern + "|" + operatorPattern + "|" + whitespacePattern
        );


        Matcher matcher = tokenPattern.matcher(input.substring(pos));


        if (matcher.find()) {
            String token = matcher.group().trim();
            System.out.println("token: " + token);
            pos += matcher.end();

            // Skip whitespace
            if (token.matches(whitespacePattern)) {
                return getNextToken();
            }

            return new Token(token, token);
        }

        return new Token("INVALID", "");
    }
}
