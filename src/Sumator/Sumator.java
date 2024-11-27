package Sumator;

public class Sumator {
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
}
