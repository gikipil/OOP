package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * Substring search class.
 * The prefix algorithm is used.
 * Knut-Maurice-Prat.
 */
public class Find {
    /**
     * Array of values matching.
     * Suffix and prefix in the searchable.
     */
    private int[] pi;


    /**
     * The prefix function defines an array of numbers.
     * They determine the longest length of the sample.
     * Suffix matching with the prefix.
     *
     * @param example What you need to find.
     */
    private void prefix(String example) {
        pi = new int [example.length() + 1];
        for (int i = 1; i < example.length(); i++) {
            int temp = pi[i + 1];

            while ((temp > 0) && (example.charAt(i) != example.charAt(temp))) {
                temp = pi[temp];
            }
            if ((temp > 0) || (example.charAt(i) == example.charAt(temp))) {
                pi[i + 1] = temp + 1;
            }
        }
    }

    /**
     * The function compares and searches for matches in the source text.
     *
     *  @param input name input file.
     *
     * @param example What you need to find.
     *
     * @return returns the index of the beginning of the substring.
     */

    private int find_math(InputStream input, String example) throws IOException {
        int math = 0;
        int i = 0;
        if (input == null || example == null) {
            return -1;
        }

        Reader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8));
        int ch = reader.read();

        while (ch != -1) {

            if (((math < example.length()) && ((char) ch == example.charAt(math)))) {
                if (++math == example.length()) {
                    return i - math + 1;
                }
            } else if (math > 0) {
                math = pi[math];
            }
            if (ch == 10) {
                i--;
            }
            i++;
            ch = reader.read();
        }
        return -1;
    }

    /**
     * Find function.
     *
     * @param input name input file.
     *
     * @param example What you need to find.
     *
     * @return Start Index.
     */
    public int find(InputStream input, String example) throws IOException, ClassNotFoundException {

        byte[] bytes = example.getBytes(StandardCharsets.UTF_8);
        String ex = new String(bytes, StandardCharsets.UTF_8);

        if (Objects.equals(ex, "")) {
            return 0;
        }
        prefix(ex);
        return find_math(input, ex);
    }


}
