import static org.example.PrimeNum.isPrime;
import static org.example.PrimeNum.psFind;
import static org.example.PrimeNum.seqFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.example.PrimeNum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * PrimeNum test class.
 */

public class PrimeNumTest {

    /**
     * isPrime test.
     */

    @Test
    void isPrimeTest() {
        Assertions.assertTrue(isPrime(744391));
    }

    /**
     * seqFind test.
     */

    @Test
    void seqFindtest() throws IOException {
        Scanner input = new Scanner(new File("src/test/resources/1.txt"));
        Assertions.assertTrue(seqFind(input));
        Scanner input2 = new Scanner(new File("src/test/resources/2.txt"));
        Assertions.assertFalse(seqFind(input2));
    }

    /**
     * psFind test.
     */

    @Test
    void psFindTest() throws IOException {
        Scanner input = new Scanner(new File("src/test/resources/1.txt"));
        Assertions.assertTrue(psFind(input));
        Scanner input2 = new Scanner(new File("src/test/resources/2.txt"));
        Assertions.assertFalse(psFind(input2));
    }

    /**
     * thrFind test.
     */


    @Test
    void thrFindTest() throws IOException, InterruptedException {
        PrimeNum obj1 = new PrimeNum();
        Scanner input = new Scanner(new File("src/test/resources/1.txt"));
        Assertions.assertTrue(obj1.thrFind(input, 4));
        Scanner input2 = new Scanner(new File("src/test/resources/2.txt"));
        PrimeNum obj2 = new PrimeNum();
        Assertions.assertFalse(obj2.thrFind(input2, 8));
    }

    /**
     * large seq test.
     */

    @Test
    void largeSeqTest() throws IOException {
        Scanner input2 = new Scanner(new File("src/test/resources/12.txt"));
        long start = System.currentTimeMillis();
        Assertions.assertFalse(seqFind(input2));
        long finish = System.currentTimeMillis();

    }

    /**
     * large ps test.
     */

    @Test
    void largePsTest() throws IOException {
        Scanner input2 = new Scanner(new File("src/test/resources/12.txt"));
        long start = System.currentTimeMillis();
        Assertions.assertFalse(psFind(input2));
        long finish = System.currentTimeMillis();
    }

    /**
     * large thr test.
     */

    @Test
    void largeThrTest() throws IOException, InterruptedException {
        PrimeNum obj = new PrimeNum();

        Scanner input2 = new Scanner(new File("src/test/resources/12.txt"));
        long start = System.currentTimeMillis();
        Assertions.assertFalse(obj.thrFind(input2, 4));
        long finish = System.currentTimeMillis();

        Scanner input1 = new Scanner(new File("src/test/resources/12.txt"));
        start = System.currentTimeMillis();
        Assertions.assertFalse(obj.thrFind(input1, 8));
        finish = System.currentTimeMillis();

    }

}