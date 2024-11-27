import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sumator implements SumatorInterface {

    public static String sum(String first, String second) {
        StringBuilder result = new StringBuilder();

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

        System.out.printf("Time: %s%n", nowDate.format(dateFormatter));

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            boolean test = Sumator.sum(data[0], data[1]).equals(data[2]);
        }

        nowDate = LocalDateTime.now();
        System.out.printf("Time: %s, Time elapsed: %f seconds %n", nowDate.format(dateFormatter), Duration.between(instant, Instant.now()).toMillis() / 1000.0);
    }
}