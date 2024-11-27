import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Sumator implements SumatorInterface {

    public static String sum(String first, String second) {
        StringBuilder result = new StringBuilder();

        if (first.length() < second.length()) {
            String t = first;
            first = second;
            second = t;
        }

        int fi = first.length() - 1, si = second.length() - 1, overflow = 0, r = 0;

        while (fi >= 0 || overflow > 0) {
            r = overflow;

            if (fi >= 0) {
                r += first.charAt(fi) - 48;
            }
            if (si >= 0) {
                r += second.charAt(si) - 48;
            }

            overflow = r / 10;
            result.append(r % 10);
            fi--; si--;
        }

        return result.reverse().toString();
    }

    @Override
    public void run(String file) {
        Instant instant = Instant.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime nowDate = LocalDateTime.now();

        System.out.printf("Time: %s%n", nowDate.format(dateFormatter));
        try {
            File inputFile = new File(file);
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] data = line.split(";");
                boolean test = Sumator.sum(data[0], data[1]).equals(data[2]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        nowDate = LocalDateTime.now();
        System.out.printf("Time: %s, Time elapsed: %f seconds %n", nowDate.format(dateFormatter), Duration.between(instant, Instant.now()).toMillis() / 1000.0);
    }
}