import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Fun[] set = new Fun[n];
        boolean preservesZero = true;
        boolean preservesOne = true;
        boolean selfDual = true;
        boolean monotonic = true;
        boolean affine = true;

        for (int i = 0; i < n; i++) {
            set[i] = new Fun(sc.nextInt(), sc.next());

            preservesZero &= set[i].getPreservesZero();
            preservesOne &= set[i].getPreservesOne();
            selfDual &= set[i].getSelfDuality();
            monotonic &= set[i].getMonotone();
            affine &= set[i].getAffinity();
        }

        if (preservesZero || preservesOne || selfDual || monotonic || affine) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }

    private static class Fun {
        private final boolean[] val;

        public Fun(int n, String tbl) {
            this.val = new boolean[1 << n];

            for (int i = 0; i < tbl.length(); i++) {
                val[i] = tbl.charAt(i) == '1';
            }
        }

        public boolean getPreservesZero() {
            return !val[0];
        }

        public boolean getPreservesOne() {
            return val[val.length - 1];
        }

        public boolean getSelfDuality() {
            boolean selfDual = true;

            if (val.length == 1) {
                return false;
            }

            for (int i = 0; i < val.length / 2 && selfDual; i++) {
                selfDual &= val[i] != val[val.length - 1 - i];
            }

            return selfDual;
        }

        public boolean getMonotone(int arg) {
            Set<Integer> anc = new HashSet<>();
            boolean monotonic = true;

            for (int i = 1; i < val.length; i *= 2) {
                anc.add(arg & ~i);
            }
            anc.remove(arg);

            for (int chk : anc) {
                if (val[chk]) {
                    monotonic &= val[arg];
                }
                monotonic &= getMonotone(chk);
            }

            return monotonic;
        }

        public boolean getMonotone() {
            return getMonotone(val.length - 1);
        }

        public boolean getAffinity() {
            boolean[] zhCoeff = new boolean[val.length];
            boolean[] comp = Arrays.copyOf(val, val.length);

            for (int i = 0; i < zhCoeff.length; i++) {
                zhCoeff[i] = comp[0];
                int size = zhCoeff.length - i - 1;
                boolean[] newVal = new boolean[size];
                for (int j = 0; j < size; j++) {
                    newVal[j] = comp[j] ^ comp[j + 1];
                }
                comp = newVal;
            }

            for (int i = 2; i < val.length; i *= 2) {
                for (int j = i + 1; j < i * 2; j++) {
                    if (zhCoeff[j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
