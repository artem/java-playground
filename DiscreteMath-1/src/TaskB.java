/*
 * This code has funny captions in Russian, keepin' them on purpose :)))
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskB {
    private static List<int[]> tbl;

    private static void matchAll(int pos, int val) {
        for (int i = 0; i < tbl.size(); i++) {
            int[] tmp = tbl.get(i);

            if (tmp == null) {
                continue;
            }

            if (tmp[pos] == val) {
                // если переменная обращает клоз в 1, то мы можем его выкинуть
                tbl.set(i, null);
            } else {
                // иначе, крутим рулетку
                tmp[pos] = -1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        tbl = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            int[] tmp = new int[n];
            for (int j = 0; j < n; j++) {
                tmp[j] = sc.nextInt();
            }
            tbl.add(tmp);
        }

        while (true) {
            int complex = 0;

            nextRow:
            for (int i = 0; i < k; i++) {
                int[] tmp = tbl.get(i);
                int cnt = 0;
                int pos = -1;

                // ищем клозы, где осталась <= 1 переменная
                if (tmp == null) {
                    complex++;
                    continue nextRow;
                }

                for (int j = 0; j < n; j++) {
                    if (tmp[j] != -1) {
                        if (cnt++ == 0) {
                            pos = j;
                        } else {
                            complex++;
                            continue nextRow;
                        }
                    }
                }

                if (cnt == 1) {
                    // ура, в клозе 1 переменная. Давайте выберем ей значение
                    matchAll(pos, tmp[pos]);
                } else {
                    // я считаю, что если в клозе все -1, то вся ф-я -- const zero
                    System.out.println("YES");
                    return;
                }
            }

            if (complex == tbl.size()) {
                // если пробежались по таблице и ни...... чего не сделали
                // то функция не const zero!
                System.out.println("NO");
                return;
            }
        }
    }
}
