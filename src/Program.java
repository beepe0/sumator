import Sumator.ReaderFileTask;
import Sumator.SharedReader;
import Sumator.SumatorInterface;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Program implements SumatorInterface {
    private int maxThreads;
    private SharedReader sharedReader;
    private Thread[] threads;

    @Override
    public void run(String file) throws IOException {
        Instant instant = Instant.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime nowDate = LocalDateTime.now();

        maxThreads = Runtime.getRuntime().availableProcessors();
        sharedReader = new SharedReader(file);
        threads = new Thread[maxThreads];

        int maxThreads = Runtime.getRuntime().availableProcessors();
        System.out.printf("Time: %s, availableProcessors(): %s%n", nowDate.format(dateFormatter), maxThreads);

        for (int i = 0; i < maxThreads; i ++) {
            Thread thread = new ReaderFileTask(sharedReader);
            thread.start();
            threads[0] = thread;
        }

        try {
            for (int i = 0; i < maxThreads; i ++) {
                threads[0].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        nowDate = LocalDateTime.now();
        System.out.printf("Time: %s, Time elapsed: %f seconds %n", nowDate.format(dateFormatter), Duration.between(instant, Instant.now()).toMillis() / 1000.0);
    }
}