import java.io.*;
import java.util.*;

public class ExpressionSimplifier {
    private final String inputFileName;
    private final String outputFileName;

    public ExpressionSimplifier(String inputFile, String outputFile) {
        this.inputFileName = new String(inputFile);
        this.outputFileName = new String(outputFile);
    }

    public void run() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        ResultWriter writer = new ResultWriter(outputFileName);
        ExpressionParser parser = new ExpressionParser();
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                writer.writeLine("");
                continue;
            }
            try {
                ArrayList<Token> tokens = parser.parse(line);
                int result = evaluator.evaluate(tokens);
                writer.writeLine(String.valueOf(result));
            } catch (EvaluationException e) {
                writer.writeLine("Invalid expression: " + e.getMessage());
            }
        }
        reader.close();
        writer.close();
    }
}