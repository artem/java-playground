import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskD {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("lzw.in"));
        Map<String, Integer> dict = new HashMap<>();
        String str = sc.next();
        int n = str.length();
        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char) ('a' + i)), i);
        }

        PrintWriter out = new PrintWriter("lzw.out");
        for (int i = 0; i < n; i++) {
            String curChar = String.valueOf(str.charAt(i));
            if (!dict.containsKey(buf.toString() + curChar)) {
                dict.put(buf.toString() + curChar, dict.size());
                out.print(dict.get(buf.toString()) + " ");
                buf = new StringBuilder();
            }
            buf.append(curChar);
        }
        out.print(dict.get(buf.toString()));
        out.close();
    }
}
