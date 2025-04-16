import java.util.*;

public class ExpressionEvaluator {
    private ArrayList<Token> tokens;
    private int pos;

    public int evaluate(ArrayList<Token> tokens) throws EvaluationException {
        this.tokens = tokens;
        this.pos = 0;
        return parseExpression();
    }

    private int parseExpression() throws EvaluationException {
        int result = parseTerm();
        while (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (token.type == TokenType.PLUS) {
                pos++;
                result += parseTerm();
            } else if (token.type == TokenType.MINUS) {
                pos++;
                result -= parseTerm();
            } else {
                break;
            }
        }
        return result;
    }

    private int parseTerm() throws EvaluationException {
        int result = parseFactor();
        while (pos < tokens.size()) {
            Token token = tokens.get(pos);
            if (token.type == TokenType.MULTIPLY) {
                pos++;
                result *= parseFactor();
            } else if (token.type == TokenType.DIVIDE) {
                pos++;
                int divisor = parseFactor();
                if (divisor == 0) {
                    throw new EvaluationException("Division by zero");
                }
                result /= divisor;
            } else {
                break;
            }
        }
        return result;
    }

    private int parseFactor() throws EvaluationException {
        Token token = tokens.get(pos);
        if (token.type == TokenType.MINUS) {
            pos++;
            return -parseFactor();
        } else if (token.type == TokenType.NUMBER) {
            pos++;
            return Integer.parseInt(token.value);
        } else if (token.type == TokenType.LPAREN) {
            pos++;
            int result = parseExpression();
            if (pos >= tokens.size() || tokens.get(pos).type != TokenType.RPAREN) {
                throw new EvaluationException("Missing closing parenthesis");
            }
            pos++;
            return result;
        } else {
            throw new EvaluationException("Unexpected token: " + token.value);
        }
    }
}