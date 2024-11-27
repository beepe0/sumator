import Sumator.Sumator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SumatorTest {

    @Test
    public void testSumator() {
        Random rand = new Random();

        for (int i = 0; i < 100; i ++) {
            int num = rand.nextInt(1, Integer.MAX_VALUE / 2 - 1);
            int num2 = rand.nextInt(1, Integer.MAX_VALUE / 2 - 1);
            int res1 = num2 + num;

            System.out.printf("TEST: %s, num1: %d, num2: %d\n", i, num, num2);

            int res2 = Integer.parseInt(Sumator.sum(Integer.toString(num), Integer.toString(num2)));

            Assertions.assertEquals(res1, res2);
        }
    }

    @Test
    public void testSumator2() {
        String a = "9";
        String b = "99";

        System.out.printf("RES: %s%n", Sumator.sum(a, b));
    }

    @Test
    public void testSumator3() throws Exception {
        new Program().run("res/targets");
    }
}
