package Sumator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SharedReader {
    private final BufferedReader reader;

    public SharedReader(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    public synchronized String readLine() throws IOException {
        return reader.readLine();
    }
}