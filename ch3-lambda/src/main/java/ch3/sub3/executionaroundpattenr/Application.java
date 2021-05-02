package ch3.sub3.executionaroundpattenr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Application {

    private static final String FILE = Application.class.getResource("./data.txt").getFile();

    public static void main(String[] args) throws IOException {

        String oneLine = processFile((BufferedReader br) -> br.readLine());
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        String path = Application.class.getResource("").getPath();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return p.process(br);
        }
    }
}
