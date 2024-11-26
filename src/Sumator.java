import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Sumator implements SumatorInterface {

    public static String sum(String a, String b) {
        StringBuilder res = new StringBuilder();

        String first = a.length() <= b.length() ? b : a;
        String second = a.length() > b.length() ? b : a;

        boolean[] overflow = new boolean[first.length()];

        for (int fi = first.length() - 1, si = second.length() - 1; fi >= 0; fi --, si --) {
            int f = Character.digit(first.charAt(fi), 10), s, tempRes;

            f += (overflow[fi] ? 1 : 0);

            if (si >= 0) {
                s = Character.digit(second.charAt(si), 10);

                if (f + s >= 10 && fi > 0) {
                    overflow[fi - 1] = true;
                    tempRes = f + s - 10;
                } else {
                    tempRes = f + s;
                }
            } else {
                if (overflow[fi] && fi > 0 && f >= 10) {
                    overflow[fi - 1] = true;
                    f = 0;
                }

                tempRes = f;
            }

            res.insert(0, tempRes);
        }

        return res.toString();
    }

    @Override
    public void run(String file) {
        Instant instant = Instant.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime nowDate = LocalDateTime.now();

        System.out.printf("Date: %s%n", nowDate.format(dateFormatter));
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
        System.out.printf("Date: %s, Time elapsed: %f seconds %n", nowDate.format(dateFormatter), Duration.between(instant, Instant.now()).toMillis() / 1000.0);
    }
}