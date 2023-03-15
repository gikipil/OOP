import static org.example.PrimeNum.isPrime;
import static org.example.PrimeNum.psFind;
import static org.example.PrimeNum.seqFind;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        InputStream file = getClass().getClassLoader().getResourceAsStream("1.txt");
        assert file != null;
        Scanner input = new Scanner(file);
        Assertions.assertTrue(seqFind(input));
        InputStream file2 = getClass().getClassLoader().getResourceAsStream("2.txt");
        assert file2 != null;
        Scanner input2 = new Scanner(file2);
        Assertions.assertFalse(seqFind(input2));
    }

    /**
     * psFind test.
     */

    @Test
    void psFindTest() throws IOException {
        InputStream file = getClass().getClassLoader().getResourceAsStream("1.txt");
        assert file != null;
        Scanner input = new Scanner(file);
        Assertions.assertTrue(psFind(input));
        InputStream file2 = getClass().getClassLoader().getResourceAsStream("2.txt");
        assert file2 != null;
        Scanner input2 = new Scanner(file2);
        Assertions.assertFalse(psFind(input2));
    }

    /**
     * thrFind test.
     */

    @Test
    void thrFindTest() throws IOException, InterruptedException {
        PrimeNum obj1 = new PrimeNum();
        InputStream file = getClass().getClassLoader().getResourceAsStream("1.txt");
        assert file != null;
        Scanner input = new Scanner(file);
        Assertions.assertTrue(obj1.thrFind(input, 4));
        InputStream file2 = getClass().getClassLoader().getResourceAsStream("2.txt");
        assert file2 != null;
        Scanner input2 = new Scanner(file2);
        PrimeNum obj2 = new PrimeNum();
        Assertions.assertFalse(obj2.thrFind(input2, 8));
    }

    /**
     * large seq test.
     */

    @Test
    void largeSeqTest() throws IOException {
        InputStream file = getClass().getClassLoader().getResourceAsStream("12.txt");
        assert file != null;
        Scanner input = new Scanner(file);
        long start = System.currentTimeMillis();
        Assertions.assertFalse(seqFind(input));
        long finish = System.currentTimeMillis();

    }

    /**
     * large ps test.
     */

    @Test
    void largePsTest() throws IOException {
        InputStream file = getClass().getClassLoader().getResourceAsStream("12.txt");
        assert file != null;
        Scanner input = new Scanner(file);
        long start = System.currentTimeMillis();
        Assertions.assertFalse(psFind(input));
        long finish = System.currentTimeMillis();
    }

    /**
     * large thr test.
     */

    @Test
    void largeThrTest() throws IOException, InterruptedException {
        PrimeNum obj = new PrimeNum();

        InputStream file = getClass().getClassLoader().getResourceAsStream("12.txt");
        assert file != null;
        Scanner input = new Scanner(file);
        long start = System.currentTimeMillis();
        Assertions.assertFalse(obj.thrFind(input, 4));
        long finish = System.currentTimeMillis();

        InputStream file2 = getClass().getClassLoader().getResourceAsStream("12.txt");
        assert file2 != null;
        Scanner input2 = new Scanner(file2);
        start = System.currentTimeMillis();
        Assertions.assertFalse(obj.thrFind(input2, 8));
        finish = System.currentTimeMillis();

    }

}