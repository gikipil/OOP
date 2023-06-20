import static org.example.PrimeNum.isPrime;
import static org.example.PrimeNum.psFind;
import static org.example.PrimeNum.seqFind;

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
    void seqFindtest() {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("1.txt")) {
            assert file != null;
            Scanner input = new Scanner(file);
            Assertions.assertTrue(seqFind(input));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (InputStream file2 = getClass().getClassLoader().getResourceAsStream("2.txt")) {
            assert file2 != null;
            Scanner input2 = new Scanner(file2);
            Assertions.assertFalse(seqFind(input2));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * psFind test.
     */

    @Test
    void psFindTest() {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("1.txt")) {
            assert file != null;
            Scanner input = new Scanner(file);
            Assertions.assertTrue(psFind(input));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (InputStream file2 = getClass().getClassLoader().getResourceAsStream("2.txt")) {
            assert file2 != null;
            Scanner input2 = new Scanner(file2);
            Assertions.assertFalse(psFind(input2));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * thrFind test.
     */

    @Test
    void thrFindTest() throws InterruptedException {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("1.txt")) {
            PrimeNum obj1 = new PrimeNum();
            assert file != null;
            Scanner input = new Scanner(file);
            Assertions.assertTrue(obj1.thrFind(input, 4));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (InputStream file2 = getClass().getClassLoader().getResourceAsStream("2.txt")) {
            assert file2 != null;
            Scanner input2 = new Scanner(file2);
            PrimeNum obj2 = new PrimeNum();
            Assertions.assertFalse(obj2.thrFind(input2, 8));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * large seq test.
     */

    @Test
    void largeSeqTest() {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("12.txt")) {
            assert file != null;
            Scanner input = new Scanner(file);
            long start = System.currentTimeMillis();
            Assertions.assertFalse(seqFind(input));
            long finish = System.currentTimeMillis();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    /**
     * large ps test.
     */

    @Test
    void largePsTest() {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("12.txt")) {
            assert file != null;
            Scanner input = new Scanner(file);
            long start = System.currentTimeMillis();
            Assertions.assertFalse(psFind(input));
            long finish = System.currentTimeMillis();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * large thr test.
     */

    @Test
    void largeThrTest() throws InterruptedException {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("101.txt")) {
            PrimeNum obj = new PrimeNum();
            assert file != null;
            Scanner input = new Scanner(file);
            long start = System.currentTimeMillis();
            Assertions.assertFalse(obj.thrFind(input, 16));
            long finish = System.currentTimeMillis();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try (InputStream file2 = getClass().getClassLoader().getResourceAsStream("102.txt"))    {
            PrimeNum obj = new PrimeNum();
            assert file2 != null;
            Scanner input2 = new Scanner(file2);
            long start1 = System.currentTimeMillis();
            Assertions.assertFalse(obj.thrFind(input2, 16));
            long finish1 = System.currentTimeMillis();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


}