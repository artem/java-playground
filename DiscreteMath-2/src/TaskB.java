import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("bwt.in"));
        String str = sc.next();
        int n = str.length();
        String[] offsets = new String[n];

        int idx = 0;
        do {
            offsets[idx++] = str;
            str = str.substring(1) + str.substring(0, 1);
        } while (idx < n);
        Arrays.sort(offsets);

        PrintWriter out = new PrintWriter("bwt.out");
        for (String cur : offsets) {
            char last = cur.charAt(n - 1);
            out.print(last);
        }
        out.close();
    }
}
