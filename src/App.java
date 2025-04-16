import java.io.IOException;
//baliqli sup ye
public class App {
    public static final String OUTPUT_FILE = "out.txt";

    public static void main(String[] args) {
        String inputFile = null;
        if (args.length > 0) {
            inputFile = args[0];
        } else {
            System.out.print("Enter input file name: ");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            inputFile = scanner.nextLine();
        }
        try {
            System.out.println("Trying to open: " + inputFile);
            ExpressionSimplifier simplifier = new ExpressionSimplifier(inputFile, OUTPUT_FILE);
            simplifier.run();
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
    }
}