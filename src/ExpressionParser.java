import java.util.*;

public class ExpressionParser {
    public ArrayList<Token> parse(String expression) throws EvaluationException {
        ArrayList<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
            } else if (c == '+') {
                tokens.add(new Token(TokenType.PLUS, "+")); i++;
            } else if (c == '-') {
                tokens.add(new Token(TokenType.MINUS, "-")); i++;
            } else if (c == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*")); i++;
            } else if (c == '/') {
                tokens.add(new Token(TokenType.DIVIDE, "/")); i++;
            } else if (c == '(') {
                tokens.add(new Token(TokenType.LPAREN, "(")); i++;
            } else if (c == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")")); i++;
            } else {
                throw new EvaluationException("Invalid character: " + c);
            }
        }
        return tokens;
    }
}