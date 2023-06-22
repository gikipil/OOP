import static org.example.PrimeNum.isPrime;
import static org.example.PrimeNum.psFind;
import static org.example.PrimeNum.seqFind;

import java.util.List;
import java.util.Stack;
import org.example.PrimeNum;
import org.example.Read;
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
    void seqFindtest() {
        Assertions.assertTrue(seqFind(Read.read("1.txt")));
        Assertions.assertFalse(seqFind(Read.read("2.txt")));
    }

    /**
     * psFind test.
     */

    @Test
    void psFindTest() {
        Assertions.assertTrue(psFind(Read.read("1.txt")));
        Assertions.assertFalse(psFind(Read.read("2.txt")));
    }

    /**
     * thrFind test.
     */

    @Test
    void thrFindTest() throws InterruptedException {
        PrimeNum obj1 = new PrimeNum();
        Assertions.assertTrue(obj1.thrFind(Read.read("1.txt", 4), 4));
        PrimeNum obj2 = new PrimeNum();
        Assertions.assertFalse(obj2.thrFind(Read.read("2.txt", 8), 8));
    }

    /**
     * large seq test.
     */

    @Test
    void largeSeqTest() {
        List<Integer> list = Read.read("101.txt");
        long start = System.currentTimeMillis();
        Assertions.assertFalse(seqFind(list));
        long finish = System.currentTimeMillis();
    }

    /**
     * large ps test.
     */

    @Test
    void largePsTest() {
        List<Integer> list = Read.read("101.txt");
        long start = System.currentTimeMillis();
        Assertions.assertFalse(psFind(list));
        long finish = System.currentTimeMillis();
    }

    /**
     * large thr test.
     */

    @Test
    void largeThrTest() throws InterruptedException {
        List<Stack<Integer>> define2 = Read.read("102.txt", 50);
        PrimeNum obj = new PrimeNum();
        long start = System.currentTimeMillis();
        Assertions.assertFalse(obj.thrFind(define2, 50));
        long finish = System.currentTimeMillis();
    }


}