import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskC {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("mtf.in"));
        LinkedList<Character> dict = new LinkedList<>();
        String str = sc.next();
        int n = str.length();

        for (char i = 'a'; i <= 'z'; i++) {
            dict.add(i);
        }

        PrintWriter out = new PrintWriter("mtf.out");
        // This is slow af, but I couldn't do any better
        for (int i = 0; i < n; i++) {
            char cur = str.charAt(i);
            int pos = dict.indexOf(cur);

            dict.remove(pos);
            dict.addFirst(cur);

            out.print(pos + 1);
            if (i + 1 != n) {
                out.print(' ');
            }
        }
        out.close();
    }
}
