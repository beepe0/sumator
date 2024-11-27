package Sumator;

import java.io.IOException;

public class ReaderFileTask extends Thread {
    private final SharedReader sharedReader;

    public ReaderFileTask(SharedReader sharedReader) {
        this.sharedReader = sharedReader;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = sharedReader.readLine()) != null) {

                String[] data = line.split(";");
                boolean test = Sumator.sum(data[0], data[1]).equals(data[2]);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}