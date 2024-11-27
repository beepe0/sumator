package Sumator;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sumator implements SumatorInterface {
    private int maxThreads;
    private SharedReader sharedReader;
    private Thread[] threads;
    
    public static String sum(String first, String second) {
        StringBuilder result = new StringBuilder();
        result.ensureCapacity(first.length() + 2);

        int fi = first.length() - 1, si = second.length() - 1, r = 0, overflow = 0;

        while (fi >= 0 || si >= 0 || overflow > 0) {
            r = overflow;

            r += fi >= 0 ? first.charAt(fi--) - 48 : 0;
            r += si >= 0 ? second.charAt(si--) - 48 : 0;

            overflow = r / 10;
            result.append(r % 10);
        }

        return result.reverse().toString();
    }

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
