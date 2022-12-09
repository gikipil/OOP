import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.example.Find;

import java.io.*;



public class FindTest {

    /**
     * checking at the beginning of the file.
     */
    @Test
    void in_the_startTest() throws IOException {
        Find test = new Find();
        String find = "aaaa";
        assertEquals(test.find("1.txt", find), 0);

    }

    /**
     * If not present in the file.
     */

    @Test
    void emptyTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("1.txt", "qwerty"),-1);
    }

    /**
     * the whole file is a searchable.
     */

    @Test
    void fullTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("2.txt", "abcd"), 0);
    }

    /**
     * palindrome test.
     */

    @Test
    void palindromeTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("3.txt", "cba"), 4);
    }

    /**
     * looking for nothing.
     */

    @Test
    void null_objectTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("1.txt", ""), 0);
    }

    /**
     * looking in an empty file.
     */

    @Test
    void null_inputTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("4.txt", "qwerty"), -1);
    }

    /**
     * if we are looking for more than there is in the file.
     */

    @Test
    void little_textTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("2.txt", "qwerty"), -1);
    }

    /**
     * a large text for 100 thousand symbols.
     */

    @Test
    void large_textTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("5.txt", "В четверть одиннадцатого, наконец, сели в кареты и поехали."), 25108);
    }

    /**
     * checking chinese characters.
     */

    @Test
    void chinaTest() throws IOException {
        Find test = new Find();
        String find = "青空";
        assertEquals(test.find("7.txt", find), 1);
    }

    /**
     * a file with almost a million characters.
     */

    @Test
    void very_largeTest() throws IOException {
        Find test = new Find();
        assertEquals(test.find("8.txt", "Я это знаю, и я это докажу, - сказал Ростов."), 324873);
    }


}
