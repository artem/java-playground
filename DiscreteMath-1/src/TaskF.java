/*
 * Ugly AF, but workz
 */

import java.util.Scanner;

public class TaskF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 1 << sc.nextInt();
        String[] par = new String[n];
        int[] val = new int[n];

        for (int i = 0; i < n; i++) {
            par[i] = sc.next();
            val[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            System.out.println(par[i] +  " " + val[0]);
            int size = n - i - 1;
            int[] newVal = new int[size];
            for (int j = 0; j < size; j++) {
                newVal[j] = val[j] ^ val [j + 1];
            }
            val = newVal;
        }
        System.out.println(par[n - 1] +  " " + val[0]);
    }
}
