/*
 * Could avoid hackery with maintaining correct array indexes and use Set
 * But this revision has passed test and I could not be bothered enough to rewrite
 */

import java.util.*;

public class TaskA {
    private static int n;
    private static List<Set<Integer>> g;
    private static Set<Integer> visited;

    private static int getPos(int var) {
        if (var > 0) {
            return var + n - 1;
        } else {
            return -1 - var;
        }
    }

    private static int getOpposite(int var) {
        if (var >= n) {
            return var - n;
        } else {
            return var + n;
        }
    }

    private static boolean _dfs(int from, int to) {
        if (from == to) {
            return true;
        } else if (visited.contains(from)) {
            return false;
        } else {
            visited.add(from);
        }

        Set<Integer> dir = g.get(from);

        for (int node : dir) {
            if (_dfs(node, to)) {
                return true;
            }
        }

        return false;
    }

    private static boolean dfs(int from, int to) {
        visited = new HashSet<>();
        return _dfs(from, to);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        g = new ArrayList<>(2 * n);
        for (int i = 0; i < n * 2; i++) {
            g.add(new HashSet<Integer>());
        }

        for (int i = 0; i < m; i++) {
            int f = getPos(sc.nextInt());
            int s = getPos(sc.nextInt());
            g.get(getOpposite(f)).add(s);
            g.get(getOpposite(s)).add(f);
        }

        for (int i = 0; i < n; i++) {
            if (dfs(i, getOpposite(i)) && dfs(getOpposite(i), i)) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}
