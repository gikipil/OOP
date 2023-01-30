import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import org.example.Find;
import org.junit.jupiter.api.Test;


/**
 * FindTest.
 */

public class FindTest {

    /**
     * checking at the beginning of the file.
     */
    @Test
    void inTheStartTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        String find = "aaaa";
        InputStream input = getClass().getClassLoader().getResourceAsStream("1.in");
        assertEquals(test.find(input, find), 0);

    }

    /**
     * If not present in the file.
     */

    @Test
    void emptyTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("1.in");
        assertEquals(test.find(input, "qwerty"), -1);
    }

    /**
     * the whole file is a searchable.
     */

    @Test
    void fullTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("2.txt");
        assertEquals(test.find(input, "abcd"), 0);
    }

    /**
     * palindrome test.
     */

    @Test
    void palindromeTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("3.txt");
        assertEquals(test.find(input, "cba"), 4);
    }

    /**
     * looking for nothing.
     */

    @Test
    void nullObjectTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("1.in");
        assertEquals(test.find(input, ""), 0);
    }

    /**
     * looking in an empty file.
     */

    @Test
    void nullInputTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("4.txt");
        assertEquals(test.find(input, "qwerty"), -1);
    }

    /**
     * if we are looking for more than there is in the file.
     */

    @Test
    void littleTextTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("2.txt");
        assertEquals(test.find(input, "qwerty"), -1);
    }

    /**
     * a large text for 100 thousand symbols.
     */

    @Test
    void largeTextTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        String find = "В четверть одиннадцатого, наконец, сели в кареты и поехали.";
        InputStream input = getClass().getClassLoader().getResourceAsStream("5.txt");
        assertEquals(test.find(input, find), 25108);
    }

    /**
     * checking chinese characters.
     */

    @Test
    void chinaTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        String find = "青空";
        InputStream input = getClass().getClassLoader().getResourceAsStream("7.txt");
        assertEquals(test.find(input, find), 1);
    }

    /**
     * a file with almost a million characters.
     */

    @Test
    void veryLargeTest() throws IOException, ClassNotFoundException {
        Find test = new Find();
        InputStream input = getClass().getClassLoader().getResourceAsStream("8.txt");
        String ex = "Я это знаю, и я это докажу, - сказал Ростов.";
        assertEquals(test.find(input, ex), 324873);
    }


}
