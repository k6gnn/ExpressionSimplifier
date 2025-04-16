import java.io.*;
import java.util.*;

public class ResultWriter {
    private final String fileName;
    private final BufferedWriter writer;

    public ResultWriter(String fileName) throws IOException {
        this.fileName = new String(fileName);
        this.writer = new BufferedWriter(new FileWriter(fileName));
    }

    public void writeLine(String line) throws IOException {
        System.out.println(line);
        writer.write(line);
        writer.newLine();
    }

    public void close() throws IOException {
        writer.close();
    }
}
