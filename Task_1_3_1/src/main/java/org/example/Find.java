package org.example;


import java.io.*;
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
     * function to convert a file.
     * in the desired encoding to a string.
     *
     * @param name name of file.
     *
     * @return String search text.
     */

    public String open_file(String name) throws IOException {
        String file = "src/test/resources/" + name;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        String line = reader.readLine();
        StringBuilder text = new StringBuilder();
        while(line != null) {
            text.append(line);
            text.append(" ");
            line = reader.readLine();
        }
        String ans = text.toString();
        if (ans.length() > 0) {
            return ans.substring(0, ans.length() - 1);
        }
        return ans;
    }

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
     * @param text The source text for the search.
     *
     * @param example What you need to find.
     *
     * @return returns the index of the beginning of the substring.
     */
     private int find_math(String text, String example) {
        int math = 0;
        for (int i = 0; i < text.length(); i++) {

            if ((math < example.length()) && (text.charAt(i) == example.charAt(math))) {
                if (++math == example.length()) {
                    return i - math + 1;
                }
            } else if (math > 0) {
                math = pi[math];
                i--;
            }
        }
        return -1;
    }

    /**
     * Find function.
     *
     * @param name name input file.
     *
     * @param example What you need to find.
     *
     * @return Start Index.
     */
    public int find(String name, String example) throws IOException {
        String text = open_file(name);
        try(FileWriter writer = new FileWriter("src/test/resources/find.txt", false)) {
            writer.write(example);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        String ex = open_file("find.txt");


        if (Objects.equals(ex, "")) {
            return 0;
        }
        if (Objects.equals(text, "")) {
            return -1;
        }
        if (text.length() < ex.length()) {
            return -1;
        }
        prefix(ex);
        return find_math(text, ex);
    }


}
