import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SumatorTest {

    @Test
    public void testSumator() {
        Random rand = new Random();

        for (int i = 0; i < 100; i ++) {
            int num = rand.nextInt(1, Integer.MAX_VALUE / 2);
            int num2 = rand.nextInt(1, Integer.MAX_VALUE / 2);
            int res1 = num2 + num;
            int res2 = Integer.parseInt(Sumator.sum(Integer.toString(num), Integer.toString(num2)));

            System.out.printf("TEST: %s, RES1: %s, RES2: %s", i, res1, res2);
            Assertions.assertEquals(res1, res2);
        }
    }

    @Test
    public void testSumator2() {
        String a = "7678687687687687687687687668687687686786876876";
        String b = "7678687687687687687687687668687687686786876876";

        System.out.printf("RES: %s%n", Sumator.sum(a, b));
    }

    @Test
    public void testSumator3() {
        new Sumator().run("D:\\Projects\\Java\\Sumator\\res\\targets");
    }
}
