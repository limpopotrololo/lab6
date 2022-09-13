package Lab5.Utility;

import java.io.*;

public class IOManager {
    private final PrintWriter writer;

    public IOManager(PrintWriter writer) {
        this.writer = writer;
    }

    public void println(Object o) {
        writer.println(o);
    }

    public void printerr(Object o) {
        writer.println("ERROR! " + o);
    }

    public String readFile(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        }
    }
}
