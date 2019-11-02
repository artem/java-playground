/*
 * !!!WARNING x WARNING x WARNING!!!
 * This solution kinda works, but is slow and checker throws TL on some tests.
 * Keep on reading further on your own risk.
 */

import java.util.Scanner;

public class TaskE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Element[] scheme = new Element[n];

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            if (m > 0) {
                Element[] inputs = new Element[m];
                boolean[] tbl = new boolean[1 << m];

                for (int j = 0; j < m; j++) {
                    inputs[j] = scheme[sc.nextInt() - 1];
                }

                for (int j = 0; j < 1 << m; j++) {
                    tbl[j] = sc.nextInt() == 1;
                }

                scheme[i] = new Function(inputs, tbl);
            } else {
                scheme[i] = new Variable();
            }
        }

        System.out.println(scheme[scheme.length - 1].getDepth());
        for (int i = 0; i < 1 << Variable.num; i++) {
            System.out.print(scheme[scheme.length - 1].compute(i) ? 1 : 0);
        }
    }

    private static interface Element {
        boolean compute(int args);

        int getDepth();
    }

    private static class Variable implements Element {
        public static int num = 0;
        private final int idx;

        public Variable() {
            this.idx = ++num;
        }

        @Override
        public boolean compute(int args) {
            return (args & 1 << num - idx) != 0;
        }

        @Override
        public int getDepth() {
            return 0;
        }
    }

    private static class Function implements Element {
        private final Element[] inputs;
        private final boolean[] tbl;
        private int depth;

        public Function(Element[] inputs, boolean tbl[]) {
            this.inputs = inputs;
            this.tbl = tbl;

            depth = 0;
            for (Element elem : inputs) {
                depth = Math.max(elem.getDepth(), depth);
            }
            depth++;
        }

        @Override
        public boolean compute(int args) {
            int vals = 0;
            int offset = 0;

            for (int i = inputs.length - 1; i >= 0; i--) {
                vals += (inputs[i].compute(args) ? 1 : 0) * (1 << offset++);
            }
            return tbl[vals];
        }

        @Override
        public int getDepth() {
            return depth;
        }
    }
}
