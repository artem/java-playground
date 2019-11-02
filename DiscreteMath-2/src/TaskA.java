import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TaskA {
    public static void main(String[] args) throws FileNotFoundException {
        PriorityQueue<Long> freq = new PriorityQueue<>();
        Scanner sc = new Scanner(new File("huffman.in"));

        int n = sc.nextInt();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long tmp = sc.nextLong();
            freq.add(tmp);
        }

        while (freq.size() > 1) {
            long f = freq.poll();
            long s = freq.poll();
            ans += f + s;
            freq.add(f + s);
        }
        PrintWriter out = new PrintWriter("huffman.out");
        out.print(ans);
        out.close();
    }
}